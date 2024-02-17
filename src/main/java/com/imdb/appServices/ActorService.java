package com.imdb.appServices;

import com.imdb.model.Actor;
import com.imdb.model.Movie;
import com.imdb.repository.ActorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ActorService {
    private final ActorRepository actorRepository;
    private final Scanner scanner;

    public ActorService(ActorRepository actorRepository, Scanner scanner) {
        this.actorRepository = actorRepository;
        this.scanner = scanner;
    }
    public List<Actor> getAllActors() {
        return actorRepository.getAllActors();
    }

    public Actor getActorById(int id) {
        return actorRepository.getActorById(id);
    }

    public Actor getActorByName(String name) {
        return actorRepository.getActorByName(name);
    }

    public void addActor(Actor actor) {
        actorRepository.addActor(actor);
    }

    public Actor findActorById(int id) {
        return actorRepository.findActorById(id);
    }

    public Actor findActorByName(String name) {
        return actorRepository.findActorByName(name);
    }

    public Actor findActorByNationality(String nationality) {
        return actorRepository.findActorByNationality(nationality);
    }

    public boolean updateActor(Actor updatedActor) {
        return actorRepository.updateActor(updatedActor);
    }

    public boolean deleteActor(int id) {
        return actorRepository.deleteActor(id);
    }

    public void editActors(ArrayList<Movie> selectedMovie) {
    }

    public void editActor(Movie movie) {
    }

    public void editDirector(Movie movie) {
    }
}
