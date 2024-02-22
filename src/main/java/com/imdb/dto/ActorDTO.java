package com.imdb.dto;

import com.imdb.model.Actor;

public record ActorDTO(
        int id,
        String name,
        String nationality
) {
  public static Actor toActor(ActorDTO actorDTO) {
    return new Actor(
            actorDTO.id(),
            actorDTO.name(),
            actorDTO.nationality()
            // ... outros campos relevantes
    );
  }

  public static ActorDTO fromActor(Actor actor) {
    return new ActorDTO(
            actor.getId(),
            actor.getName(),
            actor.getNationality()
            // ... outros campos relevantes
    );
  }

}
