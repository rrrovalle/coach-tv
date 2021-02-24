package com.example.coach_tv.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.coach_tv.CourseManagerActivity;
import com.example.coach_tv.R;
import com.example.coach_tv.adapter.MentoringAdapter;
import com.example.coach_tv.model.Mentoring;

import java.util.ArrayList;

public class MentoringFragment extends Fragment {

    private RecyclerView mentoringRV;
    private ImageView add;

    // Arraylist for storing data
    private ArrayList<Mentoring> mentoringArrayList;

    public MentoringFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_mentorings, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated (@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        initComponents();
        addListeners();
        createCardView();
    }

    private void initComponents(){
        add = getActivity().findViewById(R.id.idAddCourse);
        mentoringRV  = getActivity().findViewById(R.id.idMentoringRV);
    }

    private void addListeners(){
        add.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity().getApplicationContext(), CourseManagerActivity.class);
            startActivity(intent);
        });
    }

    private void createCardView(){
        // here we have created new array list and added data to it.
        mentoringArrayList = new ArrayList<>(); 
        mentoringArrayList.add(new Mentoring("DSA in Java", "Our DSA tutorial will guide you to learn different types of data structures and algorithms and their implementations", "Technology",4, R.drawable.food));
        mentoringArrayList.add(new Mentoring("Java Course", "Build confidence in your programming skills through interactive coding tasks. Master Java while developing an extensive project portfolio", "Technology",3, R.drawable.fin));
        mentoringArrayList.add(new Mentoring("C++ Course", "This course will start with the fundamental programming concepts before digging deeper into the more advanced C++ topics.", "Technology",4, R.drawable.mkt));
        mentoringArrayList.add(new Mentoring("DSA in C++", "C++ implementation of various data structures and algorithms", "Technology",4, R.drawable.food));
        mentoringArrayList.add(new Mentoring("Kotlin for Android", "Good course", "Technology" ,4, R.drawable.fin));
        mentoringArrayList.add(new Mentoring("Java for Android","Learn, Analyse and Implement", "Technology", 4, R.drawable.mkt));
        mentoringArrayList.add(new Mentoring("HTML and CSS", "Html, css, ajax and JS.", "Technology", 4, R.drawable.mkt));

        // we are initializing our adapter class and passing our arraylist to it.
        MentoringAdapter mentoringAdapter = new MentoringAdapter(getActivity().getApplicationContext(), mentoringArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layout manager and adapter to our recycler view.
        mentoringRV.setLayoutManager(linearLayoutManager);
        mentoringRV.setAdapter(mentoringAdapter);
    }
}