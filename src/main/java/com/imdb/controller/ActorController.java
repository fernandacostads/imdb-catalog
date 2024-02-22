package com.imdb.controller;


import com.imdb.dto.ActorDTO;
import com.imdb.repository.IActorRepository;

import java.util.Scanner;

public final class ActorController {
  private final IActorRepository actorRepository;
  private final Scanner scanner;

  public ActorController(IActorRepository actorRepository, Scanner scanner) {
    this.actorRepository = actorRepository;
    this.scanner = scanner;
  }

  public ActorDTO registerActor() {
    System.out.println("Enter actor details (name, nationality):");
    String name = scanner.nextLine();
    String nationality = scanner.nextLine();
    // Você pode adicionar mais campos relevantes aqui, se necessário

    return new ActorDTO(0, name, nationality);
  }

}
