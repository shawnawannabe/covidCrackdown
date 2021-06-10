package com.example.covidcrackdown;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ThingsToKnow extends Fragment {

    public ThingsToKnow() {
        // Required empty public constructor
    }

    public static ThingsToKnow newInstance(String param1, String param2) {
        ThingsToKnow fragment = new ThingsToKnow();
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
    String monthNames[] = {"Jan 2020", "Feb 2020","March 2020", "April 2020"};


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_things_to_know, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager((Context) getActivity()));
        recyclerAdapter = new RecyclerAdapter((Context) getActivity(), monthNames);
        recyclerView.setAdapter(recyclerAdapter);
        return view;
    }
}