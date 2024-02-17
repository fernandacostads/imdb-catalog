package com.imdb.repository;

import com.imdb.model.Actor;

import java.util.ArrayList;
import java.util.List;

public class ActorRepository {
    private final List<Actor> actorList;

    public ActorRepository() {
        this.actorList = new ArrayList<>();
    }

    public void addActor(Actor actor) {
        actorList.add(actor);
    }

    public Actor getActorById(int id) {
        for (Actor actor : actorList) {
            if (actor.getId() == id) {
                return actor;
            }
        }
        return null;
    }

    public Actor getActorByName(String name) {
        for (Actor actor : actorList) {
            if (actor.getName().equalsIgnoreCase(name)) {
                return actor;
            }
        }
        return null;
    }

    public Actor findActorById(int id) {
        for (Actor actor : actorList) {
            if (actor.getId() == id) {
                return actor;
            }
        }
        return null;
    }

    public Actor findActorByName(String name) {
        for (Actor actor : actorList) {
            if (actor.getName().equalsIgnoreCase(name)) {
                return actor;
            }
        }
        return null;
    }

    public Actor findActorByNationality(String nationality) {
        for (Actor actor : actorList) {
            if (actor.getNationality().equalsIgnoreCase(nationality)) {
                return actor;
            }
        }
        return null;
    }

    public boolean updateActor(Actor updatedActor) {
        for (Actor actor : actorList) {
            if (actor.getId() == updatedActor.getId()) {
                actor.setName(updatedActor.getName());
                actor.setNationality(updatedActor.getNationality());
                return true;
            }
        }
        return false;
    }

    public boolean deleteActor(int id) {
        for (Actor actor : actorList) {
            if (actor.getId() == id) {
                actorList.remove(actor);
                return true;
            }
        }
        return false;
    }

    public List<Actor> getAllActors() {
        return actorList;
    }
}