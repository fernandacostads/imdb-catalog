package com.imdb.util.exceptions;


import java.util.Collections;

public class ActorException extends RuntimeException {
    public ActorException(String message) {
        super(message);
    }

    public static class ActorAlreadyExist extends ActorException {
        public ActorAlreadyExist(String name) {
            super(name + " is already included on the list.");
        }
    }

    public static class ActorListIsEmpty extends ActorException {
        public ActorListIsEmpty() {
            super("No actors found on the list.");
        }
    }

    public static class ActorNotFoundException extends ActorException {
        public ActorNotFoundException() {
            super("Actor not found on the list.");
        }
    }
}

