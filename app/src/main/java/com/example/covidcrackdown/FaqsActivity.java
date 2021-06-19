package com.example.covidcrackdown;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.bluetooth.BluetoothGattDescriptor;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONObject;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
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


//        test = findViewById(R.id.faqs_test);
//        mAuth = FirebaseAuth.getInstance();
//        String uid = mAuth.getCurrentUser().getUid();
//        String url = "https://android-2c0a7-default-rtdb.firebaseio.com/users/"+ uid + "/location.json";
        /*String url = "https://android-2c0a7-default-rtdb.firebaseio.com/location.json";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d(TAG, "onResponse: HERE");
                        test.setText("Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Log.d(TAG, "onResponse: " + error.getMessage());

                    }
                });

        // Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);*/

     /*   HashMap data = new HashMap();
        data.put("loc", "kfc");
        data.put("time", 12);*/


        //postData(url, data);

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