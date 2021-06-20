package com.example.covidcrackdown;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FaqsAdapter extends RecyclerView.Adapter<FaqsAdapter.MyViewHolder> {

    Context context;
    ArrayList<Faqs> faqsList;

    public FaqsAdapter(Context context, ArrayList<Faqs> faqsList) {
        this.context = context;
        this.faqsList = faqsList;
    }

    @NonNull
    @Override
    public FaqsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faqs_list_item,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Faqs faqsActivity = faqsList.get(position);
        holder.faqsTitle.setText(faqsActivity.getFaqsTitle());
        holder.faqsDetail.setText(faqsActivity.getFaqsDetail());
    }

    @Override
    public int getItemCount() {
        return faqsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView faqsTitle, faqsDetail;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            faqsTitle = itemView.findViewById(R.id.faqs_title);
            faqsDetail = itemView.findViewById(R.id.faqs_detail);
        }
    }
}
