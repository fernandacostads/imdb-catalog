package com.imdb.repository;

import com.imdb.model.Movie;

import java.util.List;

public interface MovieRepository {
        public Movie create(Movie movie);

        public List<Movie> readByName(String name);

        public Movie update(Movie movie);

        public void delete(Movie movie);
}
