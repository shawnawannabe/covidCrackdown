package com.example.covidcrackdown.models;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Faqs {
    private String faqsTitle;
    private String faqsDetail;

    public Faqs(){

    }

    public Faqs(String faqsTitle, String faqsDetail) {
        this.faqsTitle = faqsTitle;
        this.faqsDetail = faqsDetail;
    }

    @Exclude
    public Map<String, Object> toMap(){
        HashMap<String, Object> result = new HashMap<>();
        result.put("faqsTitle", faqsTitle);
        result.put("faqsDetail", faqsDetail);

        return result;
    }

    public String getFaqsTitle() {
        return faqsTitle;
    }

    public void setFaqsTitle(String faqsTitle) {
        this.faqsTitle = faqsTitle;
    }

    public String getFaqsDetail() {
        return faqsDetail;
    }

    public void setFaqsDetail(String faqsDetail) {
        this.faqsDetail = faqsDetail;
    }
}
