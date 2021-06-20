package com.example.covidcrackdown;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Numbers {
    private String confirmedCasesTotal;
    private String confirmedCasesToday;
    private String recoveredCasesTotal;
    private String recoveredCasesToday;
    private String deathCasesTotal;
    private String deathCasesToday;
    private String activeCasesTotal;
    private String activeCasesToday;


    public Numbers(String confirmedCasesTotal, String confirmedCasesToday, String recoveredCasesTotal, String recoveredCasesToday, String deathCasesTotal, String deathCasesToday, String activeCasesTotal, String activeCasesToday) {
        this.confirmedCasesTotal = confirmedCasesTotal;
        this.confirmedCasesToday = confirmedCasesToday;
        this.recoveredCasesTotal = recoveredCasesTotal;
        this.recoveredCasesToday = recoveredCasesToday;
        this.deathCasesTotal = deathCasesTotal;
        this.deathCasesToday = deathCasesToday;
        this.activeCasesTotal = activeCasesTotal;
        this.activeCasesToday = activeCasesToday;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("confirmedCasesTotal", confirmedCasesTotal);
        result.put("confirmedCasesToday", confirmedCasesToday);
        result.put("recoveredCasesTotal", recoveredCasesTotal);
        result.put("recoveredCasesToday", recoveredCasesToday);
        result.put("deathCasesTotal", deathCasesTotal);
        result.put("deathCasesToday", deathCasesToday);
        result.put("activeCasesTotal", activeCasesTotal);
        result.put("activeCasesToday", activeCasesToday);

        return result;
    }

    public String getConfirmedCasesTotal() {
        return confirmedCasesTotal;
    }

    public void setConfirmedCasesTotal(String confirmedCasesTotal) {
        this.confirmedCasesTotal = confirmedCasesTotal;
    }

    public String getConfirmedCasesToday() {
        return confirmedCasesToday;
    }

    public void setConfirmedCasesToday(String confirmedCasesToday) {
        this.confirmedCasesToday = confirmedCasesToday;
    }

    public String getRecoveredCasesTotal() {
        return recoveredCasesTotal;
    }

    public void setRecoveredCasesTotal(String recoveredCasesTotal) {
        this.recoveredCasesTotal = recoveredCasesTotal;
    }

    public String getRecoveredCasesToday() {
        return recoveredCasesToday;
    }

    public void setRecoveredCasesToday(String recoveredCasesToday) {
        this.recoveredCasesToday = recoveredCasesToday;
    }

    public String getDeathCasesTotal() {
        return deathCasesTotal;
    }

    public void setDeathCasesTotal(String deathCasesTotal) {
        this.deathCasesTotal = deathCasesTotal;
    }

    public String getDeathCasesToday() {
        return deathCasesToday;
    }

    public void setDeathCasesToday(String deathCasesToday) {
        this.deathCasesToday = deathCasesToday;
    }

    public String getActiveCasesTotal() {
        return activeCasesTotal;
    }

    public void setActiveCasesTotal(String activeCasesTotal) {
        this.activeCasesTotal = activeCasesTotal;
    }

    public String getActiveCasesToday() {
        return activeCasesToday;
    }

    public void setActiveCasesToday(String activeCasesToday) {
        this.activeCasesToday = activeCasesToday;
    }

}
