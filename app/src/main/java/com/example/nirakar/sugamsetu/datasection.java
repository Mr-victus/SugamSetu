package com.example.nirakar.sugamsetu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class datasection extends AppCompatActivity
{

    EditText data_projectid;
    Button btn_checkone;
    CheckBox check_p_survey,check_s_survey,check_geo_tech,check_d_document,check_a_information;
    String projectid;
    String sp,s;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.datasection);

        data_projectid=findViewById(R.id.data_projectid);
        btn_checkone=findViewById(R.id.btn_checkone);
        check_p_survey=findViewById(R.id.check_p_survey);
        check_s_survey=findViewById(R.id.check_s_survey);
        check_geo_tech=findViewById(R.id.check_geo_tech);
        check_d_document=findViewById(R.id.check_d_document);
        check_a_information=findViewById(R.id.check_a_information);

        projectid=data_projectid.getText().toString();

        btn_checkone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                sp = data_projectid.getText().toString();


                if (check_p_survey.isChecked())
                {
                    s = check_p_survey.getText().toString();
                    Toast.makeText(datasection.this, s, Toast.LENGTH_SHORT).show();


                }
                else

                if (check_s_survey.isChecked())
                {
                    s = check_s_survey.getText().toString();
                    Toast.makeText(datasection.this, s, Toast.LENGTH_SHORT).show();

                }else

                if (check_geo_tech.isChecked())
                {
                    s= check_geo_tech.getText().toString();
                    Toast.makeText(datasection.this, s, Toast.LENGTH_SHORT).show();

                }else
                if (check_d_document.isChecked())
                {
                    s= check_d_document.getText().toString();
                    Toast.makeText(datasection.this, s, Toast.LENGTH_SHORT).show();

                }else
                if (check_a_information.isChecked())
                {
                    s= check_a_information.getText().toString();
                    Toast.makeText(datasection.this, s, Toast.LENGTH_SHORT).show();

                }



                if (sp.isEmpty()||s.isEmpty())
                {
                    Toast.makeText(datasection.this, "Select id and checkbox", Toast.LENGTH_SHORT).show();
                }
                else if (!sp.isEmpty()&& s.equals("Priliminary Survey Report"))
                {
                    startActivity(new Intent(datasection.this, psurveyreport.class));
                }


                if (sp.isEmpty()||s.isEmpty())
                {
                    Toast.makeText(datasection.this, "Select id and checkbox", Toast.LENGTH_SHORT).show();
                }
                else if (!sp.isEmpty()&& s.equals("Site Survey Report"))
                {
                    startActivity(new Intent(datasection.this, ssurveyreport.class));
                }


                if (sp.isEmpty()||s.isEmpty())
                {
                    Toast.makeText(datasection.this, "Select id and checkbox", Toast.LENGTH_SHORT).show();
                }
                else if (!sp.isEmpty()&& s.equals("Geo-Technical Report"))
                {
                    startActivity(new Intent(datasection.this, geotech.class));
                }

                if (sp.isEmpty()||s.isEmpty())
                {
                    Toast.makeText(datasection.this, "Select id and checkbox", Toast.LENGTH_SHORT).show();
                }
                else if (!sp.isEmpty()&& s.equals("Design Document"))
                {
                    startActivity(new Intent(datasection.this,designdoc.class));
                }

                if (sp.isEmpty()||s.isEmpty())
                {
                    Toast.makeText(datasection.this, "Select id and checkbox", Toast.LENGTH_SHORT).show();
                }
                else if (!sp.isEmpty()&& s.equals("Additional Information"))
                {
                    startActivity(new Intent(datasection.this, additionalinfor.class));
                }

            }
        });

    }
}
