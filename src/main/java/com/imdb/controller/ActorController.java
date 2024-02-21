package com.imdb.controller;

import com.imdb.dto.ActorDTO;
import com.imdb.repository.IActorRepository;
import com.imdb.util.ValidationInputService;

import java.util.List;
import java.util.Scanner;


public final class ActorController {
    private final IActorRepository actorRepository;
    private final ValidationInputService inputValidation;


    public ActorController(IActorRepository actorRepository, ValidationInputService textValidation) {
        this.actorRepository = actorRepository;
        this.inputValidation = textValidation;
    }

  public void start() {
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
  }

    public ActorDTO createActor(int qnt) {

        System.out.print("Enter the name of actor " + (qnt + 1) + ": ");
        String name = inputValidation.isValidPersonName();
        System.out.print("add nationality: ");
        String nationality = inputValidation.isValidNationality();
        ActorDTO newActorDTO = new ActorDTO(name, nationality);

        //tenta add um novo ator na lista de ator
        try {
            actorRepository.create(newActorDTO);
            //nao gerou a exception e adicionou com sucesso
            System.out.println("successfully");
            //retorna o novo ator que foi criado
            return newActorDTO;
            //recebeu a exception que o ator ja existia
        } catch (IllegalArgumentException e) {
            //retorna o ator que já estava presente na lista de ator
            return newActorDTO;
        }
    }

    private void viewActor() {
        System.out.print("Enter Actor Name: ");
        String name = inputValidation.isValidPersonName();

        try {
            //tenta receber um actorDTO
            ActorDTO actorDTO = actorRepository.readByName(name);
            //printa os datalhes do ator
            System.out.println(actorDTO);
        } catch (Exception e) {
            System.err.println("Actor not found!");
        }
    }

    private void viewAllActors() {
        List<ActorDTO> actorDTOList = actorRepository.getAll();
        System.out.println("Actors list\n");
        actorDTOList.forEach(System.out::println);
    }

    private void deleteActor() {
        System.out.print("Enter Actor name: ");
        String name = inputValidation.isValidPersonName();

        try {
            //tenta receber um actorDTO
            ActorDTO actorDTO = actorRepository.readByName(name);
            //passa o actorDTO para ser removido
            actorRepository.delete(actorDTO);
            //conseguiu remover
            System.out.println("successfully");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private void updateActor() {
        System.out.print("Enter Actor Name: ");
        String name = inputValidation.isValidPersonName();
        try {
            ActorDTO actor = actorRepository.readByName(name);

            //recebeu um novo actorDTO
            System.out.print("Enter New Actor Name: ");
            String newName = inputValidation.isValidPersonName();

            System.out.print("Enter New Actor Nationality: ");
            String newNationality = inputValidation.isValidDescription();

            ActorDTO newActorDTO = new ActorDTO(newName, newNationality);
            actorRepository.update(actor, newActorDTO);

            System.out.println("successfully");
        //o ator não existia
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}