package com.example.coach_tv;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private Button btnRegister;
    private ImageView imgScreenReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        initComponents();
        addListener();
    }

    public void initComponents(){
        btnRegister = findViewById(R.id.button);
        imgScreenReturn = findViewById(R.id.imgScreenReturn);
    }

    public void addListener(){
        btnRegister.setOnClickListener(v -> {
            Intent intent = new Intent(this, AppActivity.class);
            startActivity(intent);
        });

        imgScreenReturn.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });
    }
}