package com.example.BigScreenCinema.Models;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import android.graphics.Bitmap;
import android.net.Uri;

public class Movie {
    private String title;
    private String descriptionShort;
    private String imageUri;
    private String descriptionLong;

    public Movie(String title, String description, String imageUri, String descriptionLong) {
        this.title = title;
        this.descriptionShort = description;
        this.imageUri = imageUri;
        this.descriptionLong = descriptionLong;
    }

    public Movie (Map movieMap) {
        this.title = (String) movieMap.get("title");
        this.descriptionShort = (String) movieMap.get("descriptionShort");
        this.imageUri = (String) movieMap.get("imageUri");
        this.descriptionLong = (String) movieMap.get("descriptionLong");
    }

    public String getTitle() {
        return title;
    }

    public String getDescriptionShort() {
        return descriptionShort;
    }

    public String getImageUri() {
        return imageUri;
    }

    public String getDescriptionLong() {
        return descriptionLong;
    }


}
