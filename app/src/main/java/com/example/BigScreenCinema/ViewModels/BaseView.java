package com.example.BigScreenCinema.ViewModels;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.BigScreenCinema.R;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Map;
import java.util.Objects;


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
    private final Gson gson = new Gson();

    public MutableLiveData<ArrayList<T>> getItems() {
        if (!isLoaded) {
            loadItems();
        }
        return items;
    }

    public FirebaseFirestore getDb() {
        return db;
    }

    private CollectionReference getReference() {
        return db.collection(collectionName);
    }

    protected void loadItems() {
        getReference()
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        ArrayList<T> newItems = new ArrayList<T>();
                        for (QueryDocumentSnapshot document : task.getResult()) {

                            Map<String, Object> data = document.getData();
                            String json = gson.toJson(data);
                            T object = gson.fromJson(json, klass);
                            newItems.add(object);

                        }
                        items.setValue(newItems);
                        isLoaded = true;
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }});
    }

}

