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
import android.widget.TextView;

import com.example.coach_tv.CourseManagerActivity;
import com.example.coach_tv.R;
import com.example.coach_tv.Utils.Message;
import com.example.coach_tv.adapter.MentoringAdapter;
import com.example.coach_tv.model.MentoringDTO;
import com.example.coach_tv.model.UserDTO;
import com.example.coach_tv.retrofit.RetrofitInitializer;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MentoringFragment extends Fragment {

    private RecyclerView mentoringRV;
    private Button add;
    private TextView editMentoring;

    // Arraylist for storing data
    private ArrayList<MentoringDTO> mentoringArrayList;

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
        getUserSession();
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

    private void createCardView(UserDTO user){
        // here we have created new array list and added data to it.
        mentoringArrayList = (ArrayList<MentoringDTO>) user.getMentorings();
        // we are initializing our adapter class and passing our array list to it.
        MentoringAdapter mentoringAdapter = new MentoringAdapter(getActivity().getApplicationContext(), mentoringArrayList);

        // below line is for setting a layout manager for our recycler view.
        // here we are creating vertical list so we will provide orientation as vertical
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);

        // in below two lines we are setting layout manager and adapter to our recycler view.
        mentoringRV.setLayoutManager(linearLayoutManager);
        mentoringRV.setAdapter(mentoringAdapter);
    }

    private void getUserSession() {
        SharedPreferences sharedPreferences = this.getContext().getSharedPreferences("userDetails", this.getContext().MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("UserDTO", "");
        UserDTO user = gson.fromJson(json, UserDTO.class);
        /** Get to recover fresh user data */
        Call<UserDTO> call = new RetrofitInitializer().setUserService().getById(user.getId());
        Log.wtf("URL Called", call.request().url() + "");
        call.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                Log.e("Response", response.code()+"");
                if(response.isSuccessful()){
                    UserDTO user = response.body();
                    createCardView(user);
                } else {
                    Message.printMessage(getContext(),response.errorBody().byteStream()+"");
                    //Message.printMessage(getContext(),"Sorry, we couldn't find an account with that e-mail or password. Please double-check and try again.");
                }
            }
            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {
                Message.printMessage(getContext(),t.toString());
            }
        });
    }
}