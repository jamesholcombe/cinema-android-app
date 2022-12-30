package com.example.BigScreenCinema.ViewModels.DataModels;

abstract public class Base {
    private String id;

    public Base() {

    }

    public Base(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
