package com.example.covidcrackdown.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.covidcrackdown.models.Faqs;
import com.example.covidcrackdown.adapters.FaqsAdapter;
import com.example.covidcrackdown.models.MySingleton;
import com.example.covidcrackdown.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static android.content.ContentValues.TAG;

public class FaqsActivity extends AppCompatActivity {

    private LinearLayout faqsLinearLayout;
    //private TextView test;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;
    private RecyclerView faqRecyclerView;
    private FaqsAdapter faqsAdapter;
    private ArrayList<Faqs> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //back button on toolbar
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faqs);
        Toolbar toolbar = findViewById(R.id.faqs_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        //handler for recycler view from firebase
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();

        faqsLinearLayout = findViewById(R.id.faqs_linear_layout);
        faqRecyclerView = findViewById(R.id.faqs_recycler_view);
        faqRecyclerView.setHasFixedSize(true);  //optimization
        faqRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        faqsAdapter = new FaqsAdapter(this, list);
        faqRecyclerView.setAdapter(faqsAdapter);

        Query faqQuery = mDatabase.child("faqs");
        faqQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Faqs faqs = dataSnapshot.getValue(Faqs.class);
                    list.add(faqs);
                }
                faqsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e(TAG, "onCancelled: ",error.toException() );
            }
        });

    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    public void postData(String url, HashMap data){

        JsonObjectRequest jsonobj = new JsonObjectRequest(Request.Method.POST, url,new JSONObject(data),
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse: checkdb");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e(TAG, "onErrorResponse: ", error.getCause());
                    }
                }
        ){
            //here I want to post data to sever
        };

        MySingleton.getInstance(this).addToRequestQueue(jsonobj);
    }
}