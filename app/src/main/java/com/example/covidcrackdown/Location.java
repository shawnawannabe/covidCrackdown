package com.example.covidcrackdown;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Location {
    private String locationName;
    private Integer time;
//    private Map<String, Boolean> info = new HashMap<>();

    public Location(){

    }
    public Location(String locationName, Integer time) {
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

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

}
