package com.example.coach_tv.retrofit;

import androidx.annotation.StringRes;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body; 
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;

public interface MultipartDataService {

    @POST("multipart/upload/user/profile/encoded/{userId}")
    Call<Void> uploadProfilePhoto(@Header("Content-Type") String contentType,
                                  @Path("userId") long id, @Body String encodedImage);

    @Multipart
    @POST("multipart/upload/user/profile/{userId}")
    Call<Void> uploadProfilePicture(@Path("userId") long userId,
                                    @Part("fileName") RequestBody fileName,
                                    @Part MultipartBody.Part data);
}