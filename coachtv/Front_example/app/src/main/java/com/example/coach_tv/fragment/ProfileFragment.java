package com.example.coach_tv.fragment;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.webkit.PermissionRequest;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.coach_tv.AppActivity;
import com.example.coach_tv.LoginActivity;
import com.example.coach_tv.MainActivity;
import com.example.coach_tv.R;
import com.example.coach_tv.Utils.FileUpload;
import com.example.coach_tv.Utils.Message;
import com.example.coach_tv.model.MeetingDTO;
import com.example.coach_tv.model.UserDTO;
import com.example.coach_tv.retrofit.RetrofitInitializer;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Multipart;

public class ProfileFragment extends Fragment {

    private Button      btnSave;
    private ImageView   imgScreenReturn;
    public static final int PICK_IMAGE = 100;

    private Bitmap bitmap;

    /** Profile Field's */
    private ImageView profileImg;
    private TextView  txtLogout;
    private TextView  txtName;
    private TextView  txtEmail;
    private TextView  txtCourses;
    private TextView  txtMeet;
    private UserDTO   user;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initComponents();
        addListeners();
        fillUserData();
        //setUserProfile();
    }

    private void fillUserData() {
        SharedPreferences sharedPreferences = this.getActivity().getSharedPreferences("userDetails", this.getContext().MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("UserDTO", "");
        user = gson.fromJson(json, UserDTO.class);
        txtName.setText(user.getName());
        txtEmail.setText(user.getEmail());
        Log.wtf("size",user.getMentorings().size()+"");
        //txtCourses.setText(user.getMentorings().size()+"");
        //getMeetings(user.getId());
    }

    private UserDTO getUser(){
        return user;
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
        txtMeet         = getActivity().findViewById(R.id.idTotalMeet);
        txtCourses      = getActivity().findViewById(R.id.idTotalCourses);
    }

    private void addListeners(){
        btnSave.setOnClickListener(v -> {
           // uploadImage();
//            Intent intent = new Intent(getActivity().getApplicationContext(), AppActivity.class);
//            startActivity(intent);
        });
        profileImg.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE);
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

        getActivity();
        if (requestCode == PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            android.net.Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            android.database.Cursor cursor = getContext().getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            if (cursor == null)
                return;

            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String filePath = cursor.getString(columnIndex);
            cursor.close();

            File file = new File(filePath);
            Log.wtf("file",file.getAbsolutePath());
            Uri path = data.getData();
            Log.wtf("url", path.toString());

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), path);
                String strImage = FileUpload.bitMapToString(bitmap);
                Bitmap bitImage = FileUpload.StringToBitMap(strImage);
                profileImg.setImageBitmap(bitmap);

                uploadImage(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void uploadImage(File image){
        long userId             = getUser().getId();
        RequestBody fileName    = RequestBody.create(MediaType.parse("multipart/form-data*"), image.getAbsolutePath());

        MultipartBody.Part profilePic = null;
        RequestBody requestFile = RequestBody.create(MediaType.parse("multipart/form-data*"), image);
        profilePic = MultipartBody.Part.createFormData("data", image.getName(), requestFile);

        Call<Void> call = new RetrofitInitializer().setMultipartService().uploadProfilePicture(userId, fileName, profilePic);
        Log.wtf("URL Called", call.request().url() + "");
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                Log.e("Response", response.code()+"");
                if(response.isSuccessful()){
                    Log.wtf("body", response.body()+"");
                   // setUserProfile();
                } else {
                    Message.printMessage(getContext(),response.errorBody().byteStream()+"");
                    Log.wtf("error",response.errorBody().byteStream()+"");
                }
            }
            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.wtf("error",t.toString());
                Message.printMessage(getContext(),t.toString());
            }
        });
    }

    private void setUserProfile(){
        Call<UserDTO> call     = new RetrofitInitializer().setUserService().getById(getUser().getId());
        Log.wtf("URL Called", call.request().url() + "");
        call.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                Log.e("Response", response.code()+"");
                if(response.isSuccessful()){
                    Log.wtf("body", response.body()+"");
                    UserDTO user        = response.body();
                    String encodedImage = user.getEncodedImage();
                    profileImg.setImageBitmap(FileUpload.StringToBitMap(encodedImage));
                } else {
                    Message.printMessage(getContext(),response.errorBody().byteStream()+"");
                }
            }
            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {
                Log.wtf("error",t.toString());
                Message.printMessage(getContext(),t.toString());
            }
        });
    }

    private void getMeetings(long id){
        Call<ArrayList<MeetingDTO>> call = new RetrofitInitializer().setMeetingDataService().getMeetingByUserId(id);
        Log.wtf("URL Called", call.request().url() + "");
        call.enqueue(new Callback<ArrayList<MeetingDTO>>() {
            @Override
            public void onResponse(Call<ArrayList<MeetingDTO>> call, Response<ArrayList<MeetingDTO>> response) {
                Log.e("Response", response.code()+"");
                if(response.isSuccessful()){
                    txtMeet.setText(response.body().size());
                } else {
                    Message.printMessage(getContext(),response.errorBody().byteStream()+"");
                    //Message.printMessage(getContext(),"Sorry, we couldn't find an account with that e-mail or password. Please double-check and try again.");
                }
            }
            @Override
            public void onFailure(Call<ArrayList<MeetingDTO>> call, Throwable t) {
                Message.printMessage(getContext(), t.toString());
            }
        });
    }
}