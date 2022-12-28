package com.example.BigScreenCinema.ViewModels.DataModels;

import com.example.BigScreenCinema.ViewModels.DataModels.Tickets.TicketType;

public class Booking extends Base {

    TicketType[] tickets;
    Screening screening;
    public Booking(String id) {
        super(id);
    }
}
