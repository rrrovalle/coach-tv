package com.example.coach_tv;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MentoringDetails extends AppCompatActivity {

    private Button btnSave;
    private ImageView imgScreenReturn;

    private TextView txtCourse;
    private TextView txtSection;
    private TextView txtDesc;
    private TextView txtCoach;
    private TextView txtPrice;

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
            String name    = (String) b.get("STRING_NAME");
            String section = (String) b.get("STRING_SECTION");
            String obs     = (String) b.get("STRING_DESC");
            String coach   = (String) b.get("STRING_COACH");
            String price   = (String) b.get("STRING_PRICE");
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