package com.example.BigScreenCinema.ViewModels.DataModels.Tickets;


import android.graphics.Bitmap;

import com.example.BigScreenCinema.Utils.DownloadImage;

public class TicketType {
    private static int price;
    private final boolean isUsed = false;
    private String QRCodeUri;
    private Bitmap QRCode;

    TicketType() {

    }

    public static int getPrice() {
        return price;

    }


    public static String getFormattedPrice(int priceVal) {
        String priceStr = new String(String.valueOf(priceVal));
        StringBuilder builder = new StringBuilder(priceStr);
        builder.insert(priceStr.length() - 2, '.');
        builder.insert(0, "£");
        return builder.toString();

    }

    public String getQRCodeUri() {
        return QRCodeUri;
    }

    public void setQRCodeUri(String QRCodeUri) {
        this.QRCodeUri = QRCodeUri;
        DownloadImageTask downloadImageTask = new DownloadImageTask();
        downloadImageTask.execute(QRCodeUri);
    }

    private class DownloadImageTask extends DownloadImage {
        @Override
        protected void onPostExecute(Bitmap result) {
            QRCode = result;
        }

    }


}

