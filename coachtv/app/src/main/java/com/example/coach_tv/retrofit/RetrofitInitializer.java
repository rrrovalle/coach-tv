package com.example.coach_tv.retrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInitializer {
    private final Retrofit retrofit;
    private static final String BASE_URL = "http://10.0.2.2:8080/api/";

    public RetrofitInitializer() {
        retrofit = new retrofit2.Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public UserDataService setUserService() {
        return retrofit.create(UserDataService.class);
    }

    public MentoringDataService setMentoringService() {
        return retrofit.create(MentoringDataService.class);
    }

    public CreditsDataService setCreditsService() {
        return retrofit.create(CreditsDataService.class);
    }
}
