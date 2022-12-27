package com.example.BigScreenCinema.ViewModels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.BigScreenCinema.ViewModels.Tickets.AdultTicket;
import com.example.BigScreenCinema.ViewModels.Tickets.ChildTicket;
import com.example.BigScreenCinema.ViewModels.Tickets.TicketType;

import org.checkerframework.checker.nullness.qual.NonNull;

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

    public void setNumChildTickets(Integer newNumChildTickets) {

        numChildTickets.setValue(newNumChildTickets);
        setTotal(this.numAdultTickets.getValue(),newNumChildTickets);
    }

    private void setTotal(Integer adultTickets, Integer childTickets) {

        Integer newTotal =  (adultTickets * AdultTicket.getPrice() ) + ( childTickets * ChildTicket.getPrice());
        total.setValue(newTotal);
        System.out.println("set toal ran" + TicketType.getFormattedPrice(newTotal));
        totalFormatted.setValue(TicketType.getFormattedPrice(newTotal));

    }

    public MutableLiveData<String> getTotalFormatted() {
        return totalFormatted;
    }






}
