package com.example.covidcrackdown;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;

public class NumbersAdapter extends RecyclerView.Adapter<NumbersAdapter.MyViewHolder> {
    Context context;
    String confirmedCasesTotal;
    String confirmedCasesToday;
    String recoveredCasesTotal;
    String recoveredCasesToday;
    String deathCasesTotal;
    String deathCasesToday;
    String activeCasesTotal;
    String activeCasesToday;

    public NumbersAdapter(Context context, String confirmedCasesTotal, String confirmedCasesToday, String recoveredCasesTotal, String recoveredCasesToday, String deathCasesTotal, String deathCasesToday, String activeCasesTotal, String activeCasesToday) {
        this.context = context;
        this.confirmedCasesTotal = confirmedCasesTotal;
        this.confirmedCasesToday = confirmedCasesToday;
        this.recoveredCasesTotal = recoveredCasesTotal;
        this.recoveredCasesToday = recoveredCasesToday;
        this.deathCasesTotal = deathCasesTotal;
        this.deathCasesToday = deathCasesToday;
        this.activeCasesTotal = activeCasesTotal;
        this.activeCasesToday = activeCasesToday;
    }

    @NonNull
    @Override
    public NumbersAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.stats_list_item, parent, false);
        NumbersAdapter.MyViewHolder viewHolder = new NumbersAdapter.MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NumbersAdapter.MyViewHolder holder, int position) {
        holder.confirmedCasesTotal.setText(confirmedCasesTotal);
        holder.confirmedCasesToday.setText(confirmedCasesToday);
        holder.recoveredCasesTotal.setText(recoveredCasesTotal);
        holder.recoveredCasesToday.setText(recoveredCasesToday);
        holder.deathCasesTotal.setText(deathCasesTotal);
        holder.deathCasesToday.setText(deathCasesToday);
        holder.activeCasesTotal.setText(activeCasesTotal);
        holder.activeCasesToday.setText(activeCasesToday);

    }

    @Override
    public int getItemCount() {
        return 8;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView confirmedCasesTotal;
        TextView confirmedCasesToday;
        TextView recoveredCasesTotal;
        TextView recoveredCasesToday;
        TextView deathCasesTotal;
        TextView deathCasesToday;
        TextView activeCasesTotal;
        TextView activeCasesToday;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            confirmedCasesTotal = itemView.findViewById(R.id.stats_confirmed_cases_total);
            confirmedCasesToday = itemView.findViewById(R.id.stats_confirmed_cases_today);
            recoveredCasesTotal = itemView.findViewById(R.id.stats_recovered_cases_total);
            recoveredCasesToday = itemView.findViewById(R.id.stats_recovered_cases_today);
            deathCasesTotal = itemView.findViewById(R.id.stats_death_cases_total);
            deathCasesToday = itemView.findViewById(R.id.stats_death_cases_today);
            activeCasesTotal = itemView.findViewById(R.id.stats_active_cases_total);
            activeCasesToday = itemView.findViewById(R.id.stats_active_cases_today);
        }
    }
}
