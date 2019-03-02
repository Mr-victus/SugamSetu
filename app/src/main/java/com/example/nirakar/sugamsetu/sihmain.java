package com.example.nirakar.sugamsetu;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class sihmain extends AppCompatActivity {
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sihmain);
        final EditText email,password;

        email=findViewById(R.id.editText10);
        password=findViewById(R.id.editText);


        auth=FirebaseAuth.getInstance();



        Button login;
        TextView signup;

        login=findViewById(R.id.button2);
        signup=findViewById(R.id.textView6);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(sihmain.this,Main2Activity.class);
                startActivity(i);

            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signInWithEmailAndPassword(email.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            Toast.makeText(sihmain.this,"LOgin successful",Toast.LENGTH_SHORT).show();

                            Intent i=new Intent(sihmain.this,Main3Activity.class);
                            startActivity(i);


                        }

                    }
                });
            }
        });







    }
}
