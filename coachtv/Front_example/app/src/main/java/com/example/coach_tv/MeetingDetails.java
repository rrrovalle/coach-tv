package com.example.coach_tv;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coach_tv.Utils.IconManager;
import com.example.coach_tv.Utils.Message;
import com.example.coach_tv.fragment.MeetingFragment;
import com.example.coach_tv.model.MentoringDTO;
import com.example.coach_tv.model.UserDTO;
import com.example.coach_tv.retrofit.RetrofitInitializer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MeetingDetails extends AppCompatActivity {

    private Button btnJoin;
    private ImageView imgScreenReturn;

    private TextView txtCourse, txtObservations, edtDate, txtPrice,
            txtMentoringDesc, txtSection, txtCoach;
    private long mentoringId;
    private ImageView imgMentoring;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meeting_room_details);

        initComponents();
        addListeners();
        loadDetails();
    }

    private void initComponents(){
        btnJoin         = findViewById(R.id.btnJoin);
        imgScreenReturn = findViewById(R.id.imgScreenReturn);
    }

    private void addListeners(){
        btnJoin.setOnClickListener(v -> {
            Intent intent = new Intent(this, AppActivity.class);
            startActivity(intent);
        });
        imgScreenReturn.setOnClickListener(v -> {
            Intent intent = new Intent(MeetingDetails.this, MeetingFragment.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); finish();
        });
    }

    private void loadDetails(){
        txtCourse = findViewById(R.id.idCourseName);
        txtObservations = findViewById(R.id.idObservations);
        txtMentoringDesc = findViewById(R.id.idDesc);
        edtDate = findViewById(R.id.idDate);
        txtPrice = findViewById(R.id.idTextPrice);
        txtSection = findViewById(R.id.idSection);
        txtCoach   = findViewById(R.id.idCoach);
        imgMentoring = findViewById(R.id.idMentoringImage);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null) {
            String name     = (String) b.get("STRING_NAME");
            String coach    = (String) b.get("STRING_COACH");
            String obs      = (String) b.get("STRING_DESC");
            String section  = (String) b.get("STRING_SECTION");
            String mtDesc   = (String) b.get("STRING_MENTORING_DESC");
            mentoringId     = (long) b.get("LONG_ID");
            txtCourse.setText(name);
            txtObservations.setText(obs);
            txtMentoringDesc.setText(mtDesc);
            txtSection.setText(section);
            txtPrice.setText(300+"/h");
            txtCoach.setText(coach);
            imgMentoring.setImageResource(IconManager.getIcon(section));
        }
    }
}