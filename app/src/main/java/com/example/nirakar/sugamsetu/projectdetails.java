package com.example.nirakar.sugamsetu;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static java.security.AccessController.getContext;

public class projectdetails extends AppCompatActivity {

    FirebaseAuth auth;
    ArrayList<String> filename,uid,timestamp,latlon,desc;
    ViewReportFragmentAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_projectdetails);


        final RecyclerView recyclerView=findViewById(R.id.viewreport);

        auth=FirebaseAuth.getInstance();

        filename=new ArrayList<>();
        uid=new ArrayList<>();
        timestamp=new ArrayList<>();
        latlon=new ArrayList<>();
        desc=new ArrayList<>();



        DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("Pid");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot:dataSnapshot.getChildren())
                {
                    for(DataSnapshot snapshot1:snapshot.getChildren()) {
                        filename.add(snapshot1.getKey());
                        desc.add(snapshot1.child("description").getValue().toString());
                        timestamp.add(snapshot1.child("timestamp").getValue().toString());
                        latlon.add(snapshot1.child("lat").getValue().toString()+snapshot1.child("lon").getValue().toString());
                        uid.add(snapshot.getKey());
                    }
                }

                adapter=new ViewReportFragmentAdapter(projectdetails.this,filename,uid,timestamp,latlon,desc);

                recyclerView.setLayoutManager(new LinearLayoutManager(projectdetails.this));
                recyclerView.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
}
