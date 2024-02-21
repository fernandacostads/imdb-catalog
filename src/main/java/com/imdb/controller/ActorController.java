package com.imdb.controller;

import com.imdb.util.Menu;
import com.imdb.dto.ActorDTO;
import com.imdb.model.Actor;
import com.imdb.repository.IActorRepository;
import com.imdb.util.ValidationInputService;

import java.util.List;
import java.util.Optional;


public final class ActorController {
    private final IActorRepository actorRepository;
    private final ValidationInputService inputValidation;


    public ActorController(IActorRepository actorRepository, ValidationInputService textValidation) {
        this.actorRepository = actorRepository;
        this.inputValidation = textValidation;
    }

  /*public void start() {
    Scanner scanner = new Scanner(System.in);
    int choice;

    do {
      Menu.actorMenu();
      choice = inputValidation.isInputInt();
      //scanner.nextLine(); // Consume newline

      switch (choice) {
        case 1 -> createActor(scanner, 1);
        case 2 -> viewActor(scanner);
        case 3 -> viewAllActors();
        case 4 -> updateActor(scanner);
        case 5 -> deleteActor(scanner);
        case 0 -> System.out.println("Exiting...");
        default -> System.out.println("Invalid choice. Please try again.");
      }
    } while (choice != 0);
  }*/

    public ActorDTO createActor(int qnt) {
        System.out.print("Enter the name of actor " + (qnt + 1) + ": ");
        String name = inputValidation.isValidPersonName();
        System.out.print("add nationality: ");
        String nationality = inputValidation.isValidNationality();
        ActorDTO newActor = new ActorDTO(name, nationality);

        //tenta add um novo ator na lista de ator
        try {
            actorRepository.addActor(newActor);
            //nao gerou a exception e adicionou com sucesso
            System.out.println("successfully");
            //retorna o novo ator que foi criado
            return newActor;
            //recebeu a exception que o ator ja existia
        } catch (IllegalArgumentException e) {
            //retorna o ator que já estava presente na lista de ator
            return newActor;
        }
    }

    private void viewActor() {
        System.out.print("Enter Actor Name: ");
        String name = inputValidation.isValidPersonName();

        try {
            Optional<Actor> actor = actorRepository.searchActorByName(name);
            System.out.println(actor.get());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private String viewAllActors() {
        try {
            List<ActorDTO> actorList = actorRepository.getAllActors();
        } catch (IllegalArgumentException e) {
            return "Lista vazia";
        }
            if (actorsList.isEmpty()) {
                throw new IllegalArgumentException("A lista de atores está vazia");
            }
            StringBuilder allActorsBuilder = new StringBuilder();
            allActorsBuilder.append("Actors list");
            allActorsBuilder.append("\n");
            for (Actor actor : actorsList) {
                allActorsBuilder.append(actor);
                allActorsBuilder.append("\n"); // Adiciona uma quebra de linha entre cada representação de ator
            }
            return allActorsBuilder.toString();
    }

    private void deleteActor() {
        System.out.print("Enter Actor name: ");
        String name = scanner.nextLine();
        Optional<Actor> actor = actorRepository.searchActor(name);

        if (actor.isPresent()) {
            actorRepository.removeActor(actor.get());
            System.out.println("successfully");
        } else {
            actorRepository.actorNotFound(name);
        }
    }

    private void updateActor() {
        System.out.print("Enter Actor Name: ");
        String name = scanner.nextLine();
        Optional<Actor> actor = actorRepository.searchActor(name);

        if (actor.isPresent()) {
            System.out.print("Enter New Actor Name: ");
            String newName = scanner.nextLine();

            System.out.print("Enter New Actor Nationality: ");
            String newNationality = scanner.nextLine();

            actorRepository.updateActor(actor.get(), newName, newNationality);
        } else {
            actorRepository.actorNotFound(name);
        }
    }
}