package com.example.covidcrackdown.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.covidcrackdown.models.MySingleton;
import com.example.covidcrackdown.R;
import com.example.covidcrackdown.models.Faqs;
import com.example.covidcrackdown.models.Location;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;

public class MenuActivity extends Fragment {

    private FirebaseAuth mAuth;
    private TextView hotSpotTextView;
    private TextView faqsTextView;
    private TextView statsTextView;
    private TextView logoutTextView;
    private DatabaseReference mDatebase;

    public MenuActivity() {
        // Required empty public constructor
    }

    public static MenuActivity newInstance(String param1, String param2) {
        MenuActivity fragment = new MenuActivity();
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
        Intent intent = new Intent(getActivity(), HotSpotActivity.class);
        startActivity(intent);
    }

    private void openFaqsActivity() {
        Intent intent = new Intent(getActivity(), FaqsActivity.class);
        startActivity(intent);
    }

    public void openStatsActivity(){
        Intent intent = new Intent(getActivity(), StatisticActivity.class);
        startActivity(intent);
    }

    public void openLogoutActivity(){
        Intent intent = new Intent(getActivity(), SignInActivity.class);
        killActivity();
        startActivity(intent);
    }

    void killActivity(){
        getActivity().finish();
    }

}