package com.example.BigScreenCinema.Utils;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.InputStream;

public class DownloadImageFromUri extends DownloadImage {
    private final ImageView imageView;

    public DownloadImageFromUri(ImageView imageView) {
        this.imageView = imageView;

    }

    @Override
    protected void onPostExecute(Bitmap result) {
        imageView.setImageBitmap(result);
    }
}