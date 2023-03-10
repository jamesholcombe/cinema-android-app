package com.example.BigScreenCinema.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.BigScreenCinema.ViewModels.DataModels.Screening;
import com.example.BigScreenCinema.ViewModels.DataModels.Tickets.AdultTicket;
import com.example.BigScreenCinema.ViewModels.DataModels.Tickets.ChildTicket;
import com.example.BigScreenCinema.ViewModels.DataModels.Tickets.TicketType;

public class LiveBookingView extends ViewModel {

    private final MutableLiveData<Integer> numChildTickets = new MutableLiveData<Integer>(0);
    private final MutableLiveData<Integer> numAdultTickets = new MutableLiveData<Integer>(0);
    private final MutableLiveData<Integer> total = new MutableLiveData<Integer>(0);
    private final MutableLiveData<String> totalFormatted = new MutableLiveData<>("£0.00");
    private final MutableLiveData<Screening> screening = new MutableLiveData<Screening>();
    private final MutableLiveData<Boolean> useSavedCard = new MutableLiveData<Boolean>(false);

    public MutableLiveData<Integer> getTotal() {
        return total;
    }

    public MutableLiveData<Boolean> getUseSavedCard() {
        return useSavedCard;
    }

    public void setUseSavedCard(boolean useSavedCard) {
        this.useSavedCard.setValue(useSavedCard);
    }

    public MutableLiveData<Screening> getScreening() {
        return screening;
    }

    public void setScreening(Screening screening) {
        this.screening.setValue(screening);
    }

    public MutableLiveData<Integer> getNumAdultTickets() {
        return numAdultTickets;
    }

    public void setNumAdultTickets(Integer newNumAdultTickets) {
        this.numAdultTickets.setValue(newNumAdultTickets);
        setTotal(newNumAdultTickets, this.numChildTickets.getValue());
    }

    public MutableLiveData<Integer> getNumChildTickets() {
        return numChildTickets;
    }

    public void setNumChildTickets(Integer newNumChildTickets) {

        this.numChildTickets.setValue(newNumChildTickets);
        setTotal(this.numAdultTickets.getValue(), newNumChildTickets);
    }

    private void setTotal(Integer adultTickets, Integer childTickets) {

        int newTotal = (adultTickets * AdultTicket.getPrice()) + (childTickets * ChildTicket.getPrice());
        total.setValue(newTotal);
        totalFormatted.setValue(TicketType.getFormattedPrice(newTotal));

    }

    public MutableLiveData<String> getTotalFormatted() {
        return totalFormatted;
    }


}
