package com.imdb.controller;

import com.imdb.model.CommentRating;
import com.imdb.model.Movie;
import com.imdb.repository.RatingRepository;

public class RatingController {
    private final RatingRepository ratingRepository;

    public RatingController() {
        this.ratingRepository = new RatingRepository(); // Inicialize o repositório aqui
    }

    public void addCommentRating(Movie movie, CommentRating commentRating) {
        // Lógica para adicionar uma avaliação de comentário
        ratingRepository.addCommentRating(movie, commentRating);
    }

    // Implemente outros métodos conforme necessário
}
