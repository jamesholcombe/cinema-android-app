package com.example.BigScreenCinema.Utils;


import android.graphics.Bitmap;
import android.widget.ImageView;

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