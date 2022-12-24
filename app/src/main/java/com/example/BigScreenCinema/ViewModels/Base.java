package com.example.BigScreenCinema.ViewModels;

abstract public class Base {
    private String id;

    public String getId() {
        return id;
    }

    public Base (String id){
        this.id = id;
    }
}
