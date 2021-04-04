package com.example.coach_tv;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coach_tv.Utils.Message;
import com.example.coach_tv.adapter.MentoringDetailsAdapter;
import com.example.coach_tv.model.MeetingDTO;
import com.example.coach_tv.retrofit.RetrofitInitializer;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ScheduleActivity extends AppCompatActivity {

    private RecyclerView schedulesRV;
    private MentoringDetailsAdapter mentoringDetailsAdapter;

    private ArrayList<MeetingDTO> meetingArrayList;
    private long idMentoring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mentoring_list);
        getMeetingsList();
    }

    private void getMeetingsList(){
        Intent iin= getIntent();
        Bundle b = iin.getExtras();
        if(b!=null) {
            idMentoring = (long) b.get("INT_ID_MENTORING");
        }
        Call<ArrayList<MeetingDTO>> call = new RetrofitInitializer().setMeetingDataService().getMeetingsByMentoring(idMentoring);
        Log.wtf("URL Called", call.request().url() + "");
        call.enqueue(new Callback<ArrayList<MeetingDTO>>() {
            @Override
            public void onResponse(Call<ArrayList<MeetingDTO>> call, Response<ArrayList<MeetingDTO>> response) {
                Log.e("Response", response.code()+"");
                if(response.isSuccessful()){
                    getAllSchedules(response.body());
                } else {
                    Message.printMessage(getApplicationContext(),response.errorBody().byteStream()+"");
                }
            }
            @Override
            public void onFailure(Call<ArrayList<MeetingDTO>> call, Throwable t) {
                Message.printMessage(getApplicationContext(),t.toString());
            }
        });
    }

    private void getAllSchedules(ArrayList<MeetingDTO> list){
        schedulesRV = findViewById(R.id.idMentoringSchedule);
        mentoringDetailsAdapter = new MentoringDetailsAdapter(this, list);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        schedulesRV.setLayoutManager(layoutManager);
        schedulesRV.setAdapter(mentoringDetailsAdapter);
    }
}