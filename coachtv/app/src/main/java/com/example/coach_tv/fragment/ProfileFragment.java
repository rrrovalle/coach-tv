package com.example.coach_tv.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.coach_tv.AppActivity;
import com.example.coach_tv.MainActivity;
import com.example.coach_tv.R;

public class ProfileFragment extends Fragment {

    private Button btnSave;
    private ImageView imgScreenReturn;
    private TextView txtLogout;

    public ProfileFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initComponents();
        addListeners();
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
        txtLogout       = getActivity().findViewById(R.id.txtLogout);
    }

    private void addListeners(){
        btnSave.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity().getApplicationContext(), AppActivity.class);
            startActivity(intent);
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
}