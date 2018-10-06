package com.example.shivanshtomar.apinetworking;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {

    String url = "https://newsapi.org/v2/top-headlines?sources=techcrunch&apiKey=31a5a222701c41d1adc59b35841301c2";
    ArrayList<News> arrayList= new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnFetch = findViewById(R.id.btnFetch);

        btnFetch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeNetworkCall(url);
            }
        });
    }

    private void makeNetworkCall(String url) {
        OkHttpClient client =new OkHttpClient();

        final Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

               // call.enqueue(this);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,"SORRY!!!Request Failed,RETRY!",Toast.LENGTH_SHORT);

                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                //response can only be used once. If we use it again, it would produce an error
                //it is so because it implements a Closeable(an interface)

                //final String result = response.body().string();
               // parseJson(result);

                //Views cant be modified here because this is a different thread than Main/UI thread
                //However we can modify variables of main thread here but not any view

                String jsonResponse = response.body().string();
                arrayList= parseJson(jsonResponse);
                Gson gson = new Gson();
                News news = gson.fromJson(jsonResponse,News.class);

                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        //This code runs in main thread
                        //TextView textView =findViewById(R.id.tvView);
                       // textView.setText(result);

                        RecyclerView recyclerView = findViewById(R.id.rView);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                        NewsAdapter newsAdapter = new NewsAdapter(arrayList);
                        recyclerView.setAdapter(newsAdapter);
                    }
                });
            }
        });

    }

    public ArrayList<News> parseJson(String input){
        ArrayList<News> newsArrayList = new ArrayList<>();
        try {
            JSONObject jsonObject=new JSONObject(input);
            JSONArray jsonArray = jsonObject.getJSONArray("articles");

            for (int i=0;i<jsonArray.length();i++){
                JSONObject currObject =jsonArray.getJSONObject(i);

                String author = currObject.optString("author");
                String title,description,url,urlToImage,publishedAt,content;
                title = currObject.optString("title");
                description =currObject.optString("description");
                url= currObject.optString("url");
                urlToImage = currObject.optString("urlToImage");
                publishedAt = currObject.optString("publishedAt");
                content = currObject.optString("content");

                News currNews = new News(author,title,description,url,urlToImage,publishedAt,content);

                newsArrayList.add(currNews);


            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return newsArrayList;
    }
}
