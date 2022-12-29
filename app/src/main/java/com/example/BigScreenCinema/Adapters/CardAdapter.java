package com.example.BigScreenCinema.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.BigScreenCinema.R;
import com.example.BigScreenCinema.ViewModels.DataModels.Card;

import java.util.ArrayList;

public class CardAdapter extends ArrayAdapter<Card> {

    public CardAdapter(Context context, ArrayList<Card> screenings) {
        super(context, 0, screenings);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return initView(position, convertView, parent);
    }

    private View initView(int position, View convertView, ViewGroup parent) {
        // It is used to set our custom view.
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.card_spinner_item, parent, false);
        }
        Card currentItem = getItem(position);
        TextView textViewCardNumber = convertView.findViewById(R.id.card_number_spinner_text);
        TextView textViewCardExpiry = convertView.findViewById(R.id.card_expiry_spinner_text);

        if (currentItem != null) {
            textViewCardNumber.setText(currentItem.getRedactedCardNumber());
            textViewCardExpiry.setText(currentItem.getExpiryDate());

        }

        return convertView;
    }
}
