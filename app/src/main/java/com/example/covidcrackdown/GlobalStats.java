package com.example.covidcrackdown;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class GlobalStats extends Fragment {

    private TextView confirmedCasesTotal;
    private TextView confirmedCasesToday;
    private TextView recoveredCasesTotal;
    private TextView recoveredCasesToday;
    private TextView deathCasesTotal;
    private TextView deathCasesToday;
    private TextView activeCasesTotal;
    private TextView activeCasesToday;

    public GlobalStats() {
        // Required empty public constructor
    }

    public static GlobalStats newInstance(String param1, String param2) {
        GlobalStats fragment = new GlobalStats();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String url = "https://corona.lmao.ninja/v3/covid-19/all";
//        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
//                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//
//                        try {
//                            Numbers numbers = new Numbers(
////                                    response.get("cases").toString(), response.get("todayCases").toString(),
////                                    response.get("recovered").toString(), response.get("todayRecovered").toString(),
////                                    response.get("deaths").toString(), response.get("todayDeaths").toString(),
////                                    response.get("active").toString(), response.get("active").toString());
//                                    "1", "2",
//                                    "1", "2",
//                                    "1", "2",
//                                    "1", "2");
//                            numbersList.add(numbers);
//                            String pt = response.get("cases").toString();
//                            Log.d(TAG, "HERE:" + numbers.toString());
//                            Log.d(TAG, "HERE:" + numbersList.toString());
//                            Toast.makeText(getActivity(), pt, Toast.LENGTH_SHORT).show();
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//                        Log.d(TAG, "onResponse: " + error.getMessage());
//
//                    }
//                });
//
//        // Access the RequestQueue through your singleton class.
//        MySingleton.getInstance(getActivity()).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_global_stats, container, false);
        /*globalStatsRecyclerView = (RecyclerView) view.findViewById(R.id.global_stats_recycler_view);
        globalStatsRecyclerView.setLayoutManager(new LinearLayoutManager((Context) getActivity()));

        globalStatsNumbersAdapter = new NumbersAdapter(getActivity(),
                confirmedCasesTotal, confirmedCasesToday,
                recoveredCasesTotal, recoveredCasesToday,
                deathCasesTotal, deathCasesToday,
                activeCasesTotal, activeCasesToday);
        globalStatsRecyclerView.setAdapter(globalStatsNumbersAdapter);*/
        confirmedCasesTotal = view.findViewById(R.id.stats_confirmed_cases_total);
        confirmedCasesToday = view.findViewById(R.id.stats_confirmed_cases_today);
        recoveredCasesTotal = view.findViewById(R.id.stats_recovered_cases_total);
        recoveredCasesToday = view.findViewById(R.id.stats_recovered_cases_today);
        deathCasesTotal = view.findViewById(R.id.stats_death_cases_total);
        deathCasesToday = view.findViewById(R.id.stats_death_cases_today);
        activeCasesTotal = view.findViewById(R.id.stats_active_cases_total);
        activeCasesToday = view.findViewById(R.id.stats_active_cases_today);

        String url = "https://corona.lmao.ninja/v3/covid-19/all";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            confirmedCasesTotal.setText(response.get("cases").toString());
                            confirmedCasesToday.setText(response.get("todayCases").toString());
                            recoveredCasesTotal.setText(response.get("recovered").toString());
                            recoveredCasesToday.setText(response.get("todayRecovered").toString());
                            deathCasesTotal.setText(response.get("deaths").toString());
                            deathCasesToday.setText(response.get("todayDeaths").toString());
                            activeCasesTotal.setText(response.get("active").toString());
                            activeCasesToday.setText(response.get("active").toString());

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.d(TAG, "onResponse: " + error.getMessage());
                    }
                });
        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(getActivity()).addToRequestQueue(jsonObjectRequest);
        return view;
    }
}