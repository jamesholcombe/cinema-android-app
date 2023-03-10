package com.example.BigScreenCinema.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.BigScreenCinema.ViewModels.DataModels.User;
import com.google.firebase.auth.FirebaseUser;

public class GlobalDataView extends ViewModel {
    private final MutableLiveData<User> user = new MutableLiveData<>();
    private final MutableLiveData<FirebaseUser> firebaseUser = new MutableLiveData<>();
    private final MutableLiveData<String> fragmentName = new MutableLiveData<>("Big Screen Cinema");

    public MutableLiveData<String> getFragmentName() {
        return fragmentName;
    }

    public void setFragmentName(String fragmentName) {
        this.fragmentName.setValue(fragmentName);
    }

    public MutableLiveData<User> getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user.setValue(user);
    }

    public MutableLiveData<FirebaseUser> getFirebaseUser() {
        return firebaseUser;
    }

    public void setFirebaseUser(FirebaseUser firebaseUser) {
        this.firebaseUser.setValue(firebaseUser);
    }

}
