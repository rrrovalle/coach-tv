package com.example.coach_tv.retrofit;

import com.example.coach_tv.model.MentoringDTO;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MentoringDataService {

    @POST("mentoring/register")
    Call<Void> register(@Body MentoringDTO mentoring);

    @GET("mentoring")
    Call<ArrayList<MentoringDTO>> getAll();

    @GET("mentoring/sections")
    Call<List<String>> getAllSections();

    @GET("mentoring/{id}")
    Call<MentoringDTO> getById(@Path("id") long mentoringId);
}
