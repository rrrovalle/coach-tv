package com.example.coach_tv;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coach_tv.adapter.MentoringDetailsAdapter;
import com.example.coach_tv.model.Meeting;
import com.example.coach_tv.model.Mentoring;

import java.util.ArrayList;

public class ScheduleActivity extends AppCompatActivity {

    private RecyclerView schedulesRV;
    private MentoringDetailsAdapter mentoringDetailsAdapter;

    private ArrayList<Meeting> meetingArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mentoring_list);

        meetingArrayList = new ArrayList<>();
        meetingArrayList.add(new Meeting("DSA in Java", "21/05/2021", "Joaquim Borges","Talk about levels", 500, R.drawable.finance));
        meetingArrayList.add(new Meeting("C++", "03/05/2021", "Luciana Pereira","Talk about levels", 500, R.drawable.finance));

        getAllSchedules(meetingArrayList);
    }

    private void getAllSchedules(ArrayList<Meeting> list){
        schedulesRV = findViewById(R.id.idMentoringSchedule);
        mentoringDetailsAdapter = new MentoringDetailsAdapter(this, list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        schedulesRV.setLayoutManager(layoutManager);
        schedulesRV.setAdapter(mentoringDetailsAdapter);
    }
}