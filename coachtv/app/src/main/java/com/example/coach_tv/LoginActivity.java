package com.example.coach_tv;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity  extends AppCompatActivity {

    private TextView btnLogin;
    private TextView txtReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        initComponents();
        addListener();
    }

    private void initComponents(){
        btnLogin = findViewById(R.id.btnLogin);
        txtReg   = findViewById(R.id.txtRegister);
    }

    private void addListener(){
        btnLogin.setOnClickListener(v -> {
            Intent intent = new Intent(this, AppActivity.class);
            startActivity(intent);
        });

        txtReg.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}