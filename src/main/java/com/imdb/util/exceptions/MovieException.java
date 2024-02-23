package com.imdb.util.exceptions;

public class MovieException extends RuntimeException {
    public MovieException(String message) {
        super(message);
    }

    public static class MovieAlreadyExist extends MovieException {
        public MovieAlreadyExist(String name) {
            super(name + " is already included on the list.");
        }
    }

    public static class MovieNotFoundException extends MovieException {
        public MovieNotFoundException() {
            super("Movie not found on the list.");
        }
    }
}

