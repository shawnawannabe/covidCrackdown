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

public class GlobalStatsFragment extends Fragment {

    private TextView confirmedCasesTotalGlobal;
    private TextView confirmedCasesTodayGlobal;
    private TextView recoveredCasesTotalGlobal;
    private TextView recoveredCasesTodayGlobal;
    private TextView deathCasesTotalGlobal;
    private TextView deathCasesTodayGlobal;
    private TextView activeCasesTotalGlobal;
    private TextView date;

    public GlobalStatsFragment() {
        // Required empty public constructor
    }

    public static GlobalStatsFragment newInstance(String param1, String param2) {
        GlobalStatsFragment fragment = new GlobalStatsFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String url = "https://corona.lmao.ninja/v3/covid-19/all";

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_global_stats, container, false);

        confirmedCasesTotalGlobal = view.findViewById(R.id.stats_confirmed_cases_total);
        confirmedCasesTodayGlobal = view.findViewById(R.id.stats_confirmed_cases_today);
        recoveredCasesTotalGlobal = view.findViewById(R.id.stats_recovered_cases_total);
        recoveredCasesTodayGlobal = view.findViewById(R.id.stats_recovered_cases_today);
        deathCasesTotalGlobal = view.findViewById(R.id.stats_death_cases_total);
        deathCasesTodayGlobal = view.findViewById(R.id.stats_death_cases_today);
        activeCasesTotalGlobal = view.findViewById(R.id.stats_active_cases_total);
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

                            confirmedCasesTotalGlobal.setText(confirmedCasesTotal);
                            confirmedCasesTodayGlobal.setText(confirmedCasesToday);
                            recoveredCasesTotalGlobal.setText(recoveredCasesTotal);
                            recoveredCasesTodayGlobal.setText(recoveredCasesToday);
                            deathCasesTotalGlobal.setText(deathCasesTotal);
                            deathCasesTodayGlobal.setText(deathCasesToday);
                            activeCasesTotalGlobal.setText(activeCasesTotal);

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