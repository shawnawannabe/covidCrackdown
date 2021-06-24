package com.example.covidcrackdown.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.covidcrackdown.R;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    String date[];
    String title[];
    int profileImage[];
    int image[];
    Context context;

    public RecyclerAdapter(Context context, String[] date, String[] title, int[] profileImage, int[] image) {
        this.context = context;
        this.date = date;
        this.title = title;
        this.profileImage = profileImage;
        this.image = image;
    }

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.layout_list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        holder.date.setText(date[position]);
        holder.title.setText(title[position]);
        holder.profileImage.setImageResource(profileImage[position]);
        holder.caseImage.setImageResource(image[position]);
    }

    @Override
    public int getItemCount() {
        return date.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView date;
        TextView title;
        ImageView profileImage;
        ImageView caseImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            title = itemView.findViewById(R.id.title);
            profileImage = itemView.findViewById(R.id.profile_pic);
            caseImage = itemView.findViewById(R.id.image_of_cases);
        }
    }
}
