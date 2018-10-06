package com.example.shivanshtomar.apinetworking;

import android.annotation.SuppressLint;
import android.content.Intent;
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

public class DetailFragment extends Fragment {
    String author,title,description,url,urlToImage,publishedAt,content;
    public DetailFragment(){

    }
     @SuppressLint("ValidFragment")
    public DetailFragment(String url, String author, String img, String content, String title, String desc, String publishedAt) {
        this.author = author;
        this.title = title;
        this.description = desc;
        this.url = url;
        this.urlToImage = img;
        this.publishedAt = publishedAt;
        this.content = content;

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detailed_row,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView authorView = view.findViewById(R.id.author);
        TextView titleView = view.findViewById(R.id.title);
        TextView contentView = view.findViewById(R.id.contentView);
        TextView desc = view.findViewById(R.id.desc);
        ImageView img = view.findViewById(R.id.img);
        ImageView urlView = view.findViewById(R.id.url);
        TextView publishedAtView = view.findViewById(R.id.publishedAt);

        Intent i = getActivity().getIntent();

        if(i.hasExtra("author")){
            String recievedName = i.getStringExtra("author");
            authorView.setText(recievedName);

        }
        else
            authorView.setText(author);

        if(i.hasExtra("title")){
            String recievedTitle = i.getStringExtra("title");
            titleView.setText(recievedTitle);

        }
        else
            titleView.setText(title);

        if(i.hasExtra("desc")){
            String recievedName = i.getStringExtra("desc");
            desc.setText(recievedName);
        }
        else
            desc.setText(description);

        if(i.hasExtra("content")){
            String recievedName = i.getStringExtra("content");
            contentView.setText(recievedName);
        }
        else
            contentView.setText(content);

        if(i.hasExtra("publishedAt")){
            String recievedName = i.getStringExtra("publishedAt");
            publishedAtView.setText(recievedName);
        }
        else
            publishedAtView.setText(publishedAt);

        if(i.hasExtra("url")){
            Log.e("TAG", "onViewCreated: ImageView------- --------");
            String recievedUrl =i.getStringExtra("url");
            Picasso.get()
                    .load(recievedUrl)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_image)
                    .into(urlView);
        }
        else {
            Picasso.get()
                    .load(url)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_image)
                    .into(urlView);
        }
        if(i.hasExtra("img")){
            Log.e("TAG", "onViewCreated: ImageView------- --------");
            String recievedUrl =i.getStringExtra("img");
            Picasso.get()
                    .load(recievedUrl)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_image)
                    .into(img);
        }
        else {
            Picasso.get()
                    .load(urlToImage)
                    .placeholder(R.mipmap.ic_launcher)
                    .error(R.mipmap.ic_image)
                    .into(img);
        }

    }
}
