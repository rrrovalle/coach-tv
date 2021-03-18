package com.example.coach_tv.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coach_tv.AppActivity;
import com.example.coach_tv.CourseManagerActivity;
import com.example.coach_tv.R;
import com.example.coach_tv.adapter.MentoringAdapter;
import com.example.coach_tv.model.Mentoring;

import java.util.ArrayList;

public class MentoringFragment extends Fragment {

    private RecyclerView mentoringRV;
    private Button add;
    private TextView editMentoring;

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
        add           = getActivity().findViewById(R.id.idAddCourse);
        mentoringRV   = getActivity().findViewById(R.id.idMentoringRV);
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
        mentoringArrayList.add(new Mentoring("Minimal Culinary", "All about food.", "Food/Culinary",4, 500,R.drawable.minimal_food));
        mentoringArrayList.add(new Mentoring("Basic Code", "Learn basic skills.", "Technology",4, 650, R.drawable.code));
        mentoringArrayList.add(new Mentoring("Classical Ballet", "Put your best foot forward and try it.", "Dance/Music",3, 800, R.drawable.ballad));

        // we are initializing our adapter class and passing our array list to it.
        MentoringAdapter mentoringAdapter = new MentoringAdapter(getActivity().getApplicationContext(), mentoringArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layout manager and adapter to our recycler view.
        mentoringRV.setLayoutManager(linearLayoutManager);
        mentoringRV.setAdapter(mentoringAdapter);
    }
}