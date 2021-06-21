package com.example.covidcrackdown;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.graphics.Color;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class HotSpot extends AppCompatActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hot_spot);

        Toolbar toolbar = findViewById(R.id.hotspot_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        // Get a handle to the fragment and register the callback.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    // Get a handle to the GoogleMap object and display marker.
    @Override
    public void onMapReady(GoogleMap googleMap) {

        googleMap.addMarker(new MarkerOptions()
                .position(new LatLng(2.8327465697628815, 101.70697499691381))
                .title("Marker"));

        googleMap.addCircle(new CircleOptions()
                .center(new LatLng(2.8370114208250823, 101.70636345331911))
                .radius(300)
                .strokeWidth(10)
                .strokeColor(Color.argb(128, 255, 0, 0))
                .fillColor(Color.argb(128, 255, 0, 0)));

        googleMap.addCircle(new CircleOptions()
                .center(new LatLng(2.824554296864241, 101.69383890174753))
                .radius(500)
                .strokeWidth(10)
                .strokeColor(Color.argb(128, 255, 0, 0))
                .fillColor(Color.argb(128, 255, 0, 0)));

        googleMap.addCircle(new CircleOptions()
                .center(new LatLng(2.843081226805592, 101.68329483365896))
                .radius(800)
                .strokeWidth(10)
                .strokeColor(Color.argb(128, 255, 0, 0))
                .fillColor(Color.argb(128, 255, 0, 0)));
    }
}