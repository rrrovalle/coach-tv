package com.example.coach_tv.model.dto;

import com.example.coach_tv.model.Mentoring;
import com.google.gson.GsonBuilder;

public class MentoringDTO {

    private int image;
    private int rating;
    private int credits;
    private String title;
    private String desc;
    private String section;

    public MentoringDTO(Mentoring mentoring){
        this.title   = mentoring.getTitle();
        this.desc    = mentoring.getDesc();
        this.section = mentoring.getSection();
        this.rating  = mentoring.getRating();
        this.credits = mentoring.getCredits();
        this.image   = mentoring.getImage();
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

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this, MentoringDTO.class);
    }
}
