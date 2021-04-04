package com.example.coach_tv;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coach_tv.Utils.Message;
import com.example.coach_tv.model.MeetingDTO;
import com.example.coach_tv.model.UserDTO;
import com.example.coach_tv.retrofit.RetrofitInitializer;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MentoringDetails extends AppCompatActivity {

    private Button btnSave;
    private ImageView imgScreenReturn;

    private TextView txtCourse;
    private TextView txtSection;
    private TextView txtDesc;
    private TextView txtCoach;
    private TextView txtPrice;
    private TextView idObs;

    private int idMentoring;
    private int idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mentoring_details_card);

        txtCourse   = findViewById(R.id.idCourseName);
        txtDesc     = findViewById(R.id.idDesc);
        txtSection  = findViewById(R.id.idSection);
        txtCoach    = findViewById(R.id.txtCoach);
        txtPrice    = findViewById(R.id.idTextPrice);

        Intent in= getIntent();
        Bundle b = in.getExtras();

        if(b!=null) {
            String name     = (String) b.get("STRING_NAME");
            String section  = (String) b.get("STRING_SECTION");
            String obs      = (String) b.get("STRING_DESC");
            String coach    = (String) b.get("STRING_COACH");
            String price    = (String) b.get("STRING_PRICE");
            idMentoring = (int)    b.get("INT_ID_MENTORING");

            txtCourse.setText(name);
            txtSection.setText(section);
            txtDesc.setText(obs);
            txtCoach.setText(coach);
            txtPrice.setText(price);
        }
        initComponents();
        addListeners();
    }

    private void initComponents(){
        btnSave         = findViewById(R.id.btnBuy);
        imgScreenReturn = findViewById(R.id.imgReturn);
        idObs           = findViewById(R.id.idObservations);
    }

    private void addListeners(){
        btnSave.setOnClickListener(v -> {
            long idUser = getUserData().getId();
            /** Create Meeting object */
            MeetingDTO meeting = new MeetingDTO();
            meeting.setPrice(300);
            meeting.setDuration(60);
            meeting.setStartTime(null);
            meeting.setDescription(idObs.getText().toString());

            Call<Void> call = new RetrofitInitializer().setMeetingDataService().register(idMentoring, idUser, meeting);
            Log.wtf("URL Called", call.request().url() + "");
            call.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    Log.e("Response", response.code()+"");
                    if(response.isSuccessful()){
                        Log.e("body",response.body()+"");
                        Message.printMessage(getApplicationContext(),"The mentoring has been added to your meeting list!");
                        startActivity(new Intent(getApplicationContext(), AppActivity.class));
                    } else {
                        Log.wtf("else",response.errorBody().byteStream()+"");
                        Message.printMessage(getApplicationContext(),response.errorBody().byteStream()+"");
                        //Message.printMessage(getApplicationContext(),"Sorry, you can't purchase your own mentoring. Please, select another me.");
                    }
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Log.wtf("fail",t.toString());
                    Message.printMessage(getApplicationContext(),t.toString());
                }

            });
        });
        imgScreenReturn.setOnClickListener(v -> {
            startActivity(new Intent(this, AppActivity.class));
        });
    }

    private UserDTO getUserData(){
        SharedPreferences sharedPreferences = getSharedPreferences("userDetails", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("UserDTO", "");
        UserDTO user = gson.fromJson(json, UserDTO.class);
        return user;
    }
}