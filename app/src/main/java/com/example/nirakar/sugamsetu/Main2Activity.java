package com.example.nirakar.sugamsetu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Main2Activity extends AppCompatActivity {


    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final EditText name,email,password,phone,usertype;
        Button signupbtn;

        auth=FirebaseAuth.getInstance();


        name=findViewById(R.id.signupusername);
        email=findViewById(R.id.signupemail);
        password=findViewById(R.id.signuppassword);
        phone=findViewById(R.id.signupphone);
        usertype=findViewById(R.id.signupusertype);


        signupbtn=findViewById(R.id.signupbtn);

        signupbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                auth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            //put the stuff in database


                            DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("users").child(usertype.getText().toString()).child(auth.getCurrentUser().getUid());
                            Map map=new HashMap();
                            map.put("email",email.getText().toString());
                            map.put("phone",phone.getText().toString());
                            map.put("name",name.getText().toString());

                            reference.updateChildren(map);

                            Intent i=new Intent(Main2Activity.this,sihmain.class);
                            startActivity(i);









                            Toast.makeText(Main2Activity.this,"DONE",Toast.LENGTH_SHORT).show();




                            //move to new page



                        }
                    }
                });


            }
        });




    }
}
