package com.example.BigScreenCinema.ViewModels.DataModels.Tickets;


public class TicketType {
    private static int price;

    public static int getPrice() {
        return price;

    }

    public static String getFormattedPrice(int priceVal) {
        String priceStr = new String(String.valueOf(priceVal));
        StringBuilder builder = new StringBuilder(priceStr);
        builder.insert(priceStr.length() -2 , '.');
        builder.insert(0,"£");
        return builder.toString();

    }


}

