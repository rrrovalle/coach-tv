package com.example.coach_tv.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coach_tv.MentoringDetails;
import com.example.coach_tv.R;
import com.example.coach_tv.ScheduleActivity;
import com.example.coach_tv.model.Mentoring;

import java.util.ArrayList;

public class MentoringAdapter extends RecyclerView.Adapter<MentoringAdapter.Viewholder> {

    private Context context;
    private ArrayList<Mentoring> mentoringArrayList;

    // Constructor
    public MentoringAdapter(Context context, ArrayList<Mentoring> mentoringArrayList) {
        this.context = context;
        this.mentoringArrayList = mentoringArrayList;
    }

    @NonNull
    @Override
    public MentoringAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_mentoring, parent, false);
        return new MentoringAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MentoringAdapter.Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        Mentoring model = mentoringArrayList.get(position);
        holder.courseNameTV.setText(model.getTitle());
        holder.imgMentoring.setImageResource(model.getImage());
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return mentoringArrayList.size();
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public class Viewholder extends RecyclerView.ViewHolder {
        private ImageView imgMentoring;
        private TextView courseNameTV;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(v -> {
                Intent intent = new Intent(v.getContext(), ScheduleActivity.class);
                v.getContext().startActivity(intent);
            });

            imgMentoring = itemView.findViewById(R.id.cardViewMentoringImg);
            courseNameTV = itemView.findViewById(R.id.mentoringTitle);
        }
    }
}