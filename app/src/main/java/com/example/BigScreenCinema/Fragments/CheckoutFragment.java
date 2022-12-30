package com.example.BigScreenCinema.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.BigScreenCinema.Adapters.CardAdapter;
import com.example.BigScreenCinema.Fragments.Utils.InputWatcher;
import com.example.BigScreenCinema.R;
import com.example.BigScreenCinema.ViewModels.BookingView;
import com.example.BigScreenCinema.ViewModels.CardsView;
import com.example.BigScreenCinema.ViewModels.CheckoutView;
import com.example.BigScreenCinema.ViewModels.DataModels.Booking;
import com.example.BigScreenCinema.ViewModels.DataModels.Card;
import com.example.BigScreenCinema.ViewModels.DataModels.Movie;
import com.example.BigScreenCinema.ViewModels.DataModels.Screening;
import com.example.BigScreenCinema.ViewModels.DataModels.Tickets.AdultTicket;
import com.example.BigScreenCinema.ViewModels.DataModels.Tickets.ChildTicket;
import com.example.BigScreenCinema.ViewModels.GlobalDataView;
import com.example.BigScreenCinema.ViewModels.LiveBookingView;
import com.example.BigScreenCinema.ViewModels.SelectedMovieView;
import com.example.BigScreenCinema.databinding.FragmentCheckoutBinding;

import java.util.ArrayList;
import java.util.Arrays;

public class CheckoutFragment extends Fragment {
    private FragmentCheckoutBinding binding;
    private LiveBookingView liveBookingView;
    private SelectedMovieView selectedMovieView;
    private CheckoutView checkoutView;
    private GlobalDataView globalDataView;
    private CardsView cardsView;
    private BookingView bookingView;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCheckoutBinding.inflate(inflater, container, false);
        liveBookingView = new ViewModelProvider(requireActivity()).get(LiveBookingView.class);
        selectedMovieView = new ViewModelProvider(requireActivity()).get(SelectedMovieView.class);
        globalDataView = new ViewModelProvider(requireActivity()).get(GlobalDataView.class);
        cardsView = new ViewModelProvider(requireActivity()).get(CardsView.class);
        bookingView = new ViewModelProvider(requireActivity()).get(BookingView.class);
        checkoutView = new ViewModelProvider(this).get(CheckoutView.class);


        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        cardsView.getUser().observe(getViewLifecycleOwner(), user -> {
                    if (user != null) {
                        cardsView.getItems().observe(getViewLifecycleOwner(), new Observer<ArrayList<Card>>() {
                            @Override
                            public void onChanged(ArrayList<Card> cards) {
                                binding.spinnerSavedCards.setAdapter(new CardAdapter(getContext(), cards));
                            }
                        });


                    }
                }

        );


