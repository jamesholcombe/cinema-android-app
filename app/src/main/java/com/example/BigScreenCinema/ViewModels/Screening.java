package com.example.BigScreenCinema.ViewModels;

import com.google.type.DateTime;

enum Screen {
    A,
    B,
    C,
    D,
    E,
    F
}

public class Screening extends Base {
    private DateTime datetime;
    private Screen screen;

    public Screening( Screen screen, DateTime dateTime, String id) {
        super(id);
        this.datetime = dateTime;
        this.screen = screen;
    }

public Screening (){
        super();
}
    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }

    public DateTime getDatetime() {
        return datetime;
    }

    public void setDatetime(DateTime datetime) {
        this.datetime = datetime;
    }
}
