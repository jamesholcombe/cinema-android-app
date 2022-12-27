package com.example.BigScreenCinema.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.BigScreenCinema.ViewModels.Tickets.AdultTicket;
import com.example.BigScreenCinema.ViewModels.Tickets.ChildTicket;
import com.example.BigScreenCinema.ViewModels.Tickets.TicketType;

public class LiveBookingView extends ViewModel {

    private final  MutableLiveData<Integer> numChildTickets = new MutableLiveData<Integer>(0);
    private final MutableLiveData<Integer> numAdultTickets = new MutableLiveData<Integer>(0);
    private final MutableLiveData<Integer> total = new MutableLiveData<Integer>(0);
    private final MutableLiveData<String> totalFormatted = new MutableLiveData<>("£0.00");


    public MutableLiveData<Integer> getTotal(){
        return total;
    }

    public void setNumAdultTickets(Integer newNumAdultTickets) {
        numAdultTickets.setValue(newNumAdultTickets);
        setTotal(newNumAdultTickets, this.numChildTickets.getValue());
    }

    public MutableLiveData<Integer> getNumAdultTickets() {
        return numAdultTickets;
    }

    public MutableLiveData<Integer> getNumChildTickets() {
        return numChildTickets;
    }

    public void setNumChildTickets(Integer newNumChildTickets) {

        numChildTickets.setValue(newNumChildTickets);
        setTotal(this.numAdultTickets.getValue(),newNumChildTickets);
    }

    private void setTotal(Integer adultTickets, Integer childTickets) {

        int newTotal =  (adultTickets * AdultTicket.getPrice() ) + ( childTickets * ChildTicket.getPrice());
        total.setValue(newTotal);
        totalFormatted.setValue(TicketType.getFormattedPrice(newTotal));

    }

    public MutableLiveData<String> getTotalFormatted() {
        return totalFormatted;
    }






}
