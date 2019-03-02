package com.example.nirakar.sugamsetu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import android.support.v7.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;

public class MainActivity extends AppCompatActivity
{
    Button casestudy;
    Button datasection;
    Button accountsection;
    Button projectdetails;
    Button resourcerequirement;
    Button progressreport;
    Button quickfeatures;
    Button notification;
    FirebaseAuth auth;


    private static final Integer[] XMEN = { R.drawable.images_one, R.drawable.image_two, R.drawable.images_three, R.drawable.image_four, R.drawable.image_five};
    private ArrayList<Integer> XMENArray = new ArrayList<Integer>();
    ViewPager mPager;

    private static int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        casestudy=findViewById(R.id.casestudy);
        datasection=findViewById(R.id.datasection);
        accountsection=findViewById(R.id.accountsection);
        projectdetails=findViewById(R.id.projectdetails);
        resourcerequirement=findViewById(R.id.resourcerequirement);
        progressreport=findViewById(R.id.progressreport);
        quickfeatures=findViewById(R.id.quickfeatures);
        notification=findViewById(R.id.notification);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar2);

        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Window window = getWindow();
        window.setStatusBarColor(getResources().getColor(R.color.colorPrimary));

        auth=FirebaseAuth.getInstance();


        String devicetoken=FirebaseInstanceId.getInstance().getToken();
        DatabaseReference reference1=FirebaseDatabase.getInstance().getReference().child("Users").child(auth.getCurrentUser().getUid());
        Map nmap=new HashMap();
        nmap.put("devicetoken",devicetoken);
        reference1.updateChildren(nmap);



        casestudy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,Casestudy.class);
                startActivity(intent);
            }
        });

        datasection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,datasection.class);
                startActivity(intent);
            }
        });

        accountsection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,accountsection.class);
                startActivity(intent);
            }
        });

       projectdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,projectdetails.class);
                startActivity(intent);
            }
        });

        resourcerequirement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,resourcerequirement.class);
                startActivity(intent);
            }
        });

       /* progressreport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,progressreport.class);
                startActivity(intent);
            }
        });*/

       quickfeatures.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,quickfeatures.class);
                startActivity(intent);
            }
        });

       notification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,notification.class);
                startActivity(intent);
            }
        });

        for (int i = 0; i < XMEN.length; i++)
            XMENArray.add(XMEN[i]);
        mPager = (ViewPager) findViewById(R.id.pager);
        mPager.setAdapter(new SlideAdapter(MainActivity.this, XMENArray));

        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == XMEN.length) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 2500, 2500);
    }
}
