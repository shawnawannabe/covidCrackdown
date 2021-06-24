package com.example.covidcrackdown.models;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Location {
    private String locationName;
    private String time;
//    private Map<String, Boolean> info = new HashMap<>();

    public Location(){

    }
    public Location(String locationName, String time) {
        this.locationName = locationName;
        this.time = time;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("locationName", locationName);
        result.put("time", time);

        return result;
    }

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

}
