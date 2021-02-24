package com.example.coach_tv;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MeetingDetails extends AppCompatActivity {

    private Button btnSave;
    private ImageView imgScreenReturn;

    private TextView txtCourse;
    private TextView txtObservations;
    private EditText edtDate;

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
        btnSave         = findViewById(R.id.btnBuy);
        imgScreenReturn = findViewById(R.id.imgScreenReturn);
    }

    private void addListeners(){

        btnSave.setOnClickListener(v -> {
            Intent intent = new Intent(this, AppActivity.class);
            startActivity(intent);
        });

        imgScreenReturn.setOnClickListener(v -> {
            startActivity(new Intent(this, AppActivity.class));
        });
    }
}