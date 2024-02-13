package com.imdb.controller;

import com.imdb.model.Actor;
import com.imdb.repository.PersonRepository;

public class PersonController {
    private final PersonRepository personRepository;

    public PersonController() {
        this.personRepository = new PersonRepository(); // Inicialize o repositório aqui
    }

    public void addActor(Actor actor) {
        // Lógica para adicionar um ator
        personRepository.addActor(actor);
    }
}
