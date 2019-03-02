package com.example.nirakar.sugamsetu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class progressreport extends AppCompatActivity
{
    EditText progress_projectid;
    Button btn_uploadpic,btn_uploadactivity;
    String pid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progressreport);



        progress_projectid=findViewById(R.id.progress_projectid);
        btn_uploadpic=findViewById(R.id.btn_uploadpic);
        btn_uploadactivity=findViewById(R.id.btn_uploadactivity);





      btn_uploadpic.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v)
          {

              pid=progress_projectid.getText().toString();



              if(!pid.isEmpty()) {

                  Toast.makeText(progressreport.this, "Success", Toast.LENGTH_SHORT).show();


                  Intent intent = new Intent(progressreport.this, Main3Activity.class);
                  intent.putExtra("type", "Contractor");
                  intent.putExtra("pid", "1" + pid);
                  startActivity(intent);

              }


          }
      });
      btn_uploadactivity.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent intent=new Intent(progressreport.this,uploadactivity.class);
              startActivity(intent);

          }
      });
    }
}
