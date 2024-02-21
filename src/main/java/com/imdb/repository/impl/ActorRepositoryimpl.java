package com.imdb.repository.impl;

import com.imdb.model.Actor;
import com.imdb.repository.IActorRepository;
import com.imdb.util.FileHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActorRepositoryimpl implements IActorRepository {

    private static final String FILE_PATH =
            "src/main/java/com/imdb/util/resources/actors.txt";
    private static ActorRepositoryimpl instance;
    private static List<Actor> actorsList;
    private int idGenerator;

    private ActorRepositoryimpl() {
        actorsList = new ArrayList<>(10);
        actorsList = FileHandler.loadActorsFromFile(FILE_PATH);
        idGenerator = actorsList.isEmpty() ? 1 : actorsList.getLast().getId() + 1;
    }

    public static synchronized ActorRepositoryimpl getInstance() {
        if (instance == null) {
            instance = new ActorRepositoryimpl();
        }
        return instance;
    }

    @Override
    public void actorNotFound(String name) {
        throw new IllegalArgumentException("O Ator " + name + " não existe");

    }

    @Override
    public void actorPresent(String name) {
        throw new IllegalArgumentException("O Ator " + name + " já existe");
    }

    @Override
    public void addActor(Actor actor) {
        actor.setId(idGenerator++);
        actorsList.add(actor);
        FileHandler.updateFileA(actorsList, FILE_PATH);
    }

    @Override
    public void removeActor(Actor actor) {
        actorsList.remove(actor);
        FileHandler.updateFileA(actorsList, FILE_PATH);
    }

    @Override
    public Actor updateActor(Actor actor, String newName, String nationality) {
        actor.setName(newName);
        actor.setNationality(nationality);
        FileHandler.updateFileA(actorsList, FILE_PATH);
        return actor;
    }

    @Override
    public Optional<Actor> searchActor(String name) {
        return actorsList
                .stream()
                .filter(aux -> aux.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    @Override
    public String getAllActors() {
        if(actorsList.isEmpty()){
            throw new IllegalArgumentException("A lista de atores está vazia");
        }
        StringBuilder allActorsBuilder = new StringBuilder();
        for (Actor actor : actorsList) {
            allActorsBuilder.append(actor);
            allActorsBuilder.append("\n"); // Adiciona uma quebra de linha entre cada representação de ator
        }
        return allActorsBuilder.toString();
    }
}
