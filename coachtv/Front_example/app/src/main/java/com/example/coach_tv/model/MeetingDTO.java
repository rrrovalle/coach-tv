package com.example.coach_tv.model;

import java.time.LocalDateTime;

public class MeetingDTO {

    private long id;

    private int duration;

    private int price;

    private LocalDateTime startTime;

    private UserDTO customer;

    private MentoringDTO mentoring;

    private String description;

    public MeetingDTO(int duration, int price, LocalDateTime startTime, UserDTO customer, MentoringDTO mentoring, String description) {
        this.duration    = duration;
        this.price       = price;
        this.startTime   = startTime;
        this.customer    = customer;
        this.mentoring   = mentoring;
        this.description = description;
    }

    public MeetingDTO(){}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public UserDTO getCustomer() {
        return customer;
    }

    public void setCustomer(UserDTO customer) {
        this.customer = customer;
    }

    public MentoringDTO getMentoring() {
        return mentoring;
    }

    public void setMentoring(MentoringDTO mentoring) {
        this.mentoring = mentoring;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

