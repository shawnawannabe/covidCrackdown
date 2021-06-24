package com.example.covidcrackdown.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.covidcrackdown.R;
import com.example.covidcrackdown.adapters.RecyclerAdapter;

public class ThingsToKnowFragment extends Fragment {

    public ThingsToKnowFragment() {
        // Required empty public constructor
    }

    public static ThingsToKnowFragment newInstance(String param1, String param2) {
        ThingsToKnowFragment fragment = new ThingsToKnowFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    String monthNames[] = {"22 Jun 2021 6.30PM", "21 Jun 2021 6.30PM","20 Jun 2021 6.30PM"};
    String title[] = {"COVID-19 IN MALAYSIA AS OF 22 JUNE 2021",
            "COVID-19 IN MALAYSIA AS OF 21 JUNE 2021",
            "COVID-19 IN MALAYSIA AS OF 20 JUNE 2021"};
    int profileImage[] = {R.drawable.cprc, R.drawable.cprc, R.drawable.cprc};
    int images[] = {R.drawable.jun_22, R.drawable.jun_21, R.drawable.jun_20};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_things_to_know, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager((Context) getActivity()));
        recyclerAdapter = new RecyclerAdapter((Context) getActivity(), monthNames, title, profileImage, images);
        recyclerView.setAdapter(recyclerAdapter);
        return view;
    }
}