package com.example.coach_tv.fragment;

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

import com.example.coach_tv.R;
import com.example.coach_tv.Utils.Message;
import com.example.coach_tv.adapter.MeetingAdapter;
import com.example.coach_tv.model.MeetingDTO;
import com.example.coach_tv.model.UserDTO;
import com.example.coach_tv.retrofit.RetrofitInitializer;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MeetingFragment extends Fragment {

    private RecyclerView meetingRV;
    // Array for storing data
    private ArrayList<MeetingDTO> meetingArrayList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_meeting, container, false);

        return rootView;
    }

    @Override
    public void onActivityCreated (@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
        meetingRV = getActivity().findViewById(R.id.idMeeting);

        Call<ArrayList<MeetingDTO>> call = new RetrofitInitializer().setMeetingDataService().getMeetingByUserId(getUserData().getId());
        Log.wtf("URL Called", call.request().url() + "");
        call.enqueue(new Callback<ArrayList<MeetingDTO>>() {
            @Override
            public void onResponse(Call<ArrayList<MeetingDTO>> call, Response<ArrayList<MeetingDTO>> response) {
                Log.e("Response", response.code()+"");
                if(response.isSuccessful()){
                    meetingArrayList = response.body();
                    createCardView(meetingArrayList);
                } else {
                    Message.printMessage(getContext(),response.errorBody().byteStream()+"");
                    //Message.printMessage(getContext(),"Sorry, we couldn't find an account with that e-mail or password. Please double-check and try again.");
                }
            }
            @Override
            public void onFailure(Call<ArrayList<MeetingDTO>> call, Throwable t) {
                Message.printMessage(getContext(), t.toString());
            }
        });
    }

    private void createCardView(ArrayList<MeetingDTO> meetings) {
        MeetingAdapter meetAdapter = new MeetingAdapter(getActivity().getApplicationContext(), meetings);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        meetingRV.setLayoutManager(linearLayoutManager);
        meetingRV.setAdapter(meetAdapter);
    }

    private UserDTO getUserData(){
        SharedPreferences sharedPreferences = this.getContext().getSharedPreferences("userDetails", this.getContext().MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("UserDTO", "");
        UserDTO user = gson.fromJson(json, UserDTO.class);
        return user;
    }
}