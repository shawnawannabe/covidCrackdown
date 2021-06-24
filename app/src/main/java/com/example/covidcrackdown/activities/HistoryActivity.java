package com.example.covidcrackdown.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;

import com.example.covidcrackdown.models.Location;
import com.example.covidcrackdown.adapters.LocationAdapter;
import com.example.covidcrackdown.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class HistoryActivity extends AppCompatActivity {

    private LinearLayout linearLayout;
    private DatabaseReference mDatabase;
    private DatabaseReference locationDatabase;
    private FirebaseAuth mAuth;
    private RecyclerView historyRecyclerView;
    private LocationAdapter locationAdapter;
    private ArrayList<Location> list;

    //    private TextView button;
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        Toolbar toolbar = findViewById(R.id.history_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        linearLayout = findViewById(R.id.history_linear_layout);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        mAuth = FirebaseAuth.getInstance();

        historyRecyclerView = findViewById(R.id.history_recycler_view);
        locationDatabase = FirebaseDatabase.getInstance().getReference("location");
        historyRecyclerView.setHasFixedSize(true);
        historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        list = new ArrayList<>();
        locationAdapter = new LocationAdapter(this,list);
        historyRecyclerView.setAdapter(locationAdapter);
        String uid = mAuth.getCurrentUser().getUid();

        Query myLocation = mDatabase.child("users").child(uid).child("location");

        myLocation.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren()){

                    Location location = dataSnapshot.getValue(Location.class);
                    list.add(location);
                }
                locationAdapter.notifyDataSetChanged();
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
}