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
import com.example.coach_tv.model.Mentoring;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.Viewholder> {

    private Context context;
    private ArrayList<Mentoring> mentoringArrayList;

    // Constructor
    public PopularAdapter(Context context, ArrayList<Mentoring> mentoringArrayList) {
        this.context = context;
        this.mentoringArrayList = mentoringArrayList;
    }

    @NonNull
    @Override
    public PopularAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_recycler_items, parent, false);
        return new PopularAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        Mentoring model = mentoringArrayList.get(position);
        holder.courseNameTV.setText(model.getTitle());
        holder.courseIV.setImageResource(model.getImage());
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
        private ImageView courseIV;
        private TextView courseNameTV, courseDesc, courseRatingTV, courseCredits, courseSection;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(v -> {
                String strName = mentoringArrayList.get(getAdapterPosition()).getTitle();
                String strSection = mentoringArrayList.get(getAdapterPosition()).getSection();
                String strDesc  = mentoringArrayList.get(getAdapterPosition()).getDesc();
                int intRate = mentoringArrayList.get(getAdapterPosition()).getRating();
                // INTENT CONTENT
                Intent intent = new Intent(v.getContext(), MentoringDetails.class);
                intent.putExtra("STRING_NAME", strName);
                intent.putExtra("STRING_DESC", strDesc);
                intent.putExtra("STRING_SECTION", strSection);
                intent.putExtra("INT_RATE", intRate);
                v.getContext().startActivity(intent);
            });

            courseIV        = itemView.findViewById(R.id.idPopularImg);
            courseNameTV    = itemView.findViewById(R.id.idPopularName);
            courseDesc      = itemView.findViewById(R.id.idDesc);
            courseCredits   = itemView.findViewById(R.id.recommended_price);
            courseSection   = itemView.findViewById(R.id.section_type);
            courseRatingTV  = itemView.findViewById(R.id.recommended_rating);
        }
    }
}
