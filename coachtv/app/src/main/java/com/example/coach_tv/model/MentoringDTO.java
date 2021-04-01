package com.example.coach_tv.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MentoringDTO {

    @SerializedName("coach")
    @Expose
    private UserDTO coach;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("description")
    @Expose
    private String description;

    @SerializedName("rating")
    @Expose
    private float rating;

    @SerializedName("section")
    @Expose
    private String section;

    public MentoringDTO(UserDTO coach, String title, String description, float rating, String section) {
        this.coach = coach;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.section = section;
    }

    public MentoringDTO(){}

    public UserDTO getCoach() {
        return coach;
    }

    public void setCoach(UserDTO coach) {
        this.coach = coach;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    @Override
    public String toString() {
        return "MentoringDTO{" +
                "coach=" + coach +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", rating=" + rating +
                ", section='" + section + '\'' +
                '}';
    }
}

