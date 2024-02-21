package com.imdb.util.expections.AlreadExist;
import com.imdb.model.Director;
public class DirectorAlreadyExist extends RuntimeException{
    public DirectorAlreadyExist(Director director){
        super(director.getName() + " is already included in the list");
    }
}
