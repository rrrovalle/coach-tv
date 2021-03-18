package com.example.coach_tv.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coach_tv.MeetingDetails;
import com.example.coach_tv.R;
import com.example.coach_tv.ScheduleActivity;
import com.example.coach_tv.model.Meeting;

import java.util.ArrayList;

public class MeetingAdapter extends RecyclerView.Adapter<MeetingAdapter.Viewholder> {

    private Context context;
    private ArrayList<Meeting> meetingArrayList;

    // Constructor
    public MeetingAdapter(Context context, ArrayList<Meeting> meetingArrayList) {
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
        // to set data to textview and imageview of each card layout
        Meeting model = meetingArrayList.get(position);
        holder.courseName.setText(model.getTitle());
//        holder.courseDate.setText(model.getDate());
//        holder.coursePrice.setText("" + model.getCredits());
        holder.cardMeetingImg.setImageResource(model.getImage());
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
        private ImageView cardMeetingImg;
        private TextView courseName, courseDate, coursePrice;

        public Viewholder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(v -> {
                String strName = meetingArrayList.get(getAdapterPosition()).getTitle();
                String strDate = meetingArrayList.get(getAdapterPosition()).getDate();
                String strDesc  = meetingArrayList.get(getAdapterPosition()).getDescription();
                int intCred = meetingArrayList.get(getAdapterPosition()).getCredits();

                Intent intent = new Intent(v.getContext(), MeetingDetails.class);
                intent.putExtra("STRING_NAME", strName);
                intent.putExtra("STRING_DATE", strDate);
                intent.putExtra("STRING_DESC", strDesc);
                intent.putExtra("INT_CRED", intCred);
                v.getContext().startActivity(intent);
            });

            cardMeetingImg = itemView.findViewById(R.id.cardViewMentoringImg);
            courseName = itemView.findViewById(R.id.recommended_name);
//            courseDate = itemView.findViewById(R.id.idDate);
//            coursePrice = itemView.findViewById(R.id.idCreditsTxt);
        }
    }
}
