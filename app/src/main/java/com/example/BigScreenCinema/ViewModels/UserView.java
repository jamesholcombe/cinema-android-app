package com.example.BigScreenCinema.ViewModels;


import com.example.BigScreenCinema.ViewModels.DataModels.User;

public class UserView extends BaseView<User> {

    private User user;

    public UserView() {
        super("users", User.class);
    }


}


