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
//        FirebaseUser user = mAuth.getCurrentUser();
//        String userId = user.getUid();
        //writeLocation(userId, "KFC", 1);
        //writeFaqs("What is COVID-19?", "COVID-19 is a disease caused by a virus called SARS-CoV-2. Most people with COVID-19 have mild symptoms, but some people can become severely ill. Although most people with COVID-19 get better within weeks of illness, some people experience post-COVID conditions. Post-COVID conditions are a wide range of new, returning, or ongoing health problems people can experience more than four weeks after first being infected with the virus that causes COVID-19. Older people and those who have certain underlying medical conditions are more likely to get severely ill from COVID-19. Vaccines against COVID-19 are safe and effective.");
        //writeFaqs("How does the virus spread?",
//                "COVID-19 is thought to spread mainly through close contact from person to person, including between people who are physically near each other (within about 6 feet). People who are infected but do not show symptoms can also spread the virus to others. Cases of reinfection with COVID-19  have been reported but are rare. We are still learning about how the virus spreads and the severity of illness it causes.\n" +
//                        "\n" +
//                        "COVID-19 spreads very easily from person to person. How easily a virus spreads from person to person can vary. The virus that causes COVID-19 appears to spread more efficiently than influenza but not as efficiently as measles, which is among the most contagious viruses known to affect people.\n" +
//                        "\n" +
//                        "For more information about how COVID-19 spreads, visit the How COVID-19 Spreads page to learn how COVID-19 spreads and how to protect yourself. \n" +
//                        "\n");
        //writeFaqs("What is community spread?",
//                "Community spread means people have been infected with the virus in an area, including some who are not sure how or where they became infected. Each health department determines community spread differently based on local conditions. For information on community spread in your area, please visit your local health department’s website.");
        //writeFaqs("Can mosquitoes or ticks spread the virus that causes COVID-19?",
//                "At this time, CDC has no data to suggest that this new coronavirus or other similar coronaviruses are spread by mosquitoes or ticks. The main way that COVID-19 spreads is from person to person. See How Coronavirus Spreads for more information.");
        Log.d(TAG, "openHotSpotActivity: check db");


    }

    private void openFaqsActivity() {
        Intent intent = new Intent(getActivity(), FaqsActivity.class);
        startActivity(intent);
    }

    public void openStatsActivity(){
        Intent intent = new Intent(getActivity(), StatisticActivity.class);
        startActivity(intent);
//        tryout("https://corona.lmao.ninja/v2/all");
    }

    public void openLogoutActivity(){
        Intent intent = new Intent(getActivity(), SignInActivity.class);
        killActivity();
        startActivity(intent);
    }

    void killActivity(){
        getActivity().finish();
    }

    public void writeLocation(String userId, String locationName, String time){
        //push() to generate node
        // getKey() to generate key
        String key = mDatebase.push().getKey();
//reference        String key = mDatebase.child("users").child("location").push().getKey();
        Location location = new Location(locationName, time);
        Map<String, Object> locationValues = location.toMap();
        Map<String, Object> childUpdates = new HashMap<>();

//        childUpdates.put("/users/" + userId + "/location/" + key, locationValues);
        childUpdates.put(key, locationValues);
        mDatebase.child("location").updateChildren(childUpdates);
    }

    public void writeFaqs(String faqsTitle, String faqsDetail){
        String key = mDatebase.push().getKey();
        Faqs faqs = new Faqs(faqsTitle, faqsDetail);
        Map<String, Object> faqsValue = faqs.toMap();
        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put(key, faqsValue);
        mDatebase.child("faqs").updateChildren(childUpdates);

    }

    public void tryout(String url){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "check db");
                        try {
                            String pt = response.get("cases").toString();
                            Toast.makeText(getActivity(), pt, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: THE WRITE DATA
                        Log.d(TAG, "onResponse: " + error.getMessage());

                    }
                });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(getActivity()).addToRequestQueue(jsonObjectRequest);
    }
}