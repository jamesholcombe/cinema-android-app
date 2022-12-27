package com.example.BigScreenCinema.ViewModels;


import com.example.BigScreenCinema.ViewModels.Tickets.TicketType;

public class Ticket extends Base {

    private final TicketType ticketType;
    private final Booking booking;
    private final String QRCodeUri;


    public Ticket(String id, TicketType ticketType, String QRCodeUri, Booking booking) {
        super(id);
        this.ticketType = ticketType;
        this.QRCodeUri = QRCodeUri;
        this.booking = booking;

    }
}
