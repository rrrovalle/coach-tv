package com.example.coach_tv;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coach_tv.Utils.Mask;
import com.example.coach_tv.Utils.Message;
import com.example.coach_tv.Utils.Validate;
import com.example.coach_tv.model.UserDTO;
import com.example.coach_tv.retrofit.RetrofitInitializer;

import java.time.LocalDate;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private Button btnRegister;
    private ImageView imgScreenReturn;

    /**
     * Screen fields
     **/
    private EditText txtName, txtBirthday, txtEmail, txtPassword, txtConfirmPassword;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        initComponents();
        addListener(this);
    }

    public void initComponents() {
        btnRegister = findViewById(R.id.button);
        imgScreenReturn = findViewById(R.id.imgScreenReturn);
        /** TextViews **/
        txtName = findViewById(R.id.txtName);
        txtBirthday = findViewById(R.id.txtBirthday);
        txtBirthday.addTextChangedListener(Mask.insert("##/##/####", txtBirthday));
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        txtConfirmPassword = findViewById(R.id.txtConfirmPassword);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addListener(Context context) {
        btnRegister.setOnClickListener(v -> {
            String password = txtPassword.getText().toString();
            String confirmPassword = txtConfirmPassword.getText().toString();
            if (Validate.validate(password, confirmPassword)) {
                if (password.equals(confirmPassword)) {
                    UserDTO user = new UserDTO();
                    user.setName(txtName.getText().toString());
                    user.setEmail(txtEmail.getText().toString());
                    user.setPassword(txtPassword.getText().toString());
                    user.setBirthday(txtBirthday.getText().toString());
                    user.setCredits(0);

                    Call<Void> call = new RetrofitInitializer().setUserService().register(user);
                    Log.wtf("URL Called", call.request().url() + "");
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            if(response.isSuccessful()){
                                Message.printMessage(context, "Your account has been successfully created!");
                                Intent intent = new Intent(context, MainActivity.class);
                                startActivity(intent);
                            } else {
                                Message.printMessage(getApplicationContext(),"Oops, something happened differently than expected. Try again");
                                //Message.printMessage(getApplicationContext(),response.errorBody().byteStream()+"");
                            }
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Message.printMessage(context, t.toString());
                        }
                    });
                    resetFields();
                } else {
                    Toast.makeText(context, "Please, double-check your password.", Toast.LENGTH_LONG).show();
                }
            } else {
                Toast.makeText(context, "Please, make sure all fields are filled in correctly.", Toast.LENGTH_LONG).show();
            }
        });

        imgScreenReturn.setOnClickListener(v -> {
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });
    }

    public void resetFields() {
        txtName.setText("");
        txtEmail.setText("");
        txtBirthday.setText("");
        txtPassword.setText("");
        txtConfirmPassword.setText("");
    }
}
