package com.example.BigScreenCinema.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.BigScreenCinema.ViewModels.DataModels.Card;

public class CheckoutView extends ViewModel {
    private final MutableLiveData<Boolean> useSavedCard = new MutableLiveData<Boolean>(false);
    private final MutableLiveData<Card> selectedCard = new MutableLiveData<Card>();
    private final MutableLiveData<Long> newCardNumber = new MutableLiveData<>();
    private final MutableLiveData<String> newCardHolderName = new MutableLiveData<String>();
    private final MutableLiveData<Integer> newCardExpiryMonth = new MutableLiveData<Integer>();
    private final MutableLiveData<Integer> newCardExpiryYear = new MutableLiveData<Integer>();
    private final MutableLiveData<Integer> newCardCvv = new MutableLiveData<Integer>();
    private final MutableLiveData<String> newCardPostcode = new MutableLiveData<String>();
    private final MutableLiveData<Boolean> newCardSave = new MutableLiveData<Boolean>(false);
    private final MutableLiveData<Card> newCard = new MutableLiveData<Card>(new Card());

    public MutableLiveData<Card> getNewCard() {
        return newCard;
    }

    public MutableLiveData<Boolean> getNewCardSave() {
        return newCardSave;
    }

    public void setNewCardSave(Boolean newCardSave) {
        this.newCardSave.setValue(newCardSave);
    }

    public MutableLiveData<Boolean> getUseSavedCard() {
        return useSavedCard;
    }

    public void setUseSavedCard(boolean useSavedCard) {
        this.useSavedCard.setValue(useSavedCard);
    }

    public MutableLiveData<Card> getSelectedCard() {
        return selectedCard;
    }

    public void setSelectedCard(Card selectedCard) {
        this.selectedCard.setValue(selectedCard);
    }

    public MutableLiveData<Long> getNewCardNumber() {
        return newCardNumber;
    }

    public void setNewCardNumber(Long newCardNumber) {
        this.newCardNumber.setValue(newCardNumber);
        Card card = this.newCard.getValue();
        card.setCardNumber(newCardNumber);
        this.newCard.setValue(card);
    }

    public MutableLiveData<String> getNewCardHolderName() {
        return newCardHolderName;
    }

    public void setNewCardHolderName(String newCardHolderName) {
        this.newCardHolderName.setValue(newCardHolderName);
        Card card = this.newCard.getValue();
        card.setCardHolderName(newCardHolderName);
        this.newCard.setValue(card);
    }

    public MutableLiveData<Integer> getNewCardExpiryMonth() {
        return newCardExpiryMonth;
    }

    public void setNewCardExpiryMonth(Integer newCardExpiryMonth) {
        this.newCardExpiryMonth.setValue(newCardExpiryMonth);
        Card card = this.newCard.getValue();
        card.setExpiryMonth(newCardExpiryMonth);
        this.newCard.setValue(card);
    }

    public MutableLiveData<Integer> getNewCardExpiryYear() {
        return newCardExpiryYear;
    }

    public void setNewCardExpiryYear(Integer newCardExpiryYear) {

        this.newCardExpiryYear.setValue(newCardExpiryYear);
        Card card = this.newCard.getValue();
        card.setExpiryYear(newCardExpiryYear);
        this.newCard.setValue(card);
    }

    public MutableLiveData<Integer> getNewCardCvv() {
        return newCardCvv;
    }

    public void setNewCardCvv(Integer newCardCvv) {
        this.newCardCvv.setValue(newCardCvv);
        Card card = this.newCard.getValue();
        card.setCvv(newCardCvv);
        this.newCard.setValue(card);


    }

    public MutableLiveData<String> getNewCardPostcode() {
        return newCardPostcode;
    }

    public void setNewCardPostcode(String newCardPostcode) {
        this.newCardPostcode.setValue(newCardPostcode);
        Card card = this.newCard.getValue();
        card.setPostcode(newCardPostcode);
        this.newCard.setValue(card);
    }

    public void clear() {
        useSavedCard.setValue(false);
        selectedCard.setValue(null);
        newCardNumber.setValue(null);
        newCardHolderName.setValue(null);
        newCardExpiryMonth.setValue(null);
        newCardExpiryYear.setValue(null);
        newCardCvv.setValue(null);
        newCardPostcode.setValue(null);
    }

}
