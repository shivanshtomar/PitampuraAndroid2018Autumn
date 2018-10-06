package com.example.shivanshtomar.planet;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PlanetsAdapter extends RecyclerView.Adapter<PlanetsAdapter.PlanetsHolder> {


    private ArrayList<Planets> planets;
    private AppCompatActivity appCompatActivity;
    private Context context;

    public PlanetsAdapter(ArrayList<Planets> planets, AppCompatActivity activity) {
        this.planets = planets;
        this.appCompatActivity = activity;
    }

    @NonNull
    @Override
    public PlanetsHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();

        LayoutInflater li = LayoutInflater.from(viewGroup.getContext());
        View inflatedView = li.inflate(R.layout.item_view, viewGroup, false);
        return new PlanetsHolder(inflatedView);
    }

    @Override
    public void onBindViewHolder(@NonNull PlanetsHolder planetsHolder, int i) {

        final Planets currPlanet = planets.get(i);
        Picasso.get()
                .load(currPlanet.getUrl())
                .placeholder(R.mipmap.ic_launcher)
                .error(R.mipmap.ic_image)
                .into(planetsHolder.imageView);

        planetsHolder.planetName.setText(currPlanet.getPlanetName());
        planetsHolder.desc.setText(currPlanet.getDesc());
        //picasso

    }

    @Override
    public int getItemCount() {
        return planets.size();
    }

    class PlanetsHolder extends RecyclerView.ViewHolder {

        TextView planetName, desc;
        ImageView imageView;

        public PlanetsHolder(@NonNull View view) {
            super(view);


            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Planets currPlanet = planets.get(getAdapterPosition());

                    Log.e("TAG", "onClick1:-----++++--- ");
                    if (appCompatActivity.findViewById(R.id.container) == null) {
                        Log.e("TAG", "onClick2:-------- ");

                        startIntent(currPlanet.getPlanetName(), currPlanet.getDesc(), currPlanet.getUrl());
                    } else
                        addFragment(currPlanet.getPlanetName(), currPlanet.getDesc(),currPlanet.getUrl());
                }
            });

            imageView = view.findViewById(R.id.imageView);
            planetName = view.findViewById(R.id.planetName);
            desc = view.findViewById(R.id.planetDesc);
        }

        private void startIntent(String planetName, String desc, String url) {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("NAME", planetName);
            intent.putExtra("DESC", desc);
            intent.putExtra("URL", url);

            Log.e("TAG", "startIntent: ------" + planetName);
            context.startActivity(intent);

            Log.e("TAG", "startIntent---------: ------");

        }

        private void addFragment(String name, String descpt,String url) {

            FragmentManager fm = appCompatActivity.getSupportFragmentManager();
            fm.beginTransaction()
                    .add(R.id.container, new DetailActivityFragment(name, descpt, url))
                    .commit();
        }
    }
}
