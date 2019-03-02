package com.example.nirakar.sugamsetu;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;

public class resourcerequirement extends AppCompatActivity
{
    EditText et_req;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resourcereq);

        et_req=findViewById(R.id.et_req);
    }
}
