package com.imdb.controller;


import com.imdb.dto.ActorDTO;
import com.imdb.repository.IActorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public final class ActorController {
  private final IActorRepository actorRepository;
  private final Scanner scanner;

  public ActorController(IActorRepository actorRepository, Scanner scanner) {
    this.actorRepository = actorRepository;
    this.scanner = scanner;
  }

  public List<ActorDTO> registerActor() {
    System.out.println("How many actors would you like to register?");
    int qntActors = scanner.nextInt(); scanner.nextLine();
    List<ActorDTO> actors = new ArrayList<>(10);

    for (int i = 1; i <= qntActors; i++) {
      System.out.println("Enter the " + i +  " actor:");
      System.out.print("name: ");
      String name = scanner.nextLine();
      System.out.print("Nationality: ");
      String nationality = scanner.nextLine();

      ActorDTO newactor = new ActorDTO(0,name,nationality);
      actorRepository.create(newactor);
      actors.add(newactor);
    }
    // Você pode adicionar mais campos relevantes aqui, se necessário

    return actors;
  }
}
