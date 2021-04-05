package com.example.coach_tv.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDTO {

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("password")
    @Expose
    private String password;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("birthday")
    @Expose
    private String birthday;

    @SerializedName("credits")
    @Expose
    private int credits;

    @SerializedName("id")
    @Expose
    private long id;

    private List<MentoringDTO> mentorings = new ArrayList<>();

    private String encodedImage;

    public UserDTO(){}

    public UserDTO(String email, String password, String name, String birthday, int credits) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
        this.credits = credits;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public List<MentoringDTO> getMentorings() {
        return mentorings;
    }

    public void setMentorings(List<MentoringDTO> mentorings) {
        this.mentorings = mentorings;
    }

    public String getEncodedImage() {
        return encodedImage;
    }

    public void setEncodedImage(String encodedImage) {
        this.encodedImage = encodedImage;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", credits=" + credits +
                ", id=" + id +
                ", mentorings=" + mentorings +
                ", encodedImage='" + encodedImage + '\'' +
                '}';
    }
}

    