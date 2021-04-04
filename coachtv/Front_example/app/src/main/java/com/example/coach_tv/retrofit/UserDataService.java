package com.example.coach_tv.retrofit;

import com.example.coach_tv.model.MentoringDTO;
import com.example.coach_tv.model.UserDTO;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserDataService {

    @POST("user/register/")
    Call<Void> register(@Body UserDTO person);

    @POST("user/login/")
    Call<UserDTO> login(@Body UserDTO person);

    @GET("user/{id}")
    Call<UserDTO> getById(@Path("id") long id);
}
