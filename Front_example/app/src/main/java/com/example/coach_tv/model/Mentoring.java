package com.example.coach_tv.model;

public class Mentoring {

    private int image;
    private int rating;
    private String title;
    private String desc;
    private String section;

    public Mentoring( String title, String desc, String section, int rating, int image){
        this.title   = title;
        this.desc    = desc;
        this.section = section;
        this.rating  = rating;
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
}
