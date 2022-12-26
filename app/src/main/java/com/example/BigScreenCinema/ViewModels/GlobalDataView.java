package com.example.BigScreenCinema.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.firebase.auth.FirebaseUser;

public class GlobalDataView extends ViewModel {
    private MutableLiveData<User> user = new MutableLiveData<>();
    private MutableLiveData<FirebaseUser> firebaseUser = new MutableLiveData<>();


    public User getUser() {
        return user.getValue();
    }

    public void setUser(User user) {
        this.user.setValue(user);
    }

    private void createOrRetrieveUser() {

    }

    public FirebaseUser getFirebaseUser() {
        return firebaseUser.getValue();
    }

    public void setFirebaseUser(FirebaseUser firebaseUser) {
        this.firebaseUser.setValue(firebaseUser);
    }

}