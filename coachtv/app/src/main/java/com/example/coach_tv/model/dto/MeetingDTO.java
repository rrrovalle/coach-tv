package com.example.coach_tv.model.dto;

import com.google.gson.GsonBuilder;

public class MeetingDTO {

    private int id;
    private int image;
    private int credits;
    private String date;
    private String title;
    private String username;
    private String description;

    public MeetingDTO(MeetingDTO meeting){
        this.title = meeting.getTitle();
        this.image = meeting.getImage();
        this.date = meeting.getDate();
        this.description = meeting.getDescription();
        this.username = meeting.getUsername();
        this.credits = meeting.getCredits();
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, MeetingDTO.class);
    }
}