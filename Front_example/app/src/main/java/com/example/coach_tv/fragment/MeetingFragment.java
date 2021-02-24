package com.example.coach_tv.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.coach_tv.R;
import com.example.coach_tv.adapter.MeetingAdapter;
import com.example.coach_tv.model.Meeting;

import java.util.ArrayList;

public class MeetingFragment extends Fragment {

    private RecyclerView meetingRV;

    // Array for storing data
    private ArrayList<Meeting> meetingArrayList;

    public MeetingFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_meeting, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated (@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        meetingRV = getActivity().findViewById(R.id.idMeeting);
        // here we have created new array list and added data to it.
        meetingArrayList = new ArrayList<>();
        meetingArrayList.add(new Meeting("DSA in Java", "10/02/2021", "Rodrigo", "Want to learn about: Stack, Tree, Hash Table and Array", 500, R.drawable.mkt));
        meetingArrayList.add(new Meeting("Java Course", "10/02/2021", "Pedro", "Want to learn about: OO, Methods, interfaces.", 500, R.drawable.mkt));
        meetingArrayList.add(new Meeting("C++ Course", "22/02/2021", "Robson","Want to learn about C++", 500, R.drawable.mkt));
        meetingArrayList.add(new Meeting("DSA in C++", "23/03/2021", "Guilherme", "Want to learn about DSA in C++.", 500, R.drawable.mkt));
        meetingArrayList.add(new Meeting("Kotlin for Android", "23/03/2021", "Juliane", "Want to learn about Kotlin for Android.", 500, R.drawable.mkt));
        meetingArrayList.add(new Meeting("Java for Android","01/04/2021", "Maria Carolina","Want to learn about Java for Android.", 500, R.drawable.mkt));
        meetingArrayList.add(new Meeting("HTML and CSS", "04/04/2021", "Joana","Want to learn about JS too.", 500, R.drawable.mkt));

        // we are initializing our adapter class and passing our arraylist to it.
        MeetingAdapter meetAdapter = new MeetingAdapter(getActivity().getApplicationContext(), meetingArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layoutmanager and adapter to our recycler view.
        meetingRV.setLayoutManager(linearLayoutManager);
        meetingRV.setAdapter(meetAdapter);
    }
}