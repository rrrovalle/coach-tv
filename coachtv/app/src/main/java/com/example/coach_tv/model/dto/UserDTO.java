package com.example.coach_tv.model.dto;

import com.example.coach_tv.model.User;
import com.google.gson.GsonBuilder;

public class UserDTO {
    private int id;

    public UserDTO(User user){
        this.id = user.getId();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, UserDTO.class);
    }
}
