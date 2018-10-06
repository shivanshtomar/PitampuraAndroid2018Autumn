package com.example.shivanshtomar.planet;

import java.net.URL;

public class Planets {

    String PlanetName, desc;
    String url;

    public Planets(String planetName, String desc, String url) {
        PlanetName = planetName;
        this.desc = desc;
        this.url= url;
    }

    public String getPlanetName() {
        return PlanetName;
    }

    public String  getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setPlanetName(String planetName) {
        PlanetName = planetName;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
