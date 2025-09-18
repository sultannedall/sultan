package com.example.dpalgo;

public class Route {
    private String path;
    private int cost;

    public Route(String path, int cost) {
        this.path = path;
        this.cost = cost;
    }

    public String getPath() {
        return this.path;
    }

    public int getCost() {
        return this.cost;
    }
}
