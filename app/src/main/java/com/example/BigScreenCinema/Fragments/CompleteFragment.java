package com.example.BigScreenCinema.Fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.BigScreenCinema.R;
import com.example.BigScreenCinema.databinding.FragmentCompleteBinding;

public class CompleteFragment extends Fragment {

    private FragmentCompleteBinding binding;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentCompleteBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonNavHome.setOnClickListener(v -> {
            NavHostFragment.findNavController(CompleteFragment.this).navigate(R.id.action_completeFragment_to_FirstFragment);
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
