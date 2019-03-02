package com.example.nirakar.sugamsetu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class additionalinfor extends AppCompatActivity
{
    EditText etone,ettwo,etthree,etfour,etfive;
    Button btn_finalupload,btn_notifyall;
    String s1,s2,s3,s4,s5;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_additionalinfo);

        etone=findViewById(R.id.etone);
        ettwo=findViewById(R.id.ettwo);
        etthree=findViewById(R.id.etthree);
        etfour=findViewById(R.id.etfour);
        etfive=findViewById(R.id.etfive);
        btn_finalupload=findViewById(R.id.btn_finalupload);
        btn_notifyall=findViewById(R.id.btn_notifyall);

        s1=etone.getText().toString();
        s2=ettwo.getText().toString();
        s3=etthree.getText().toString();
        s4=etfour.getText().toString();
        s5=etfive.getText().toString();

        btn_finalupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                if(s1.isEmpty() && s2.isEmpty() && s3.isEmpty() && s4.isEmpty() && s5.isEmpty())
                {
                    Toast.makeText(additionalinfor.this, "Fail", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(additionalinfor.this, "Success", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}
