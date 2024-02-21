package com.imdb.util.expections.AlreadExist;
import com.imdb.model.Movie;

public class MovieAlreadyExist extends RuntimeException{
    public MovieAlreadyExist (Movie movie){
        super(movie.getTitle() + " is already included in the list");
    }
}
