package com.example.windows.bookinghotel;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class History extends Fragment {


    public History() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);

        TextView textView = (TextView) view.findViewById(R.id.history);
        SharedPreferences sharedPreferences = getContext().getSharedPreferences(BookingActivity.prefsBooking,0);
        textView.setText(sharedPreferences.getString("booking",""));

        return view;
    }

}
