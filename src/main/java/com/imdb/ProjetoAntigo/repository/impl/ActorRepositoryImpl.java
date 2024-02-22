import com.imdb.model.Actor;//package com.imdb.repository.impl;
//
//import com.imdb.model.Actor;
//import com.imdb.repository.IActorRepository;
//import com.imdb.util.FileHandler;
//import com.imdb.util.FileHandler.EntityConverter;
//
//import java.util.List;
//
//public class ActorRepositoryImpl implements IActorRepository {
//    private static final String FILE_PATH = "actors.txt";
//    private List<Actor> actors;
//
//    public ActorRepositoryImpl() {
//        actors = FileHandler.loadFromFile(FILE_PATH, new ActorConverter());
//    }
//
//    @Override
//    public void create(Actor actor) {
//        actors.add(actor);
//        FileHandler.saveToFile(actors, FILE_PATH, new ActorConverter());
//    }
//
//    @Override
//    public ActorDTO update(ActorDTO actorDTO, ActorDTO newActorDTO) {
//        Actor actor = converter.convertDTOToObjt(actorDTO);
//        actor.setName(newActorDTO.name());
//        actor.setNationality(newActorDTO.nationality());
//        FileHandler.updateFileA(actorsList, FILE_PATH);
//        return converter.convertObjToDTO(actor);
//    }
//
//    @Override
//    public void delete(ActorDTO actorDTO) {
//        actorsList.remove(converter.convertDTOToObjt(actorDTO));
//        FileHandler.updateFileA(actorsList, FILE_PATH);
//    }
//
//    @Override
//    public List<ActorDTO> getAll() {
//        return converter.convertActorListObjToDTO(actorsList);
//    }
//
//    @Override
//    public ActorDTO readById(int id) {
//        Optional<Actor> actor = actorsList.stream()
//                .filter(aux -> aux.getId() == id)
//                .findFirst();
//
//        if (actor.isPresent()) {
//            return converter.convertObjToDTO(actor.get());
//        } else {
//            throw new IllegalStateException("Paulo");//paulo
//        }
//    }
//
//    @Override
//    public ActorDTO readByName(String name) {
//        Optional<Actor> optionalActor = actorsList
//                .stream()
//                .filter(aux -> aux.getName().equalsIgnoreCase(name))
//                .findFirst();
//        if (optionalActor.isPresent()) {
//            return converter.convertObjToDTO(optionalActor.get());
//        } else {
//            throw new ActorNotFoundException(name);//Paulo
//        }
//    }
//
//    private Actor isPresent(ActorDTO actorDTO) {
//        Actor actor = converter.convertDTOToObjt(actorDTO);
//        Optional<Actor> optionalActor = actorsList
//                .stream()
//                .filter(aux -> aux.equals(actor))
//                .findFirst();
//
//        if (optionalActor.isEmpty()) {
//            return actor;
//        } else {
//            throw new ActorAlreadyExist(actor);
//        }
//    }
//}
//
