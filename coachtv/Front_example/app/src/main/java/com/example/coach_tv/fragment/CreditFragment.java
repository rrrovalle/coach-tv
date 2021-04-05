package com.example.coach_tv.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.coach_tv.CourseManagerActivity;
import com.example.coach_tv.R;
import com.example.coach_tv.Utils.Message;
import com.example.coach_tv.adapter.CreditsAdapter;
import com.example.coach_tv.adapter.PopularAdapter;
import com.example.coach_tv.model.CreditPackDTO;
import com.example.coach_tv.model.MentoringDTO;
import com.example.coach_tv.model.UserDTO;
import com.example.coach_tv.retrofit.RetrofitInitializer;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreditFragment extends Fragment {

    private RecyclerView creditsRecyclerView;
    private CreditsAdapter creditsAdapter;
    private TextView txtCredits;
    private ArrayList<CreditPackDTO> creditPackList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_credits, container, false);
    }

    @Override
    public void onActivityCreated (@Nullable Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);

        getUserBalance();
        Call<ArrayList<CreditPackDTO>> call = new RetrofitInitializer().setCreditsService().getAll();
        Log.wtf("URL Called", call.request().url() + "");
        call.enqueue(new Callback<ArrayList<CreditPackDTO>>() {
            @Override
            public void onResponse(Call<ArrayList<CreditPackDTO>> call, Response<ArrayList<CreditPackDTO>> response) {
                Log.e("Response", response.code()+"");
                if(response.isSuccessful()){
                    creditPackList = response.body();
                    getCreditsPackOptions(creditPackList);
                } else {
                    Message.printMessage(getContext(),"Oops, something happened differently than expected. Try again");
                }
            }
            @Override
            public void onFailure(Call<ArrayList<CreditPackDTO>> call, Throwable t) {
                Message.printMessage(getContext(), t.toString());
            }
        });
    }

    private void getCreditsPackOptions(ArrayList<CreditPackDTO> creditsList){
        creditsRecyclerView = getView().findViewById(R.id.credits_recycler);
        creditsAdapter      = new CreditsAdapter(getActivity().getApplicationContext(), creditsList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        creditsRecyclerView.setLayoutManager(layoutManager);
        creditsRecyclerView.setAdapter(creditsAdapter);
    }

    private void getUserBalance() {
        SharedPreferences sharedPreferences = this.getContext().getSharedPreferences("userDetails", this.getContext().MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("UserDTO", "");
        UserDTO user = gson.fromJson(json, UserDTO.class);
        /** Get to recover fresh user data */
        Call<UserDTO> call = new RetrofitInitializer().setUserService().getById(user.getId());
        Log.wtf("URL Called", call.request().url() + "");
        call.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                Log.e("Response", response.code()+"");
                if(response.isSuccessful()){
                    UserDTO user = response.body();
                    updateUserBalance(user.getCredits());
                } else {
                    //Message.printMessage(getContext(),response.errorBody().byteStream()+"");
                    Message.printMessage(getContext(),"Sorry, we couldn't retrieve your balance. Try again");
                }
            }
            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {
                Message.printMessage(getContext(),t.toString());
            }
        });
    }

    private void updateUserBalance(float balance){
        txtCredits = getActivity().findViewById(R.id.idTotalCredits);
        txtCredits.setText(balance+"");
    }
}