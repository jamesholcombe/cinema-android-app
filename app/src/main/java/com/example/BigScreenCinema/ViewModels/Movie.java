package com.example.BigScreenCinema.ViewModels;

public class Movie extends Base {
    private String title;
    private String descriptionShort;
    private String imageUri;
    private String descriptionLong;
    private Float rating;
    private Screening[] screenings;
    private boolean isFeatured;

    public Movie(){
        super();
    }


    public Movie(String id, String title, String description, String imageUri, String descriptionLong, Float rating, Screening[] screenings, boolean isFeatured) {
        super(id);
        this.title = title;
        this.descriptionShort = description;
        this.imageUri = imageUri;
        this.descriptionLong = descriptionLong;
        this.rating = rating;
        this.screenings = screenings;
        this.isFeatured = isFeatured;
    }

    public Screening[] getScreenings() {
        return screenings;
    }



    @Override
    public String getId() {
        return super.getId();
    }

    public void setDescriptionLong(String descriptionLong) {
        this.descriptionLong = descriptionLong;
    }

    public void setDescriptionShort(String descriptionShort) {
        this.descriptionShort = descriptionShort;
    }

    public void setImageUri(String imageUri) {
        this.imageUri = imageUri;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public void setScreenings(Screening[] screenings) {
        this.screenings = screenings;
    }

    public void setTitle(String title) {
        this.title = title;
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


    public boolean isFeatured() {
        return isFeatured;
    }

    public void setFeatured(boolean featured) {
        isFeatured = featured;
    }
}
