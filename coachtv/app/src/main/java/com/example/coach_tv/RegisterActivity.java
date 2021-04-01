package com.example.coach_tv;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coach_tv.model.UserDTO;
import com.example.coach_tv.retrofit.RetrofitInitializer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private Button btnRegister;
    private ImageView imgScreenReturn;

    /** Screen fields **/
    private TextView txtName;
    private TextView txtBirthday;
    private TextView txtEmail;
    private TextView txtPassword;
    private TextView txtConfirmPassword;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        initComponents();
        addListener(this);
    }

    public void initComponents(){
        btnRegister = findViewById(R.id.button);
        imgScreenReturn = findViewById(R.id.imgScreenReturn);
        /** TextViews **/
        txtName = findViewById(R.id.txtName);
        txtBirthday = findViewById(R.id.txtBirthday);
        txtEmail = findViewById(R.id.txtEmail);
        txtPassword = findViewById(R.id.txtPassword);
        txtConfirmPassword = findViewById(R.id.txtConfirmPassword);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void addListener(Context context){
        btnRegister.setOnClickListener(v -> {
            if(validate()) {
                String password = txtPassword.getText().toString();
                String confirmPassword = txtConfirmPassword.getText().toString();
                if (password.equals(confirmPassword)) {
                    UserDTO user = new UserDTO();
                    user.setName(txtName.getText().toString());
                    user.setEmail(txtEmail.getText().toString());
                    user.setPassword(txtPassword.getText().toString());
                    user.setBirthday(null);
                    user.setCredits(0);

                    /** Call the method with parameter in the interface */
                    Call<Void> call = new RetrofitInitializer().setUserService().register(user);
                    /**Log the URL called*/
                    Log.wtf("URL Called", call.request().url() + "");
                    /** Enqueue the call */
                    call.enqueue(new Callback<Void>() {
                        @Override
                        public void onResponse(Call<Void> call, Response<Void> response) {
                            Log.e("code", response.errorBody()+"");
                            Toast.makeText(context, "Your account has been successfully created!", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(context, MainActivity.class);
                            startActivity(intent);
                        }

                        @Override
                        public void onFailure(Call<Void> call, Throwable t) {
                            Log.e("failure", t.toString());
                            Toast.makeText(context, t.toString(), Toast.LENGTH_LONG).show();
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

    public void resetFields(){
        txtName.setText("");
        txtEmail.setText("");
        txtBirthday.setText("");
        txtPassword.setText("");
        txtConfirmPassword.setText("");
    }

    public boolean validate(){
        String email = txtEmail.getText().toString();
        String name  = txtName.getText().toString();
        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(name)){
            return true;
        }
        return false;
    }
}