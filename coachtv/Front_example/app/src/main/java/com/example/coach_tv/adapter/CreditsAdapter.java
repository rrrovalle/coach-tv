package com.example.coach_tv.adapter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.coach_tv.CourseManagerActivity;
import com.example.coach_tv.R;
import com.example.coach_tv.fragment.CreditFragment;
import com.example.coach_tv.fragment.MentoringFragment;
import com.example.coach_tv.model.CreditPackDTO;
import com.example.coach_tv.model.UserDTO;
import com.example.coach_tv.retrofit.RetrofitInitializer;
import com.google.gson.Gson;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreditsAdapter extends RecyclerView.Adapter<CreditsAdapter.Viewholder> {

    private Context context;
    private ArrayList<CreditPackDTO> creditPackArrayList;

    public CreditsAdapter(Context context, ArrayList<CreditPackDTO> creditPackArrayList) {
        this.context = context;
        this.creditPackArrayList = creditPackArrayList;
    }

    @NonNull
    @Override
    public CreditsAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // to inflate the layout for each item of recycler view.
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.credit_pack_card, parent, false);
        context = parent.getContext();
        return new CreditsAdapter.Viewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        CreditPackDTO model = creditPackArrayList.get(position);
        holder.packAmount.setText(model.getTitle());
        //holder.packAmount.setText(model.getAmount()+" Credits");
        holder.packPrice.setText("Total: R$"+model.getPrice());
    }

    @Override
    public int getItemCount() {
        return creditPackArrayList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {
        private TextView packAmount, packPrice, packTitle;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(v -> {
                UserDTO user = new UserDTO();
                CreditPackDTO purchasePack = new CreditPackDTO(getUser(), packAmount.getText().toString());
                Call<Void> call = new RetrofitInitializer().setCreditsService().purchase(purchasePack);
                Log.wtf("URL:", call.request().url() + "");
                call.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        message("Your credits has been successfully add to your account!");
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        message(t.toString());
                    }
                });
            });
            packAmount = itemView.findViewById(R.id.idPackTitle);
            packPrice  = itemView.findViewById(R.id.idPackPrice);
        }
    }

    private UserDTO getUser() {
        SharedPreferences sharedPreferences = context.getSharedPreferences("userDetails", context.MODE_PRIVATE);
        Gson gson = new Gson();
        String json = sharedPreferences.getString("UserDTO", "");
        UserDTO user = gson.fromJson(json, UserDTO.class);
        return user;
    }

    private void message(String msg){
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }
}
