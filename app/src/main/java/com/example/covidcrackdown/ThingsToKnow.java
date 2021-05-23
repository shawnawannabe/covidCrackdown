package com.example.covidcrackdown;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ThingsToKnow#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ThingsToKnow extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ThingsToKnow() {
        // Required empty public constructor
    }

    public static ThingsToKnow newInstance(String param1, String param2) {
        ThingsToKnow fragment = new ThingsToKnow();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    RecyclerView recyclerView;
    RecyclerAdapter recyclerAdapter;
    String monthNames[] = {"Jan", "Feb","Jan", "Feb","Jan", "Feb","Jan", "Feb","Jan", "Feb","Jan", "Feb","Jan", "Feb",
            "Jan", "Feb","Jan", "Feb","Jan", "Feb","Jan", "Feb","Jan", "Feb","Jan", "Feb","Jan", "Feb"};


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