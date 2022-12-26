package com.example.BigScreenCinema.ViewModels;

import static android.content.ContentValues.TAG;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Map;


class BaseView<T extends Base> extends ViewModel {

    private final Class<? extends T> klass;

    BaseView(String collectionName, Class<? extends T> klass) {
        db = FirebaseFirestore.getInstance();
        this.klass = klass;
        this.collectionName = collectionName;

    }

    private final FirebaseFirestore db;
    private final String collectionName;
    private final ArrayList<T> items = new ArrayList<T>();
    private boolean isLoaded = false;
    private final Gson gson = new Gson();

    public ArrayList<T> getItems() {
        if (!isLoaded) {
            loadItems();
        }
        System.out.println("items: " + items);
        return items;
    }

    public FirebaseFirestore getDb() {
        return db;
    }

    private void loadItems() {
        db.collection(collectionName)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {

                            Map<String, Object> data = document.getData();
                            String json = gson.toJson(data);
                            T object = gson.fromJson(json, klass);
                            items.add(object);

                        }
                        isLoaded = true;
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }});
    }

}

