package com.example.coach_tv;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coach_tv.fragment.HomeFragment;
import com.example.coach_tv.fragment.MeetingFragment;
import com.example.coach_tv.fragment.MentoringFragment;

public class MentoringDetails extends AppCompatActivity {

    private Button btnSave;
    private ImageView imgScreenReturn;

    private TextView txtCourse;
    private TextView txtSection;
    private TextView txtDesc;
    private TextView txtRate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mentoring_details_card);

        txtCourse = findViewById(R.id.idCourseName);
        txtDesc = findViewById(R.id.idDesc);
        txtSection = findViewById(R.id.idSection);
        //txtRate = findViewById(R.id.idTVCourseRating);

        Intent iin= getIntent();
        Bundle b = iin.getExtras();

        if(b!=null)
        {
            String name = (String) b.get("STRING_NAME");
            String section = (String) b.get("STRING_SECTION");
            String obs  = (String) b.get("STRING_DESC");
            //int rate = (int) b.get("INT_RATE");

            txtCourse.setText(name);
            txtSection.setText(section);
            //txtRate.setText(""+rate);
            txtDesc.setText(obs);
        }

        initComponents();
        addListeners();
    }

    private void initComponents(){
        btnSave         = findViewById(R.id.btnBuy);
        imgScreenReturn = findViewById(R.id.imgReturn);
    }

    private void addListeners(){
        btnSave.setOnClickListener(v -> {
            startActivity(new Intent(this, AppActivity.class));
        });
        imgScreenReturn.setOnClickListener(v -> {
            startActivity(new Intent(this, AppActivity.class));
        });
    }
}