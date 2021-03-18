package com.example.coach_tv;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coach_tv.fragment.MeetingFragment;

public class MeetingDetails extends AppCompatActivity {

    private Button btnJoin;
    private ImageView imgScreenReturn;

    private TextView txtCourse;
    private TextView txtObservations;
    private TextView edtDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.meeting_room_details);
        txtCourse = findViewById(R.id.idCourseName);
        txtObservations = findViewById(R.id.idObservations);
        edtDate = findViewById(R.id.idDate);
        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            String name = (String) b.get("STRING_NAME");
            String date = (String) b.get("STRING_DATE");
            String obs  = (String) b.get("STRING_DESC");
            txtCourse.setText(name);
            edtDate.setText(date);
            txtObservations.setText(obs);
        }
        initComponents();
        addListeners();
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
}