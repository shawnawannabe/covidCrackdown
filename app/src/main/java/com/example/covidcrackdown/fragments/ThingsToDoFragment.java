package com.example.covidcrackdown.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.covidcrackdown.R;

public class ThingsToDoFragment extends Fragment {

    public ThingsToDoFragment() {
        // Required empty public constructor
    }
    public static ThingsToDoFragment newInstance(String param1, String param2) {
        ThingsToDoFragment fragment = new ThingsToDoFragment();
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
        View view =  inflater.inflate(R.layout.fragment_things_to_do, container, false);

        return view;
    }
}