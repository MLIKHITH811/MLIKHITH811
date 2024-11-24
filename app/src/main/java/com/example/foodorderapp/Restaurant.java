package com.example.foodorderapp;

public class Restaurant {

    private int id;
    private String name;
    private String description;
    private int imageResId;

    // Constructor for restaurants with images
    public Restaurant(int id, String name, String description, int imageResId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageResId = imageResId;
    }

    // Constructor for restaurants without images
    public Restaurant(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageResId = -1; // No image
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getImageResId() {
        return imageResId;
    }
}
