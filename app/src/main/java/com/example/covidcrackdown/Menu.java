package com.example.covidcrackdown;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class Menu extends Fragment {

    private FirebaseAuth mAuth;
    private TextView hotSpotTextView;
    private TextView faqsTextView;
    private TextView statsTextView;
    private TextView logoutTextView;
    private DatabaseReference mDatebase;

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

        mAuth = FirebaseAuth.getInstance();
        mDatebase = FirebaseDatabase.getInstance().getReference();

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
                mAuth.signOut();
                openLogoutActivity();
            }
        });

        return view;
    }

    public void openHotSpotActivity(){
//        Intent intent = new Intent(getActivity(), HotSpot.class);
//        startActivity(intent);
        FirebaseUser user = mAuth.getCurrentUser();
        String userId = user.getUid();
        writeLocation(userId, "Domino", 11);
        Log.d(TAG, "openHotSpotActivity: check db");


    }

    private void openFaqsActivity() {
        Intent intent = new Intent(getActivity(), Faqs.class);
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

    public void writeLocation(String userId, String locationName, Integer time){
        //push() to generate node
        // getKey() to generate key
        String key = mDatebase.child("location").getKey();
//reference        String key = mDatebase.child("users").child("location").push().getKey();
        Location location = new Location(locationName, time);
        Map<String, Object> locationValues = location.toMap();
        Map<String, Object> childUpdates = new HashMap<>();

//        childUpdates.put("/users/" + userId + "/location/" + key, locationValues);
        childUpdates.put(key, locationValues);
        mDatebase.updateChildren(childUpdates);
    }
}