package com.example.coach_tv;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.coach_tv.fragment.MentoringFragment;

public class CourseManagerActivity extends AppCompatActivity {

    private ImageView imgReturn;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_course);
        initComponents();
        addListeners();
    }

    public void initComponents(){
        imgReturn  = findViewById(R.id.imgReturn);
        btnSave    = findViewById(R.id.btnSaveProfile);
    }

    public void addListeners(){
        imgReturn.setOnClickListener(v -> {
            Intent intent = new Intent(CourseManagerActivity.this, MentoringFragment.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); finish();
        });
        btnSave.setOnClickListener(v -> {
            Intent intent = new Intent(CourseManagerActivity.this, MentoringFragment.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); finish();
        });
    }
}