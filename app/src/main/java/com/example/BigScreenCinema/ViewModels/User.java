package com.example.BigScreenCinema.ViewModels;

public class User extends Base {

    private Booking[] bookings;
    private User user;


    public User(String id) {
        super(id);
    }

    public User(String id, Booking[] bookings) {
        super(id);
        this.bookings = bookings;
    }

}
