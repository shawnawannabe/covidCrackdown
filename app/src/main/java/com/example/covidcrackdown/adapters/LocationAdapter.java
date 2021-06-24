package com.example.covidcrackdown.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;

import com.example.covidcrackdown.R;
import com.example.covidcrackdown.models.Location;

import java.util.ArrayList;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.MyViewHolder> {
    Context context;
    ArrayList<Location> locationList;

    public LocationAdapter(Context context, ArrayList<Location> locationList) {
        this.context = context;
        this.locationList = locationList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.location_list_item,parent,false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Location location = locationList.get(position);
        holder.locationName.setText(location.getLocationName());
        holder.locationDate.setText(location.getTime());

    }

    @Override
    public int getItemCount() {
        return locationList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView locationName, locationDate, age;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            locationName = itemView.findViewById(R.id.location_name);
            locationDate = itemView.findViewById(R.id.location_date);

        }
    }
}