        binding.spinnerSavedCards.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Card card = (Card) parent.getItemAtPosition(position);
                checkoutView.setSelectedCard(card);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        globalDataView.setFragmentName("Checkout");
        validateUseNewCard();
        liveBookingView.getTotalFormatted().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String price) {

                binding.checkoutTotal.setText(price);
            }
        });
        liveBookingView.getNumChildTickets().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer num) {

                binding.textNumChildCheckout.setText(String.valueOf(num));
            }
        });
        liveBookingView.getNumAdultTickets().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable Integer num) {

                binding.textNumAdultsCheckout.setText(String.valueOf(num));
            }
        });

        selectedMovieView.getMovie().observe(getViewLifecycleOwner(), new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                binding.textViewMovieTitle.setText(movie.getTitle());
            }
        });
        liveBookingView.getScreening().observe(getViewLifecycleOwner(), new Observer<Screening>() {
            @Override
            public void onChanged(Screening screening) {
                binding.textDate.setText(screening.getDateString());
            }
        });
        binding.buttonCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        binding.radioGroupPaymentMethod.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.radio_button_new_card) {
                    binding.layoutChooseCard.setVisibility(View.GONE);
                    binding.cardDetailsTable.setVisibility(View.VISIBLE);
                    checkoutView.setUseSavedCard(false);

                } else if (checkedId == R.id.radio_button_saved_card) {
                    binding.layoutChooseCard.setVisibility(View.VISIBLE);
                    binding.cardDetailsTable.setVisibility(View.GONE);
                    checkoutView.setUseSavedCard(true);
                }


            }
        });

        binding.checkoutCardCvs.addTextChangedListener(new FragmentInputWatcher() {
            @Override
            public void onValidated(String text) {
                int cvv = Integer.parseInt(text);
                checkoutView.setNewCardCvv(cvv);
            }

            @Override
            public int getMaxChars() {
                return 3;
            }

            @Override
            public EditText getEditText() {
                return binding.checkoutCardCvs;
            }

        });

        binding.editTextCardholderPostcode.addTextChangedListener(new FragmentInputWatcher() {
            @Override
            public void onValidated(String text) {
                checkoutView.setNewCardPostcode(text);
            }

            @Override
            public EditText getEditText() {
                return binding.editTextCardholderPostcode;
            }

        });

        binding.editTextCardholderName.addTextChangedListener(new FragmentInputWatcher() {
            @Override
            public void onValidated(String text) {
                checkoutView.setNewCardHolderName(text);
            }

            @Override
            public EditText getEditText() {
                return binding.editTextCardholderName;
            }

        });


        binding.checkoutCardNumber.addTextChangedListener(new FragmentInputWatcher() {


            @Override
            public void onValidated(String text) {
                long cardNumber = Long.parseLong(text);
                checkoutView.setNewCardNumber(cardNumber);
            }

            @Override
            public int getMaxChars() {
                return 16;
            }

            @Override
            public EditText getEditText() {
                return binding.checkoutCardNumber;
            }
        });

        binding.checkoutCardExpiryMonth.addTextChangedListener(new FragmentInputWatcher() {
            @Override
            public void onValidated(String text) {
                int month = Integer.parseInt(text);
                checkoutView.setNewCardExpiryMonth(month);
            }

            @Override
            public int getMaxChars() {
                return 2;
            }

            @Override
            public EditText getEditText() {
                return binding.checkoutCardExpiryMonth;
            }
        });

        binding.checkoutCardExpiryYear.addTextChangedListener(new FragmentInputWatcher() {
            @Override
            public void onValidated(String text) {
                int year = Integer.parseInt(text);
                checkoutView.setNewCardExpiryYear(year);
            }

            @Override
            public int getMaxChars() {
                return 2;
            }

            @Override
            public EditText getEditText() {
                return binding.checkoutCardExpiryYear;
            }
        });


        checkoutView.getSelectedCard().observe(getViewLifecycleOwner(), new Observer<Card>() {
            @Override
            public void onChanged(Card card) {
                validateUseNewCard();
            }
        });

        checkoutView.getUseSavedCard().observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean useSavedCard) {
                validateUseNewCard();
            }
        });


        checkoutView.getNewCard().observe(getViewLifecycleOwner(), new Observer<Card>() {
            @Override
            public void onChanged(Card card) {
                validateUseNewCard();

            }

        });

        //validation has already been done.
        binding.buttonCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (binding.radioGroupPaymentMethod.getCheckedRadioButtonId() == R.id.radio_button_new_card && binding.checkoutSaveCard.isChecked()) {
                    Card card = checkoutView.getNewCard().getValue();
                    cardsView.createItem(card, true);

                }
                NavHostFragment.findNavController(CheckoutFragment.this).navigate(R.id.action_checkoutFragment_to_completeFragment);

                Booking booking = new Booking();
                Screening screening = liveBookingView.getScreening().getValue();
                Movie movieWithoutScreenings = selectedMovieView.getMovie().getValue();

                // we don't want to save the screenings in the booking
                movieWithoutScreenings.setScreenings(null);
                screening.setMovie(movieWithoutScreenings);
                booking.setScreening(screening);

                int numAdults = liveBookingView.getNumAdultTickets().getValue();
                int numChildren = liveBookingView.getNumChildTickets().getValue();

                AdultTicket[] adultTickets = new AdultTicket[numAdults];
                ChildTicket[] childTickets = new ChildTicket[numChildren];

                for (int i = 0; i < numAdults; i++) {
                    adultTickets[i] = new AdultTicket();
                }

                for (int i = 0; i < numChildren; i++) {
                    childTickets[i] = new ChildTicket();
                }

                booking.generateQRCode();
                booking.setAdultTickets(Arrays.asList(adultTickets));
                booking.setChildTickets(Arrays.asList(childTickets));
                bookingView.createItem(booking, true);
            }
        });
    }

    public void validateUseNewCard() {
        if (Boolean.TRUE.equals(checkoutView.getUseSavedCard().getValue())) {
            toggleCheckoutButton(checkoutView.getSelectedCard().getValue() != null);
        } else {
            toggleCheckoutButton(checkoutView.getNewCard().getValue().isValid());
        }
    }

    public void toggleCheckoutButton(boolean enabled) {
        binding.buttonCheckout.setEnabled(enabled);
        System.out.println("Checkout button enabled: " + enabled);
        if (enabled) {
            binding.buttonCheckout.setBackgroundColor(getResources().getColor(R.color.purple_500, null));
        } else {
            binding.buttonCheckout.setBackgroundColor(getResources().getColor(R.color.purple_200, null));
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    abstract private class FragmentInputWatcher extends InputWatcher {
        @Override
        public FragmentActivity getActivity() {
            return requireActivity();
        }


    }


}
