package com.imdb.util.expections.NotFoundException;

public class ActorNotFoundException extends RuntimeException{
    public ActorNotFoundException (String name){
        super("Actor " + name + " not found!");
    }
}
