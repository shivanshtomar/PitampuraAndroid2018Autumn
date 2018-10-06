package com.example.shivanshtomar.apinetworking;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

    private ArrayList<News> arrayList;
    private Context context;
    private AppCompatActivity appCompatActivity;

    public NewsAdapter(ArrayList<News> arrayList) {
        this.arrayList = arrayList;
    }

    public NewsAdapter(ArrayList<News> arrayList, AppCompatActivity appCompatActivity) {
        this.arrayList = arrayList;
        this.appCompatActivity = appCompatActivity;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_row, viewGroup, false);

        return new NewsHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder newsHolder, int i) {
        News currentNews = arrayList.get(i);
        newsHolder.author.setText(currentNews.getAuthor());
        newsHolder.desc.setText(currentNews.getDescription());
        newsHolder.title.setText(currentNews.getTitle());
        Picasso.get().load(currentNews.getUrlToImage()).into(newsHolder.userImage);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class NewsHolder extends RecyclerView.ViewHolder {
        ImageView userImage;
        TextView author, title, desc;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    News currNews = arrayList.get(getAdapterPosition());

                    if (appCompatActivity.findViewById(R.id.container) == null) {
                        startIntent(currNews.getUrl(), currNews.getAuthor(), currNews.getUrlToImage(), currNews.getContent(),
                                currNews.getTitle(), currNews.getDescription(), currNews.getPublishedAt());
                    } else {
                        addFragment(currNews.getUrl(), currNews.getAuthor(), currNews.getUrlToImage(), currNews.getContent(),
                                currNews.getTitle(), currNews.getDescription(), currNews.getPublishedAt());
                    }
                }
            });
            userImage = itemView.findViewById(R.id.userImage);
            author = itemView.findViewById(R.id.author);
            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
        }

        private void startIntent(String url, String author, String img, String content, String title, String desc, String publishedAt) {
            Intent intent = new Intent(context, DetailNews.class);

            intent.putExtra("url", url);
            intent.putExtra("author", author);
            intent.putExtra("title", title);
            intent.putExtra("content", content);
            intent.putExtra("desc", desc);
            intent.putExtra("publishedAt", publishedAt);
            intent.putExtra("img", img);

            context.startActivity(intent);
        }

        private void addFragment(String url, String author, String img, String content, String title, String desc, String publishedAt) {
            FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
            fragmentManager.beginTransaction()
                    .add(R.id.container, new DetailFragment(url, author, img, content, title, desc, publishedAt))
                    .commit();
        }
    }
}
