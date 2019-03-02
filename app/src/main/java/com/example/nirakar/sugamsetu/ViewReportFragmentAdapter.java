package com.example.nirakar.sugamsetu;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Calendar;

public class ViewReportFragmentAdapter extends RecyclerView.Adapter<ViewReportFragmentAdapter.viewholder> {

    ArrayList<String> filename,uid,timestamp,latlon,desc;


    Context context;
    View view;

    FirebaseAuth auth;

    StorageReference ref;

    public ViewReportFragmentAdapter(Context context, ArrayList<String> filename,ArrayList<String> uid,ArrayList<String> timestamp,ArrayList<String> latlon,ArrayList<String>desc) {
        this.context = context;
        this.filename = filename;
        this.timestamp=timestamp;
        this.latlon=latlon;
        this.uid=uid;
        this.desc=desc;





    }




    @NonNull
    @Override
    public viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        view = LayoutInflater.from(context).inflate(R.layout.customviewreportrecyclerview, parent, false);


        return new viewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final viewholder holder, final int position) {

        auth=FirebaseAuth.getInstance();
        holder.file.setText(""+filename.get(position));
        Long t = Long.valueOf(timestamp.get(position));
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(t);

        int mYear = calendar.get(Calendar.YEAR);
        int mMonth = calendar.get(Calendar.MONTH) + 1;
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);

        int hrs = calendar.get(Calendar.HOUR_OF_DAY);
        int mins = calendar.get(Calendar.MINUTE);
        holder.datetime.setText("" + mYear + "-" + "" + mMonth + "-" + "" + mDay+" "+"" + hrs + ":" + "" + mins);
        holder.latlon.setText(latlon.get(position));

        holder.desc.setText(desc.get(position));

        // Log.d("helelll",inid+caseno+cid+auth.getCurrentUser().getUid()+filename.get(position));
        final StorageReference ref = FirebaseStorage.getInstance().getReference().child("Users").child(uid.get(position)).child(filename.get(position));


        Glide.with(context)
                .using(new FirebaseImageLoader())
                .load(ref)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .skipMemoryCache(true)
                .into(holder.custom);





        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,ViewSingleReport.class);
                context.startActivity(i);
            }
        });

    }


    @Override
    public int getItemCount() {
        return filename.size();
    }

    public  class viewholder extends RecyclerView.ViewHolder{
        TextView file,datetime,latlon,desc;
        ImageView custom;
        Button callbtn;
        Dialog dialog;
        Layout layout;
        Button submitbtn;
        EditText editText;
        public viewholder(View itemView) {
            super(itemView);

            file=itemView.findViewById(R.id.custommultiplephotoname);
            custom=itemView.findViewById(R.id.custommultiplephoto);
            datetime=itemView.findViewById(R.id.customdatetime);
            latlon=itemView.findViewById(R.id.customlatlon);
            desc=itemView.findViewById(R.id.customdescription);
            dialog=new Dialog(context);





            // customer_pic=
            // (ImageView) itemView.findViewById(R.id.doctor_pic);

            Log.d("TAAAAG","kk");

        }

    }

}

