package com.example.productapi;

public class Product {
    private String title;
    private Double price;
    private Rating rating;

    // Getters and setters
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Rating getRating() { return rating; }
    public void setRating(Rating rating) { this.rating = rating; }
}