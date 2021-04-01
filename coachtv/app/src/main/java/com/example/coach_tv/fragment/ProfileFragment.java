package com.example.coach_tv.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coach_tv.AppActivity;
import com.example.coach_tv.LoginActivity;
import com.example.coach_tv.MainActivity;
import com.example.coach_tv.R;
import com.example.coach_tv.model.UserDTO;
import com.google.gson.Gson;

import java.io.IOException;

public class ProfileFragment extends Fragment {

    private Button      btnSave;
    private ImageView   imgScreenReturn;
    private int IMG_REQUEST = 21;

    private Bitmap bitmap;

    /** Profile Field's */
    private ImageView profileImg;
    private TextView  txtLogout;
    private TextView  txtName;
    private TextView  txtEmail;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initComponents();
        addListeners();
        fillUserData();
    }

    private void fillUserData() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("userDetails", this.getContext().MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("UserDTO", "");
        UserDTO user = gson.fromJson(json, UserDTO.class);
        txtName.setText(user.getName());
        txtEmail.setText(user.getEmail());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    private void initComponents(){
        btnSave         = getActivity().findViewById(R.id.btnSaveProfile);
        imgScreenReturn = getActivity().findViewById(R.id.imgScreenReturn);
        profileImg      = getActivity().findViewById(R.id.profileImage);
        txtLogout       = getActivity().findViewById(R.id.txtLogout);
        txtName         = getActivity().findViewById(R.id.txtUsername);
        txtEmail        = getActivity().findViewById(R.id.txtEmail);
    }

    private void addListeners(){
        btnSave.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity().getApplicationContext(), AppActivity.class);
            startActivity(intent);
        });

        profileImg.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(intent, IMG_REQUEST);
        });

        imgScreenReturn.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity().getApplicationContext(), AppActivity.class);
            startActivity(intent);
        });

        txtLogout.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        int RESULT_OK = -1;
        if(requestCode == IMG_REQUEST && resultCode == RESULT_OK && data != null){
            Uri path = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), path);
                profileImg.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}