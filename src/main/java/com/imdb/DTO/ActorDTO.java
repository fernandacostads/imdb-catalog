package com.imdb.DTO;

import com.imdb.model.Actor;

/**
 * Data Transfer Object (DTO) for Actor entities.
 * This class is used to transfer actor data between layers without exposing the domain model.
 */

public record ActorDTO(
        int id,
        String name,
        String nationality
) {

    /**
     * Converts an ActorDTO instance to an Actor model.
     *
     * @param actorDTO The ActorDTO instance to convert.
     * @return An Actor model populated with data from the ActorDTO.
     */

    public static Actor toActor(ActorDTO actorDTO) {
        return new Actor(
                actorDTO.id(),
                actorDTO.name(),
                actorDTO.nationality()
        );
    }

    /**
     * Converts an Actor model to an ActorDTO instance.
     *
     * @param actor The Actor model to convert.
     * @return An ActorDTO instance populated with data from the Actor model.
     */

    public static ActorDTO fromActor(Actor actor) {
        return new ActorDTO(
                actor.getId(),
                actor.getName(),
                actor.getNationality()
        );
    }

    /**
     * Provides a string representation of the ActorDTO instance.
     *
     * @return A string containing the ActorDTO's id, name, and nationality.
     */

    @Override
    public String toString() {
        return "ID: " + id +
                ", Name: " + name +
                ", Nationality: " + nationality + "\n";
    }
}
