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
import com.example.coach_tv.model.MentoringDTO;

import java.util.ArrayList;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.Viewholder> {

    private Context context;
    private ArrayList<MentoringDTO> mentoringArrayList;

    // Constructor
    public PopularAdapter(Context context, ArrayList<MentoringDTO> mentoringArrayList) {
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
        MentoringDTO model = mentoringArrayList.get(position);
        holder.courseNameTV.setText(model.getTitle());
        //holder.courseIV.setImageResource(model.getImage());
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
        private TextView courseNameTV, courseCredits;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(v -> {
                String strName      = mentoringArrayList.get(getAdapterPosition()).getTitle();
                String strSection   = mentoringArrayList.get(getAdapterPosition()).getSection();
                String strDesc      = mentoringArrayList.get(getAdapterPosition()).getDescription();
                String strCoach     = mentoringArrayList.get(getAdapterPosition()).getCoach().getName();
                int intIdMentoring  = mentoringArrayList.get(getAdapterPosition()).getId();
                // INTENT CONTENT
                Intent intent = new Intent(v.getContext(), MentoringDetails.class);
                intent.putExtra("STRING_NAME", strName);
                intent.putExtra("STRING_DESC", strDesc);
                intent.putExtra("STRING_SECTION", strSection);
                intent.putExtra("STRING_COACH", strCoach);
                intent.putExtra("STRING_PRICE", "300");
                intent.putExtra("INT_ID_MENTORING", intIdMentoring);
                //intent.putExtra("INT_RATE", intRate);
                v.getContext().startActivity(intent);
            });

            courseIV        = itemView.findViewById(R.id.idPopularImg);
            courseNameTV    = itemView.findViewById(R.id.idPopularName);
            courseCredits   = itemView.findViewById(R.id.recommended_price);
        }
    }
}
