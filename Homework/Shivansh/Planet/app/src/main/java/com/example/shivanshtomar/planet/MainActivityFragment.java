package com.example.shivanshtomar.planet;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MainActivityFragment extends Fragment{

    ArrayList<Planets> planets= new ArrayList<>();


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String url = "https://www.colourbox.com/preview/24934266-isometric-mercury-planet-vector-illustration.jpg";

        planets.add(new Planets("Mercury","This is MerDesc",url));
        planets.add(new Planets("Venus","This is VDesc","https://media.wired.com/photos/592684798d4ebc5ab806a8d1/master/w_1164,c_limit/PlanetVenusTA-680802775.jpg" ));
        planets.add(new Planets("Earth","This is EDesc","https://ak3.picdn.net/shutterstock/videos/24290543/thumb/1.jpg"));
        planets.add(new Planets("Mars","This is MDesc","https://d1a9v60rjx2a4v.cloudfront.net/2016/12/25/10_30_43_272_1200_mars14_1.jpg"));
        planets.add(new Planets("Jupiter","This is JDesc","https://3.bp.blogspot.com/-JzB2ruOjBOs/WJy8tR_tJSI/AAAAAAAABdA/26gANOQ4Y4IZyMnEGS2L8X-dvhVhGL0ZQCLcB/s1600/jupiter_HD.jpg"));
        planets.add(new Planets("Saturn","This is SDesc","https://d2v9y0dukr6mq2.cloudfront.net/video/thumbnail/p796KEh/saturn-planet-ring-asteroids_4d-i4d1yx__F0012.png"));
        planets.add(new Planets("Uranus","This is UDesc","https://www.weltderphysik.de/typo3temp/_processed_/e/a/csm_20121114_EinsamerPlanet_ESO-VVV-et-al_7b15342ea9.jpg"));
        planets.add(new Planets("Neptune","This is NDesc","https://i.ebayimg.com/images/g/5t0AAOSwFNZWzK9n/s-l300.jpg"));



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

         return inflater.inflate(R.layout.fragment_main, container,false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView recyclerView= view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        PlanetsAdapter planetsAdapter = new PlanetsAdapter(planets, (AppCompatActivity) getActivity());
        recyclerView.setAdapter(planetsAdapter);
    }
}


