package com.imdb.controller;

import com.imdb.model.Actor;
import com.imdb.model.Director;
import com.imdb.repository.PersonRepository;

public class PersonController {

    public void addActor(Actor actor) {
        // Lógica para adicionar um ator
        PersonRepository.addActor(actor);
    }

    public void addDirector (Director director) {
        // Lógica para adicionar um ator
        PersonRepository.addDirector(director);
    }
}
