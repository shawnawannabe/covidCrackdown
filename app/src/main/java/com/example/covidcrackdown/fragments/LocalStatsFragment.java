package com.example.covidcrackdown.fragments;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.covidcrackdown.models.MySingleton;
import com.example.covidcrackdown.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static android.content.ContentValues.TAG;

public class LocalStatsFragment extends Fragment {

    private TextView confirmedCasesTotalLocal;
    private TextView confirmedCasesTodayLocal;
    private TextView recoveredCasesTotalLocal;
    private TextView recoveredCasesTodayLocal;
    private TextView deathCasesTotalLocal;
    private TextView deathCasesTodayLocal;
    private TextView activeCasesTotalLocal;
    private TextView activeCasesTodayLocal;
    private TextView date;

    public LocalStatsFragment() {
        // Required empty public constructor
    }

    public static LocalStatsFragment newInstance(String param1, String param2) {
        LocalStatsFragment fragment = new LocalStatsFragment();
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

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_local_stats, container, false);
        confirmedCasesTotalLocal = view.findViewById(R.id.stats_local_confirmed_cases_total);
        confirmedCasesTodayLocal = view.findViewById(R.id.stats_local_confirmed_cases_today);
        recoveredCasesTotalLocal = view.findViewById(R.id.stats_local_recovered_cases_total);
        recoveredCasesTodayLocal = view.findViewById(R.id.stats_local_recovered_cases_today);
        deathCasesTotalLocal = view.findViewById(R.id.stats_local_death_cases_total);
        deathCasesTodayLocal = view.findViewById(R.id.stats_local_death_cases_today);
        activeCasesTotalLocal = view.findViewById(R.id.stats_local_active_cases_total);
        activeCasesTodayLocal = view.findViewById(R.id.stats_local_active_cases_today);
        date = view.findViewById(R.id.stats_local_date);

        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd MMM yyyy");
        LocalDateTime now = LocalDateTime.now();
        date.setText("DATE: " + dtf.format(now));

        String url = "https://corona.lmao.ninja/v3/covid-19/countries/Malaysia?strict=true";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            String confirmedCasesTotal = "Total Confirmed cases: \n" + numFormatter(response.get("cases").toString());
                            String confirmedCasesToday = "+" + numFormatter(response.get("todayCases").toString()) + " today";
                            String recoveredCasesTotal = "Total Recovered cases: \n" + numFormatter(response.get("recovered").toString());
                            String recoveredCasesToday = "+" + numFormatter(response.get("todayRecovered").toString()) + " today";
                            String deathCasesTotal = "Total Death cases: \n" + numFormatter(response.get("deaths").toString());
                            String deathCasesToday = "+" + numFormatter(response.get("todayDeaths").toString()) + " today";
                            String activeCasesTotal = "Total Active cases: \n" + numFormatter(response.get("active").toString());
                            String activeCasesToday = "+" + numFormatter(response.get("active").toString()) + " today";

                            confirmedCasesTotalLocal.setText(confirmedCasesTotal);
                            confirmedCasesTodayLocal.setText(confirmedCasesToday);
                            recoveredCasesTotalLocal.setText(recoveredCasesTotal);
                            recoveredCasesTodayLocal.setText(recoveredCasesToday);
                            deathCasesTotalLocal.setText(deathCasesTotal);
                            deathCasesTodayLocal.setText(deathCasesToday);
                            activeCasesTotalLocal.setText(activeCasesTotal);
                            activeCasesTodayLocal.setText(activeCasesToday);

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
        // Access the RequestQueue through singleton class.
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