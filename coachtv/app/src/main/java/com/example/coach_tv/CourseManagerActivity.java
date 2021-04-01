package com.example.coach_tv;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.coach_tv.fragment.MentoringFragment;
import com.example.coach_tv.model.Mentoring;
import com.example.coach_tv.model.MentoringDTO;
import com.example.coach_tv.model.UserDTO;
import com.example.coach_tv.retrofit.RetrofitInitializer;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseManagerActivity extends AppCompatActivity {

    private ImageView imgReturn;
    private Button    btnSave;
    private Spinner   selectSection;
    private ImageView addIcon;
    private ImageView mentoringImg;

    /** Default Settings */
    private int IMG_REQUEST = 21;
    private Bitmap bitmap;

    /** Edit Texts */
    private EditText txtTitle;
    private EditText txtDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_course);
        initComponents();
        addListeners(this);
        fillSelect(this);
    }

    public void initComponents(){
        imgReturn         = findViewById(R.id.imgReturn);
        addIcon           = findViewById(R.id.plusIcon);
        mentoringImg      = findViewById(R.id.mentoringImage);
        btnSave           = findViewById(R.id.btnSaveProfile);
        selectSection     = findViewById(R.id.sections_select);
        txtTitle          = findViewById(R.id.idTitle);
        txtDesc           = findViewById(R.id.idDescription);
    }

    public void addListeners(Context context){
        addIcon.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, IMG_REQUEST);
        });
        imgReturn.setOnClickListener(v -> {
            Intent intent = new Intent(CourseManagerActivity.this, MentoringFragment.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); finish();
        });
        btnSave.setOnClickListener(v -> {
            if(validate()){
                MentoringDTO mentoring = new MentoringDTO();
                mentoring.setDescription(txtDesc.getText().toString());
                mentoring.setSection(selectSection.getSelectedItem().toString());
                mentoring.setTitle(txtTitle.getText().toString());
                mentoring.setRating(0f);
                mentoring.setCoach(getUser());

                /** Call register method */
                Call<Void> call = new RetrofitInitializer().setMentoringService().register(mentoring);
                Log.wtf("URL:", call.request().url() + "");
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        message("Your mentoring has been successfully offered!");
                        Intent intent = new Intent(CourseManagerActivity.this, MentoringFragment.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP); finish();
                    }

                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        message(t.toString());
                    }
                });
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data); 
        int RESULT_OK = -1;
        if(requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null){
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), path);
                mentoringImg.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void fillSelect(Context c){
        Call<List<String>> call = new RetrofitInitializer().setMentoringService().getAllSections();
        Log.wtf("URL:", call.request().url() + "");
        call.enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                //create a list of items for the spinner.
                List<String> items = response.body();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(c, android.R.layout.simple_spinner_dropdown_item, items);
                //set the spinners adapter to the previously created one.
                selectSection.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                message(t.toString());
            }
        });
    }

    private boolean validate(){
        String title = txtTitle.getText().toString();
        String desc  = txtDesc.getText().toString();
        if(!TextUtils.isEmpty(title) && !TextUtils.isEmpty(desc)){
            return true;
        }
        return false;
    }

    private UserDTO getUser(){
        SharedPreferences sharedPreferences = getSharedPreferences("userDetails", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("UserDTO", "");
        UserDTO user = gson.fromJson(json, UserDTO.class);
        return user;
    }

    private void message(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}