package com.example.BigScreenCinema.ViewModels.DataModels.Tickets;


import android.graphics.Bitmap;

import com.example.BigScreenCinema.Utils.DownloadImage;

import java.util.UUID;

public class TicketType {
    private static int price;
    private String QRCodeUri;
    private Bitmap QRCode;
    private final boolean isUsed = false;

    TicketType() {

    }

    public static int getPrice() {
        return price;

    }

    public Bitmap getQRCode() {
        return QRCode;
    }

    public void generateQRCode() {
        String uuid = UUID.randomUUID().toString();
        this.QRCodeUri = "https://api.qrserver.com/v1/create-qr-code/?data=" + uuid + "&size=100x100";

    }

    public static String getFormattedPrice(int priceVal) {
        String priceStr = new String(String.valueOf(priceVal));
        StringBuilder builder = new StringBuilder(priceStr);
        builder.insert(priceStr.length() - 2, '.');
        builder.insert(0, "£");
        return builder.toString();

    }

    public void setQRCodeUri(String QRCodeUri) {
        this.QRCodeUri = QRCodeUri;
        DownloadImageTask downloadImageTask = new DownloadImageTask();
        downloadImageTask.execute(QRCodeUri);
    }

    public String getQRCodeUri() {
        return QRCodeUri;
    }

    private class DownloadImageTask extends DownloadImage {
        @Override
        protected void onPostExecute(Bitmap result) {
            QRCode = result;
        }

    }


}

