package com.example.nirakar.sugamsetu;

import android.content.Intent;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import me.relex.circleindicator.CircleIndicator;

public class ContractorActivity extends AppCompatActivity {
    int currentPage = 0;
    ViewPager mPager;

    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contractor);

        final Button progressreport=findViewById(R.id.progressreport);

        auth=FirebaseAuth.getInstance();

        String devicetoken=FirebaseInstanceId.getInstance().getToken();
        DatabaseReference reference1=FirebaseDatabase.getInstance().getReference().child("Users").child(auth.getCurrentUser().getUid());
        Map nmap=new HashMap();
        nmap.put("devicetoken",devicetoken);
        reference1.updateChildren(nmap);




        progressreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i=new Intent(ContractorActivity.this,progressreport.class);
                startActivity(i);


            }
        });

    }
}
