package com.example.coach_tv.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SearchView;

import com.example.coach_tv.CourseManagerActivity;
import com.example.coach_tv.R;
import com.example.coach_tv.adapter.CourseAdapter;
import com.example.coach_tv.adapter.PopularAdapter;
import com.example.coach_tv.adapter.RecommendedAdapter;
import com.example.coach_tv.model.Mentoring;
import com.example.coach_tv.model.MentoringDTO;
import com.example.coach_tv.model.UserDTO;
import com.example.coach_tv.retrofit.RetrofitInitializer;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private RecyclerView popularRecyclerView, recommendedRecyclerView, allMenuRecyclerView;

    private PopularAdapter      popularAdapter;
    private RecommendedAdapter  recommendedAdapter;
    private CourseAdapter       allMenuAdapter;

    private ImageView myMentoring;

    // Arraylist for storing data
    private ArrayList<MentoringDTO> mentoringArrayList;

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

        Call<ArrayList<MentoringDTO>> call = new RetrofitInitializer().setMentoringService().getAll();
        Log.wtf("URL Called", call.request().url() + "");
        call.enqueue(new Callback<ArrayList<MentoringDTO>>() {
            @Override
            public void onResponse(Call<ArrayList<MentoringDTO>> call, Response<ArrayList<MentoringDTO>> response) {
                Log.e("Response", response.code()+"");
                if(response.isSuccessful()){
                    mentoringArrayList = response.body();

                    getPopularData(mentoringArrayList);
                    getRecommendedData(mentoringArrayList);
                    getAllMenu(mentoringArrayList);
                } else {
                   // message("Sorry, we couldn't find an account with that e-mail or password. Please double-check and try again.");
                }
            }

            @Override
            public void onFailure(Call<ArrayList<MentoringDTO>> call, Throwable t) {
                //message(t.toString());
            }

        });

    }

    private void getPopularData(ArrayList<MentoringDTO> popularList){
        popularRecyclerView = getView().findViewById(R.id.popular_recycler);
        popularAdapter = new PopularAdapter(getActivity().getApplicationContext(), popularList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        popularRecyclerView.setLayoutManager(layoutManager);
        popularRecyclerView.setAdapter(popularAdapter);
    }

    private void getRecommendedData(ArrayList<MentoringDTO> recommendedList){
        recommendedRecyclerView = getView().findViewById(R.id.recommended_recycler);
        recommendedAdapter = new RecommendedAdapter(getActivity().getApplicationContext(), recommendedList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        recommendedRecyclerView.setLayoutManager(layoutManager);
        recommendedRecyclerView.setAdapter(recommendedAdapter);
    }

    private void getAllMenu(ArrayList<MentoringDTO> allMenuList){
        allMenuRecyclerView = getView().findViewById(R.id.idCourse);
        allMenuAdapter = new CourseAdapter(getActivity().getApplicationContext(), allMenuList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        allMenuRecyclerView.setLayoutManager(layoutManager);
        allMenuRecyclerView.setAdapter(allMenuAdapter);
        allMenuAdapter.notifyDataSetChanged();
    }
}