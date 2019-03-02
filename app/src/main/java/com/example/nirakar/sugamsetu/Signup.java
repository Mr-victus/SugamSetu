package com.example.nirakar.sugamsetu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.isapanah.awesomespinner.AwesomeSpinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Signup extends AppCompatActivity

{
    EditText sign_name, sign_address, sign_phone, sign_mail,sign_password;
    Button btn_click;
    String name, address, phone, mail,password,type;

    FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        sign_name= findViewById(R.id.sign_name);
        sign_address= findViewById(R.id.sign_address);
        sign_phone= findViewById(R.id.sign_phone);
        sign_mail= findViewById(R.id.sign_mail);
        sign_password=findViewById(R.id. sign_password);
        btn_click = findViewById(R.id.btn_click);


        auth=FirebaseAuth.getInstance();

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = database.getReference("User");


        final AwesomeSpinner spinner_type =  findViewById(R.id.spinner_type);

        spinner_type.setSelectedItemHintColor(getResources().getColor(R.color.colorAccent));
        spinner_type.setDownArrowTintColor(getResources().getColor(R.color.colorAccent));

        final List<String> categories = new ArrayList<String>();
        categories.add("Chief Engineer");
        categories.add("Executive Engineer");
        categories.add("Subdivision Officer");
        categories.add("Junior Engineer");
        categories.add("Contractor");

        ArrayAdapter<String> categoriesAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);
        spinner_type.setAdapter(categoriesAdapter);

        spinner_type.setOnSpinnerItemClickListener(new AwesomeSpinner.onSpinnerItemClickListener<String>() {
            @Override
            public void onItemSelected(int position, String itemAtPosition) {
                Toast.makeText(getApplicationContext(), "Position: "+position+" | Item: "+itemAtPosition+" | isSelected:"+spinner_type.isSelected(), Toast.LENGTH_LONG).show();
                type = itemAtPosition;
                Toast.makeText(Signup.this, type, Toast.LENGTH_SHORT).show();

            }
        });


        btn_click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = sign_name.getText().toString();
                address =  sign_address.getText().toString();
                phone = sign_phone.getText().toString();
                mail = sign_mail.getText().toString();
                password=sign_password.getText().toString();
                if (name.isEmpty()||address.isEmpty()||phone.isEmpty()||mail.isEmpty()||password.isEmpty())
                {
                    Toast.makeText(Signup.this, "Fill all Fields", Toast.LENGTH_SHORT).show();
                }
                else
                {
                   /* Toast.makeText(Signup.this, "Sign up Success", Toast.LENGTH_SHORT).show();
                    Toast.makeText(Signup.this, "Now login to use the app", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(Signup.this,Login.class);
                    startActivity(intent);*/



                   auth.createUserWithEmailAndPassword(mail,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {

                            if(task.isSuccessful()) {
                                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users").child(auth.getCurrentUser().getUid());

                                reference.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        Map map = new HashMap();
                                        map.put("Email", mail);
                                        map.put("Phone", phone);
                                        map.put("Category", spinner_type.getSelectedItem().toString());
                                        map.put("Address", address);
                                        map.put("Name", name);
                                        reference.updateChildren(map);


                                        Intent i = new Intent(Signup.this, Login.class);
                                        startActivity(i);
                                        finish();


                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });
                            }
                            else {
                                Toast.makeText(Signup.this,"Sign up Error",Toast.LENGTH_SHORT).show();
                            }
                       }
                   });


                   /* if (Common.isConnectedToInternet(getBaseContext()))
                    {
                        final ProgressDialog progressDialog = new ProgressDialog(Signup.this);

                        progressDialog.setMessage("Signing Up...");
                        progressDialog.show();

                        databaseReference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.child(sign_phone.getText().toString()).exists()) {
                                    progressDialog.dismiss();
                                    Toast.makeText(Signup.this, "Phone number exists", Toast.LENGTH_SHORT).show();

                                } else {
                                    progressDialog.dismiss();
                                    Users user = new Users(sign_name.getText().toString(),sign_phone.getText().toString(),sign_mail.getText().toString(), sign_password.getText().toString(),sign_address.getText().toString(),type);
                                    databaseReference.child(sign_phone.getText().toString()).setValue(user);
                                    Toast.makeText(Signup.this, "Registered Successfully", Toast.LENGTH_SHORT).show();
                                    Toast.makeText(Signup.this, "Now Login to use it", Toast.LENGTH_LONG).show();
                                    Intent intent=new Intent(Signup.this,Login.class);
                                    startActivity(intent);
                                    finish();

                                }

                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError)
                            {
                                Toast.makeText(Signup.this, "There is an error in database!!!", Toast.LENGTH_SHORT).show();

                            }
                        });
                    }
                    else
                    {
                        Toast.makeText(Signup.this, "Check Internet Conneciton", Toast.LENGTH_LONG).show();
                    }*/
                }
            }
        });
    }
}
