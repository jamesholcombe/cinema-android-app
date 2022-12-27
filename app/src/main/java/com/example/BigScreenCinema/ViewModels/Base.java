package com.example.BigScreenCinema.ViewModels;

abstract public class Base {
    private String id;

    public Base(){

    }

    public Base(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
