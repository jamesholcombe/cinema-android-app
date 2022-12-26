package com.example.BigScreenCinema.ViewModels;

public class Movie extends Base {
    private String title;
    private String descriptionShort;
    private String imageUri;
    private String descriptionLong;
    private Float rating;


    public Movie(String id, String title, String description, String imageUri, String descriptionLong, Float rating) {
        super(id);
        this.title = title;
        this.descriptionShort = description;
        this.imageUri = imageUri;
        this.descriptionLong = descriptionLong;
        this.rating = rating;


    }


    public String getTitle() {
        return title;
    }

    public Float getRating() {
        return rating;
    }

    public String getDescriptionShort() {
        return descriptionShort;
    }

    public String getImageUri() {
        return imageUri;
    }


    public String getDescriptionLong() {
        return descriptionLong;
    }


}
