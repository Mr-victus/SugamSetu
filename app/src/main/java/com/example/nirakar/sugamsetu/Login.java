package com.example.nirakar.sugamsetu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

import io.paperdb.Paper;

public class Login extends AppCompatActivity
{
        EditText et_name;
        EditText et_password;
        Button btn_login;
       //TextView text_name, text_password;

        FirebaseAuth auth;

        String email, password;

        @Override
        protected void onCreate (Bundle savedInstanceState)
        {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.login);


            et_name = findViewById(R.id.et_name);
            et_password = findViewById(R.id.et_password);
            btn_login = findViewById(R.id.btn);
           // text_password = findViewById(R.id.text_password);
           // text_name = findViewById(R.id.text_name);

            FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
            final DatabaseReference table_user = firebaseDatabase.getReference("User");

            Paper.init(this);

            auth=FirebaseAuth.getInstance();

            btn_login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    email = et_name.getText().toString();
                    password = et_password.getText().toString();

                    if (email.isEmpty() || password.isEmpty())
                        {
                        Toast.makeText(Login.this, "All fields are required", Toast.LENGTH_SHORT).show();
                        }
                    else
                        {

                        /*Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Login.this,MainActivity.class));*/


                            auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if(task.isSuccessful()) {

                                        DatabaseReference reference = FirebaseDatabase.getInstance().getReference().child("Users").child(auth.getCurrentUser().getUid());
                                        reference.addValueEventListener(new ValueEventListener() {
                                            @Override
                                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                                                String cat = dataSnapshot.child("Category").getValue().toString();

                                                if (cat.equals("Contractor")) {
                                                    Intent i = new Intent(Login.this, ContractorActivity.class);
                                                    startActivity(i);
                                                    finish();
                                                } else {
                                                    Intent i = new Intent(Login.this, MainActivity.class);
                                                    startActivity(i);
                                                    finish();
                                                }
                                            }

                                            @Override
                                            public void onCancelled(@NonNull DatabaseError databaseError) {

                                            }
                                        });
                                    }
                                    else {
                                        Toast.makeText(Login.this,"Error ",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            /*if (Common.isConnectedToInternet(getBaseContext()))
                            {

                                Paper.book().write(Common.USER_KEY,et_name.getText().toString());
                                Paper.book().write(Common.PWD_KEY,et_password.getText().toString());


                                final ProgressDialog progressDialog = new ProgressDialog(Login.this);
                                progressDialog.setMessage("Logging in...");
                                progressDialog.show();
                                table_user.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(DataSnapshot dataSnapshot) {

                                        if (dataSnapshot.child(et_name.getText().toString()).exists()) {

                                            progressDialog.dismiss();
                                            Users user = dataSnapshot.child(et_name.getText().toString()).getValue(Users.class);
                                            user.setPhone(et_name.getText().toString());





                                            if (user.getPassword().equals(et_password.getText().toString())) {
                                                Toast.makeText(Login.this, "Signin Successfully", Toast.LENGTH_SHORT).show();
                                                Common.currentuser = user;
                                                startActivity(new Intent(Login.this, MainActivity.class));
                                                finish();
                                            } else {

                                                Toast.makeText(Login.this, "Sign in failed!!!! ", Toast.LENGTH_SHORT).show();
                                            }
                                        }
                                        else {
                                            progressDialog.dismiss();
                                            Toast.makeText(Login.this, "User does not exists ", Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onCancelled(DatabaseError databaseError) {

                                    }
                                });
                            }
                            else

                            {
                                Toast.makeText(Login.this, "Please check Internet Connection", Toast.LENGTH_LONG).show();
                                return;
                            }*/


                        }
                    //text_password.setText(password);
                    //text_name.setText(name);
                }
            });

        }
    }




