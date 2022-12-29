package com.example.BigScreenCinema.ViewModels;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.BigScreenCinema.ViewModels.DataModels.Base;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;


class BaseView<T extends Base> extends ViewModel {

    private final Class<? extends T> klass;

    BaseView(String collectionName, Class<? extends T> klass) {
        db = FirebaseFirestore.getInstance();
        this.klass = klass;
        this.collectionName = collectionName;

    }

    protected final FirebaseFirestore db;
    protected final String collectionName;
    private final MutableLiveData<ArrayList<T>> items = new MutableLiveData<>(new ArrayList<T>());
    private boolean isLoaded = false;


    public MutableLiveData<ArrayList<T>> getItems() {
        if (!isLoaded) {
            loadItems();
        }
        return items;
    }

    public FirebaseFirestore getDb() {
        return db;
    }

    protected CollectionReference getReference() {
        return db.collection(collectionName);
    }

    public void createItem(T item) {
        getReference().add(item);
    }

    protected void loadItems() {
        getReference()
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        ArrayList<T> newItems = new ArrayList<T>();
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            T object = document.toObject(klass);
                            object.setId(document.getId());
                            newItems.add(object);

                        }
                        items.setValue(newItems);
                        isLoaded = true;
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }});
    }

}

