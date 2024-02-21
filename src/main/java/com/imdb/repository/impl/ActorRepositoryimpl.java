package com.imdb.repository.impl;

import com.imdb.dto.ActorDTO;
import com.imdb.model.Actor;
import com.imdb.repository.IActorRepository;
import com.imdb.util.FileHandler;
import com.imdb.util.ModelConvertUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActorRepositoryimpl implements IActorRepository {

    private static final String FILE_PATH =
            "src/main/java/com/imdb/util/resources/actors.txt";
    private static ActorRepositoryimpl instance;
    private static List<Actor> actorsList;
    private final ModelConvertUtil converter = new ModelConvertUtil();
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
    public void addActor(ActorDTO actorDTO) {
        Actor actor = converter.convertDTOToObjt(actorDTO);
        Optional<Actor> optionalActor = getActor(actor);
        if (optionalActor.isPresent()) {
            throw new IllegalArgumentException("Actor had already exist!");
        }
        actor.setId(idGenerator++);
        actorsList.add(actor);
        FileHandler.updateFileA(actorsList, FILE_PATH);
    }

    @Override
    public void removeActor(Actor actor) {
        Optional<Actor> optionalActor = getActor(actor);
        if (optionalActor.isEmpty()) {
            throw new IllegalArgumentException("The actor does not exist!");
        }
        actorsList.remove(optionalActor.get());

        FileHandler.updateFileA(actorsList, FILE_PATH);

    }

    @Override
    public Actor updateActor(Actor actor) {
        Optional<Actor> optionalActor = getActor(actor);
        if (optionalActor.isEmpty()) {
            throw new IllegalArgumentException("The actor does not exist");
        }
        actorsList.remove(optionalActor.get());
        actorsList.add(actor);
        FileHandler.updateFileA(actorsList, FILE_PATH);
        return actor;
    }

    @Override
    public Optional<Actor> searchActorByName(String name) {
        return actorsList
                .stream()
                .filter(aux -> aux.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    @Override
    public List<ActorDTO> getAllActors() {
        return converter.convertObjToDTO(actorsList);
    }

    private Optional<Actor> getActor(Actor actor) {
        return actorsList.stream().filter(aux -> aux.equals(actor)).findFirst();
    }

}
