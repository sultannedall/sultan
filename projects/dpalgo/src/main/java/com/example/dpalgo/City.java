package com.example.dpalgo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class City {
    private String name;
    private List<AdjacentCity> adjacents;

    public City(String name) {
        this.name = name;
        this.adjacents = new ArrayList();
    }

    public String getName() {
        return this.name;
    }

    public List<AdjacentCity> getAdjacents() {
        return this.adjacents;
    }

    public void addAdjacent(AdjacentCity adjacentCity) {
        this.adjacents.add(adjacentCity);
    }

    public AdjacentCity getAdjacent(String cityName) {
        Iterator var3 = this.adjacents.iterator();

        while(var3.hasNext()) {
            AdjacentCity adjacent = (AdjacentCity)var3.next();
            if (adjacent.getName().equals(cityName)) {
                return adjacent;
            }
        }

        return null;
    }

    public String toString() {
        return "City{name='" + this.name + "'";
    }
}
