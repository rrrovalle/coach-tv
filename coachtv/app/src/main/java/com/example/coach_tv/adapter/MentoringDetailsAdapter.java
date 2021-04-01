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
import com.example.coach_tv.R;
import com.example.coach_tv.model.Meeting;

import java.util.ArrayList;

public class MentoringDetailsAdapter extends RecyclerView.Adapter<MentoringDetailsAdapter.Viewholder> {

    private Context context;
    private ArrayList<Meeting> meetingArrayList;

    // Constructor
    public MentoringDetailsAdapter(Context context, ArrayList<Meeting> meetingArrayList) {
        this.context = context;
        this.meetingArrayList = meetingArrayList;
    }

    @NonNull
    @Override
    public MentoringDetailsAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_card, parent, false);
        return new MentoringDetailsAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MentoringDetailsAdapter.Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        Meeting model = meetingArrayList.get(position);
        holder.courseNameTV.setText("Meeting - "+ (position+1));
        holder.courseDesc.setText(model.getDescription());
        holder.courseDate.setText(model.getDate());
        holder.courseUser.setText(model.getUsername());
    }

    @Override
    public int getItemCount() {
        // this method is used for showing number
        // of card items in recycler view.
        return meetingArrayList.size();
    }

    // View holder class for initializing of
    // your views such as TextView and Imageview.
    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView courseNameTV, courseDesc, courseDate, courseUser;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(v -> {
                /** Lógica para entrar na sala de conferência */
            });

            courseNameTV = itemView.findViewById(R.id.idSchedule);
            courseDesc = itemView.findViewById(R.id.idObservations);
            courseDate = itemView.findViewById(R.id.idDate);
            courseUser = itemView.findViewById(R.id.idUser);
        }
    }
}
