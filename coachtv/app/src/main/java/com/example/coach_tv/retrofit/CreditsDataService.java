package com.example.coach_tv.retrofit;

import com.example.coach_tv.model.CreditPackDTO;
import com.example.coach_tv.model.MentoringDTO;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CreditsDataService {

    @GET("credits/plans")
    Call<ArrayList<CreditPackDTO>> getAll();

    @POST("credits/purchase")
    Call<Void> purchase(@Body CreditPackDTO creditsPack);
}
