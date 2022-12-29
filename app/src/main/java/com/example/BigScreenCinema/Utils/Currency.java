package com.example.BigScreenCinema.Utils;

public class Currency {
    public static String getFormattedPrice(int price) {
        return String.format("£%d.%02d", price / 100, price % 100);
    }
}

