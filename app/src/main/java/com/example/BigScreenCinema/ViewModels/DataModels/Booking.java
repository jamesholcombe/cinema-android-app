package com.example.BigScreenCinema.ViewModels.DataModels;

import com.example.BigScreenCinema.ViewModels.DataModels.Tickets.AdultTicket;
import com.example.BigScreenCinema.ViewModels.DataModels.Tickets.ChildTicket;

import java.util.List;
import java.util.UUID;

public class Booking extends Base {

    List<AdultTicket> adultTickets;
    List<ChildTicket> childTickets;
    Screening screening;

    String QRCodeUri;


    public Booking() {
        super();

    }

    public Booking(String id, Screening screening, List<AdultTicket> adultTickets, List<ChildTicket> childTickets, String QRCodeUri) {
        super(id);
        this.screening = screening;
        this.adultTickets = adultTickets;
        this.childTickets = childTickets;
        this.QRCodeUri = QRCodeUri;
    }

    public void generateQRCode() {
        String uuid = UUID.randomUUID().toString();
        this.QRCodeUri = "https://api.qrserver.com/v1/create-qr-code/?data=" + uuid + "&size=200x200";
    }

    public String getQRCodeUri() {
        return QRCodeUri;
    }

    public void setQRCodeUri(String QRCodeUri) {
        this.QRCodeUri = QRCodeUri;

    }

    public List<AdultTicket> getAdultTickets() {
        return adultTickets;
    }

    public void setAdultTickets(List<AdultTicket> adultTickets) {
        this.adultTickets = adultTickets;
    }

    public List<ChildTicket> getChildTickets() {
        return childTickets;
    }

    public void setChildTickets(List<ChildTicket> childTickets) {
        this.childTickets = childTickets;
    }

    public Screening getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening = screening;
    }

}
