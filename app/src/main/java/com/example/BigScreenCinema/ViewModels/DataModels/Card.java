package com.example.BigScreenCinema.ViewModels.DataModels;

public class Card extends Base {
    private long cardNumber = 0;
    private int expiryMonth = 0;
    private int expiryYear = 0;
    private int cvv = 0;
    private String cardHolderName;
    private String postcode;

    public Card(long cardNumber, int expiryMonth, int expiryYear, int cvv, String cardHolderName, String postcode) {
        this.cardNumber = cardNumber;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.cvv = cvv;
        this.cardHolderName = cardHolderName;
        this.postcode = postcode;
    }

    public Card() {

    }

    public long getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(Long newCardNumber) {
        this.cardNumber = newCardNumber;
    }

    public int getExpiryMonth() {
        return expiryMonth;
    }

    public void setExpiryMonth(int expiryMonth) {
        this.expiryMonth = expiryMonth;
    }

    public int getExpiryYear() {
        return expiryYear;
    }

    public void setExpiryYear(int expiryYear) {
        this.expiryYear = expiryYear;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public String getCardHolderName() {
        return cardHolderName;
    }

    public void setCardHolderName(String cardHolderName) {
        this.cardHolderName = cardHolderName;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public boolean isValid() {
        return cardNumber != 0 && expiryMonth != 0 && expiryYear != 0 && cvv != 0 && cardHolderName != null && postcode != null;
    }

}
