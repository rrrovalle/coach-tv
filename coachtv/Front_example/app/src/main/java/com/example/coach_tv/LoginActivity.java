package com.example.coach_tv;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coach_tv.Utils.Message;
import com.example.coach_tv.model.UserDTO;
import com.example.coach_tv.retrofit.RetrofitInitializer;
import com.google.gson.Gson;

import org.json.JSONObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity  extends AppCompatActivity {

    private TextView btnLogin;
    private TextView txtReg;
    private TextView txtEmail;
    private TextView txtPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        initComponents();
        addListener();
    }

    private void initComponents(){
        btnLogin    = findViewById(R.id.btnLogin);
        txtReg      = findViewById(R.id.txtRegister);
        txtEmail    = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
    }

    private void addListener(){
        btnLogin.setOnClickListener(v -> {
            if(!TextUtils.isEmpty(txtEmail.getText().toString()) && !TextUtils.isEmpty(txtPassword.getText().toString())){
                UserDTO user = new UserDTO();
                user.setName(null);
                user.setEmail(txtEmail.getText().toString());
                user.setPassword(txtPassword.getText().toString());
                user.setBirthday(null);
                user.setCredits(0);
                /** Call retrofit */
                Call<UserDTO> call = new RetrofitInitializer().setUserService().login(user);
                Log.wtf("URL Called", call.request().url() + "");
                call.enqueue(new Callback<UserDTO>() {
                    @Override
                    public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                        Log.e("Response", response.code()+"");
                        if(response.isSuccessful()){
                            Log.e("body",response.body()+"");
                            SharedPreferences sharedPreferences = getSharedPreferences("userDetails", MODE_PRIVATE);
                            saveUserDetails(sharedPreferences, response.body());
                            showMainScreen();
                        } else {
                            Log.wtf("ERROR", response.errorBody().byteStream()+"");
                            Message.printMessage(getApplicationContext(),response.errorBody().byteStream()+"");
                        }
                    }
                    @Override
                    public void onFailure(Call<UserDTO> call, Throwable t) {
                        Message.printMessage(getApplicationContext(), t.toString());
                    }
                });
            } else {
                Message.printMessage(getApplicationContext(),"Please, make sure all fields are filled in correctly.");
            }
        });
        txtReg.setOnClickListener(v -> {
            Intent intent = new Intent(this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void showMainScreen() {
        Intent intent = new Intent(this, AppActivity.class);
        startActivity(intent);
    }

    private void saveUserDetails(SharedPreferences preferences, UserDTO userDTO) {
        SharedPreferences.Editor editor = preferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(userDTO);
        editor.putString("UserDTO", json);
        editor.apply();
    }
}