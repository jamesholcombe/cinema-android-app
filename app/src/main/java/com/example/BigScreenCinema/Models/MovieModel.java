package com.example.BigScreenCinema.Models;

import com.example.BigScreenCinema.Movie;

public class MovieModel extends BaseModel<Movie> {
    public MovieModel() {
        super("movies", Movie.class);
    }
}

