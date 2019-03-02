package com.example.nirakar.sugamsetu;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.location.Location;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.HashMap;
import java.util.Map;

public class Main3Activity extends AppCompatActivity
{
    FirebaseAuth auth;
    ImageView img;
    Bitmap photo;

    Uri filepath;

    EditText description;

    FusedLocationProviderClient fusedLocationProviderClient;

    StorageReference reference;

    Intent i;
    String pid,type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Button setup, upload;

        i=getIntent();

        description=findViewById(R.id.description);

        pid=i.getStringExtra("pid");
        type=i.getStringExtra("type");

        img = findViewById(R.id.imageView);
        setup = findViewById(R.id.button);
        upload = findViewById(R.id.button3);

        auth = FirebaseAuth.getInstance();

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);


        setup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(Intent.createChooser(i, "Select the image"), 0);
            }
        });


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (filepath != null) {


                    reference = FirebaseStorage.getInstance().getReference();

                    StorageReference ref = reference.child("Users").child(auth.getCurrentUser().getUid()).child(pid);
                    ref.putFile(filepath).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(Main3Activity.this, "Uploaded", Toast.LENGTH_SHORT).show();
                        }
                    });

                    if (ActivityCompat.checkSelfPermission(Main3Activity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(Main3Activity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    Task locationresult = fusedLocationProviderClient.getLastLocation();

                    locationresult.addOnCompleteListener(new OnCompleteListener() {
                        @Override
                        public void onComplete(@NonNull Task task) {
                            if(task.isSuccessful())
                            {
                                Location location=(Location) task.getResult();
                                DatabaseReference reference=FirebaseDatabase.getInstance().getReference().child("Location").child(type).child(auth.getCurrentUser().getUid()).child(pid);
                                Map map=new HashMap();
                                map.put("lat",location.getLatitude());
                                map.put("lon",location.getLongitude());
                                map.put("alt",location.getAltitude());
                                map.put("timestamp",System.currentTimeMillis());
                                map.put("description",description.getText().toString());
                                reference.updateChildren(map);


                                DatabaseReference reference1=FirebaseDatabase.getInstance().getReference().child("Users").child(auth.getCurrentUser().getUid()).child("pid");
                                Map map1=new HashMap();
                                map1.put(pid,"1");
                                reference1.updateChildren(map1);

                                DatabaseReference reference2=FirebaseDatabase.getInstance().getReference().child("Pid").child(auth.getCurrentUser().getUid()).child(pid);
                                Map map2=new HashMap();

                                map2.put("lat",location.getLatitude());
                                map2.put("lon",location.getLongitude());
                                map2.put("alt",location.getAltitude());
                                map2.put("timestamp",System.currentTimeMillis());
                                map2.put("description",description.getText().toString());
                                reference2.updateChildren(map2);


                                DatabaseReference reference3=FirebaseDatabase.getInstance().getReference().child("Users");
                                reference3.addValueEventListener(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        for(DataSnapshot snapshot:dataSnapshot.getChildren())
                                        {
                                            if(snapshot.child("Category").getValue().toString().equals("Chief Engineer"))
                                            {
                                                DatabaseReference reference33=FirebaseDatabase.getInstance().getReference().child("Notifications").child(snapshot.getKey()).push();
                                                Map map3=new HashMap();
                                                map3.put("from",auth.getCurrentUser().getUid());

                                                reference33.updateChildren(map3);
                                            }
                                        }

                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {

                                    }
                                });





                                Intent i=new Intent(Main3Activity.this,ContractorActivity.class);
                                startActivity(i);

                            }
                        }
                    });

                }

            }
        });




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if(requestCode==0)
        {

            photo=(Bitmap)data.getExtras().get("data");

            filepath=getImageUri(Main3Activity.this,photo);






            img.setImageURI(filepath);
        }





    }


    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
}
