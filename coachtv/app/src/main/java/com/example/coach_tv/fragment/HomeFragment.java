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
import com.example.coach_tv.adapter.CourseAdapter;
import com.example.coach_tv.adapter.PopularAdapter;
import com.example.coach_tv.adapter.RecommendedAdapter;
import com.example.coach_tv.model.Mentoring;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private RecyclerView popularRecyclerView, recommendedRecyclerView, allMenuRecyclerView;

    private PopularAdapter      popularAdapter;
    private RecommendedAdapter  recommendedAdapter;
    private CourseAdapter       allMenuAdapter;

    private ImageView myMentoring;

    // Arraylist for storing data
    private ArrayList<Mentoring> mentoringArrayList;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated (@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        // here we have created new array list and added data to it.
        mentoringArrayList = new ArrayList<>();
        mentoringArrayList.add(new Mentoring("Basic Code", "Learn basic skills.", "Technology",4, 650, R.drawable.code));
        mentoringArrayList.add(new Mentoring("Classical Ballet", "Put your best foot forward and try it.", "Dance/Music",3, 800, R.drawable.ballad));
        mentoringArrayList.add(new Mentoring("Executive Finance", "Fundamental concepts.", "Entrepreneur",4,700, R.drawable.finance));
        mentoringArrayList.add(new Mentoring("Minimal Culinary", "All about food.", "Food/Culinary",4, 500,R.drawable.minimal_food));
        mentoringArrayList.add(new Mentoring("Bartender Course", "Cocktail course.", "Drinks" ,5,900, R.drawable.barman));
        mentoringArrayList.add(new Mentoring("Oil Painting","Improve your child's art skills", "Art", 4, 500, R.drawable.painting_studio));
        mentoringArrayList.add(new Mentoring("First Aid Training", "Are your first aid skills up-to-date?", "Technology", 4, 100,R.drawable.med_course));

        getPopularData(mentoringArrayList);
        getRecommendedData(mentoringArrayList);
        getAllMenu(mentoringArrayList);
    }

    private void getPopularData(ArrayList<Mentoring> popularList){
        popularRecyclerView = getView().findViewById(R.id.popular_recycler);
        popularAdapter = new PopularAdapter(getActivity().getApplicationContext(), popularList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        popularRecyclerView.setLayoutManager(layoutManager);
        popularRecyclerView.setAdapter(popularAdapter);
    }

    private void getRecommendedData(ArrayList<Mentoring> recommendedList){
        recommendedRecyclerView = getView().findViewById(R.id.recommended_recycler);
        recommendedAdapter = new RecommendedAdapter(getActivity().getApplicationContext(), recommendedList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recommendedRecyclerView.setLayoutManager(layoutManager);
        recommendedRecyclerView.setAdapter(recommendedAdapter);
    }

    private void getAllMenu(ArrayList<Mentoring> allMenuList){
        allMenuRecyclerView = getView().findViewById(R.id.idCourse);
        allMenuAdapter = new CourseAdapter(getActivity().getApplicationContext(), allMenuList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        allMenuRecyclerView.setLayoutManager(layoutManager);
        allMenuRecyclerView.setAdapter(allMenuAdapter);
        allMenuAdapter.notifyDataSetChanged();
    }
}