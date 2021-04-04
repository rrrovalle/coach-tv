package com.example.coach_tv.retrofit;

import com.example.coach_tv.model.MeetingDTO;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface MeetingDataService {

    @POST("meeting/schedule/{mentoringId}/user/{userId}")
    Call<Void> register(@Path("mentoringId") long mentoringId,
                        @Path("userId") long userId,
                        @Body MeetingDTO meeting);

    @GET("meeting/user/{userId}")
    Call<ArrayList<MeetingDTO>> getMeetingByUserId(@Path("userId") long userId);

    @GET("meeting/mentoring/{mentoringId}")
    Call<ArrayList<MeetingDTO>> getMeetingsByMentoring(@Path("mentoringId") long mentoringId);
}
