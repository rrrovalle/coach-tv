package com.example.coach_tv.model;

public class Mentoring {

    private int id;
    private int image;
    private int rating;
    private int credits;
    private String title;
    private String desc;
    private String section;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Mentoring(String title, String desc, String section, int rating, int credits, int image){
        this.title   = title;
        this.desc    = desc;
        this.section = section;
        this.rating  = rating;
        this.credits = credits;
        this.image   = image;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }
}
