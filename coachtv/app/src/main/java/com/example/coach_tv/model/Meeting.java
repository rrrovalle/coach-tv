package com.example.coach_tv.model;

public class Meeting {

    private int id;
    private int image;
    private int credits;
    private String date;
    private String title;
    private String username;
    private String description;

    public Meeting(String title, String date, String username, String desc, int credits,  int image){
        this.title = title;
        this.image = image;
        this.date = date;
        this.description = desc;
        this.username = username;
        this.credits = credits;
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
}
