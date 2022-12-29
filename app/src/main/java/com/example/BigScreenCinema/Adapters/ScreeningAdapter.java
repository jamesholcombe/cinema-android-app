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
import com.example.BigScreenCinema.ViewModels.DataModels.Screening;

import java.util.ArrayList;

public class ScreeningAdapter extends ArrayAdapter<Screening> {

    public ScreeningAdapter(Context context, ArrayList<Screening> screenings) {
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
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.screening_spinner_item, parent, false);
        }
        Screening currentItem = getItem(position);
        TextView textViewName = convertView.findViewById(R.id.screening_text_date);
        if (currentItem != null) {
            textViewName.setText(currentItem.getDateString());
        }

        return convertView;
    }
}
