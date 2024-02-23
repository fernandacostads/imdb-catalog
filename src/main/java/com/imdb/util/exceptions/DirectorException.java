package com.imdb.util.exceptions;


public class DirectorException extends RuntimeException {
    public DirectorException(String message) {
        super(message);
    }

    public static class DirectorAlreadyExist extends DirectorException {
        public DirectorAlreadyExist(String name) {
            super(name + " is already included on the list.");
        }
    }

    public static class DirectorListIsEmpty extends DirectorException {
        public DirectorListIsEmpty() {
            super("No directors found on the list.");
        }
    }

    public static class DirectorNotFoundException extends DirectorException {
        public DirectorNotFoundException() {
            super("Director not found on the list.");
        }
    }
}

