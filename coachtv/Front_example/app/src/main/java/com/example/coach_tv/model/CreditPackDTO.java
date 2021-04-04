package com.example.coach_tv.model;

public class CreditPackDTO {

    private UserDTO userDTO;

    private String title;

    private int amount;

    private float price;

    public CreditPackDTO(UserDTO userDTO, String title) {
        this.userDTO = userDTO;
        this.title = title;
    }

    public CreditPackDTO() {
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}

