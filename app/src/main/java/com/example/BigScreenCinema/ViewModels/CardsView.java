package com.example.BigScreenCinema.ViewModels;

import androidx.lifecycle.MutableLiveData;

import com.example.BigScreenCinema.ViewModels.DataModels.Card;
import com.example.BigScreenCinema.ViewModels.DataModels.Movie;
import com.example.BigScreenCinema.ViewModels.DataModels.Screening;
import com.example.BigScreenCinema.ViewModels.DataModels.User;
import com.google.firebase.firestore.CollectionReference;

public class CardsView extends BaseView<Card> {

    MutableLiveData<User> user = new MutableLiveData<>();
    public CardsView() {
        super("users", Card.class);
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
        return db.collection(collectionName).document(user.getValue().getId()).collection("cards");
    }
}
