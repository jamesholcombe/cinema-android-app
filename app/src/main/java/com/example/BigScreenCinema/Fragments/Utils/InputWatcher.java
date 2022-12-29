package com.example.BigScreenCinema.Fragments.Utils;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.fragment.app.FragmentActivity;

abstract public class InputWatcher implements TextWatcher {

    public abstract void onValidated(String text);

    public int getMaxChars() {
        return 0;
    }

    public abstract EditText getEditText();

    public abstract FragmentActivity getActivity();

    @Override
    final public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    final public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    private void unFocus() {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(getEditText().getWindowToken(), 0);
        getEditText().clearFocus();
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (getMaxChars() == 0) {
            String text = s.toString();
            onValidated(text);
        } else if (s.length() == getMaxChars()) {

            unFocus();
            String text = s.toString();
            onValidated(text);
        }

    }


}
