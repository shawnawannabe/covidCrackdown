package com.example.covidcrackdown;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Menu extends Fragment {

    TextView hotSpotTextView;
    TextView faqsTextView;
    TextView statsTextView;
    TextView logoutTextView;

    public Menu() {
        // Required empty public constructor
    }

    public static Menu newInstance(String param1, String param2) {
        Menu fragment = new Menu();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_menu, container, false);

        //insert on click listener
        hotSpotTextView = view.findViewById(R.id.hot_spot);
        faqsTextView = view.findViewById(R.id.faqs);
        statsTextView = view.findViewById(R.id.stats);
        logoutTextView = view.findViewById(R.id.logout);

        hotSpotTextView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openHotSpotActivity();
            }
        });

        faqsTextView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openFaqsActivity();
            }
        });

        statsTextView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                openStatsActivity();
            }
        });

        logoutTextView.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Toast.makeText(getActivity(), "Signed out successfully", Toast.LENGTH_SHORT).show();

                openLogoutActivity();
            }
        });

        return view;
    }

    private void openFaqsActivity() {
        Intent intent = new Intent(getActivity(), Faqs.class);
        startActivity(intent);
    }

    public void openHotSpotActivity(){
        Intent intent = new Intent(getActivity(), HotSpot.class);
        startActivity(intent);
    }

    public void openStatsActivity(){
        Intent intent = new Intent(getActivity(), Statistic.class);
        startActivity(intent);
    }

    public void openLogoutActivity(){
        Intent intent = new Intent(getActivity(), SignIn.class);
        killActivity();
        startActivity(intent);
    }

    void killActivity(){
        getActivity().finish();
    }
}