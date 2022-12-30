package com.example.BigScreenCinema.ViewModels;

import androidx.lifecycle.MutableLiveData;

import com.example.BigScreenCinema.ViewModels.DataModels.Booking;
import com.example.BigScreenCinema.ViewModels.DataModels.User;
import com.google.firebase.firestore.CollectionReference;

public class BookingView extends BaseView<Booking> {

    MutableLiveData<User> user = new MutableLiveData<>();
    MutableLiveData<Booking> selectedBooking = new MutableLiveData<>();

    public BookingView() {
        super("users", Booking.class);
    }

    public MutableLiveData<Booking> getSelectedBooking() {
        return selectedBooking;
    }

    public void setSelectedBooking(Booking booking) {
        selectedBooking.setValue(booking);
    }

    public MutableLiveData<User> getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user.setValue(user);
        loadItems();
    }

    @Override
    protected CollectionReference getReference() {
        return db.collection(collectionName).document(user.getValue().getId()).collection("bookings");
    }
}
