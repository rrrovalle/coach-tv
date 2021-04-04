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

/** ALL MENU ADAPTER */
public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.Viewholder> {

    private Context context;
    private ArrayList<MentoringDTO> mentoringArrayList;

    // Constructor
    public CourseAdapter(Context context, ArrayList<MentoringDTO> mentoringArrayList) {
        this.context = context;
        this.mentoringArrayList = mentoringArrayList;
    }

    @NonNull
    @Override
    public CourseAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.allmenu_recycler_items, parent, false);
        return new Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.Viewholder holder, int position) {
        // to set data to textview and imageview of each card layout
        MentoringDTO model = mentoringArrayList.get(position);
        holder.courseNameTV.setText(model.getTitle());
        holder.courseDesc.setText(model.getDescription());
        holder.courseRatingTV.setText("" + model.getRating());
        //holder.courseCredits.setText(""+model.getCredits());
        holder.courseSection.setText(model.getSection());
        holder.courseCredits.setText("300");
        int id = context.getResources().getIdentifier("res:drawable/" + "code.png", null, null);
        holder.courseIV.setImageResource(id);
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
        private TextView courseNameTV, courseRatingTV, courseSection, courseDesc, courseCredits;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(v -> {
                String strName = mentoringArrayList.get(getAdapterPosition()).getTitle();
                String strSection = mentoringArrayList.get(getAdapterPosition()).getSection();
                String strDesc  = mentoringArrayList.get(getAdapterPosition()).getDescription();
                String strCoach  = mentoringArrayList.get(getAdapterPosition()).getCoach().getName();
                int intIdMentoring  = mentoringArrayList.get(getAdapterPosition()).getId();
                // INTENT CONTENT
                Intent intent = new Intent(v.getContext(), MentoringDetails.class);
                intent.putExtra("STRING_NAME", strName);
                intent.putExtra("STRING_DESC", strDesc);
                intent.putExtra("STRING_SECTION", strSection);
                intent.putExtra("STRING_COACH", strCoach);
                intent.putExtra("STRING_PRICE","300");
                intent.putExtra("INT_ID_MENTORING", intIdMentoring);
                v.getContext().startActivity(intent);
            });
            courseIV = itemView.findViewById(R.id.cardViewImg);
            courseNameTV = itemView.findViewById(R.id.idCourseName);
            courseRatingTV = itemView.findViewById(R.id.idTVCourseRating);
            courseDesc = itemView.findViewById(R.id.idDesc);
            courseCredits = itemView.findViewById(R.id.all_menu_price);
            courseSection = itemView.findViewById(R.id.all_menu_section);
        }
    }
}