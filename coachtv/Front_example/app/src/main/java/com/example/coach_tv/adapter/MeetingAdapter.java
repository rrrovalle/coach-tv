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

import com.example.coach_tv.MeetingDetails;
import com.example.coach_tv.R;
import com.example.coach_tv.model.MeetingDTO;

import java.util.ArrayList;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.Viewholder> {

    private Context context;
    private ArrayList<MeetingDTO> meetingArrayList;

    public MeetingAdapter(Context context, ArrayList<MeetingDTO> meetingArrayList) {
        this.context = context;
        this.meetingArrayList = meetingArrayList;
    }

    @NonNull
    @Override
    public MeetingAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_meeting, parent, false);
        return new MeetingAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MeetingAdapter.Viewholder holder, int position) {
        MeetingDTO model = meetingArrayList.get(position);
        holder.courseName.setText(model.getMentoring().getTitle());
//        holder.courseDate.setText(model.getDate());
//        holder.coursePrice.setText("" + model.getCredits());
 //       holder.cardMeetingImg.setImageResource(model.getImage());
    }

    @Override
    public int getItemCount() {
        return meetingArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private ImageView cardMeetingImg;
        private TextView courseName, courseDate, coursePrice;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(v -> {
                String strName    = meetingArrayList.get(getAdapterPosition()).getMentoring().getTitle();
                //String strDate  = meetingArrayList.get(getAdapterPosition()).getStartTime();
                String strDesc    = meetingArrayList.get(getAdapterPosition()).getDescription();
                String strCoach   = meetingArrayList.get(getAdapterPosition()).getMentoring().getCoach().getName();
                String strMentoringSection   = meetingArrayList.get(getAdapterPosition()).getMentoring().getSection();
                String strMentoringDesc   = meetingArrayList.get(getAdapterPosition()).getMentoring().getDescription();
                long idMent = meetingArrayList.get(getAdapterPosition()).getId();
                Intent intent = new Intent(v.getContext(), MeetingDetails.class);
                intent.putExtra("STRING_NAME", strName);
                intent.putExtra("LONG_ID", idMent);
                intent.putExtra("STRING_DESC", strDesc);
                intent.putExtra("STRING_SECTION", strMentoringSection);
                intent.putExtra("STRING_MENTORING_DESC", strMentoringDesc);
                intent.putExtra("STRING_COACH", strCoach);
                v.getContext().startActivity(intent);
            });

            cardMeetingImg = itemView.findViewById(R.id.cardViewMentoringImg);
            courseName = itemView.findViewById(R.id.recommended_name);
        }
    }
}
