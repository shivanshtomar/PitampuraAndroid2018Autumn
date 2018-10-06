package com.example.shivanshtomar.planet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


@SuppressLint("ValidFragment")
public class DetailActivityFragment extends Fragment {
    String name,desc,url;




    @SuppressLint("ValidFragment")
    public DetailActivityFragment(String name, String desc, String url) {
         this.url=url;
        this.name = name;
        this.desc= desc;
    }




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_detail,container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tvName= view.findViewById(R.id.tvName);
        TextView tvDesc =view.findViewById(R.id.tvDesc);
         ImageView imgName =view.findViewById(R.id.imgView);
        //ImageView imgView = view.findViewById(id.imgView);

        Intent i = getActivity().getIntent();

        if(i.hasExtra("NAME")){
            String recievedName = i.getStringExtra("NAME");

            //String url = i.getStringExtra("URL");
            tvName.setText(recievedName);

        }
        else
            tvName.setText(name);

       if(i.hasExtra("URL")){
           Log.e("TAG", "onViewCreated: ImageView------- --------");
           String recievedUrl =i.getStringExtra("URL");
           Picasso.get()
                   .load(recievedUrl)
                   .placeholder(R.mipmap.ic_launcher)
                   .error(R.mipmap.ic_image)
                   .into(imgName);
       }
       else {
           Picasso.get()
                   .load(url)
                   .placeholder(R.mipmap.ic_launcher)
                   .error(R.mipmap.ic_image)
                   .into(imgName);
       }
        if(i.hasExtra("DESC")){
            String recievedDesc =i.getStringExtra("DESC");
            tvDesc.setText(recievedDesc);

        }
        else
            tvDesc.setText(desc);

    }
}
