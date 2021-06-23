package com.example.covidcrackdown;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
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

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class GlobalStats extends Fragment {

    private TextView confirmedCasesTotalGlobal;
    private TextView confirmedCasesTodayGlobal;
    private TextView recoveredCasesTotalGlobal;
    private TextView recoveredCasesTodayGlobal;
    private TextView deathCasesTotalGlobal;
    private TextView deathCasesTodayGlobal;
    private TextView activeCasesTotalGlobal;
    private TextView activeCasesTodayGlobal;
    private TextView date;

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

    @RequiresApi(api = Build.VERSION_CODES.O)
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
        confirmedCasesTotalGlobal = view.findViewById(R.id.stats_confirmed_cases_total);
        confirmedCasesTodayGlobal = view.findViewById(R.id.stats_confirmed_cases_today);
        recoveredCasesTotalGlobal = view.findViewById(R.id.stats_recovered_cases_total);
        recoveredCasesTodayGlobal = view.findViewById(R.id.stats_recovered_cases_today);
        deathCasesTotalGlobal = view.findViewById(R.id.stats_death_cases_total);
        deathCasesTodayGlobal = view.findViewById(R.id.stats_death_cases_today);
        activeCasesTotalGlobal = view.findViewById(R.id.stats_active_cases_total);
        activeCasesTodayGlobal = view.findViewById(R.id.stats_active_cases_today);
        date = view.findViewById(R.id.stats_global_date);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy");
        LocalDateTime now = LocalDateTime.now();
        date.setText("DATE: " + dtf.format(now));

        String url = "https://corona.lmao.ninja/v3/covid-19/all";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            String confirmedCasesTotal = "Total Confirmed cases: \n" + numFormatter(response.get("cases").toString());
                            String confirmedCasesToday = "+" + numFormatter(response.get("todayCases").toString());
                            String recoveredCasesTotal = "Total Recovered cases: \n" + numFormatter(response.get("recovered").toString());
                            String recoveredCasesToday = "+" + numFormatter(response.get("todayRecovered").toString());
                            String deathCasesTotal = "Total Death cases: \n" + numFormatter(response.get("deaths").toString());
                            String deathCasesToday = "+" + numFormatter(response.get("todayDeaths").toString());
                            String activeCasesTotal = "Total Active cases: \n" + numFormatter(response.get("active").toString());
                            String activeCasesToday = "+" + numFormatter(response.get("active").toString());

                            confirmedCasesTotalGlobal.setText(confirmedCasesTotal);
                            confirmedCasesTodayGlobal.setText(confirmedCasesToday);
                            recoveredCasesTotalGlobal.setText(recoveredCasesTotal);
                            recoveredCasesTodayGlobal.setText(recoveredCasesToday);
                            deathCasesTotalGlobal.setText(deathCasesTotal);
                            deathCasesTodayGlobal.setText(deathCasesToday);
                            activeCasesTotalGlobal.setText(activeCasesTotal);
                            activeCasesTodayGlobal.setText(activeCasesToday);

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

    public String numFormatter(String cases){
        double amount = Double.parseDouble(cases);
        DecimalFormat formatter = new DecimalFormat("#,###");
        String converted = formatter.format(amount);
        return converted;
    }
}