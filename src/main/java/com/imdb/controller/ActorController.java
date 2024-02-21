package com.imdb.controller;

import com.imdb.controller.menu.Menu;
import com.imdb.dto.ActorDTO;
import com.imdb.model.Actor;
import com.imdb.repository.IActorRepository;
import com.imdb.util.ValidationService;

import java.util.Optional;
import java.util.Scanner;

public final class ActorController {
    private final IActorRepository actorRepository;
    private final Scanner scanner;

    public ActorController(IActorRepository actorRepository, Scanner scanner) {
        this.actorRepository = actorRepository;
        this.scanner = scanner;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            Menu.actorMenu();
            choice = ValidationService.isInputInt(scanner);
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

    public void createActor(Scanner scanner, int qnt) {
        System.out.print("Enter the name of actor " + (qnt + 1) + ": ");
        String name = scanner.nextLine();

        Optional<Actor> actor = actorRepository.searchActor(name);
        //se o ator já existe
        if (actor.isPresent()) {
            actorRepository.actorPresent(name); //chama a exception
        } else {
            System.out.print("Actor not found, add nationality: ");
            String nationality = scanner.nextLine();
            ActorDTO newActor = new ActorDTO(name, nationality);
            actorRepository.addActor(newActor);
            System.out.println("successfully");
        }
    }

    private void viewActor(Scanner scanner) {
        System.out.print("Enter Actor Name: ");
        String name = scanner.nextLine();

        Optional<Actor> actor = actorRepository.searchActor(name);

        if (actor.isPresent()) {
            System.out.println(actor.get());
        } else {
            actorRepository.actorNotFound(name);
        }
    }

    private void viewAllActors() {
        System.out.println(actorRepository.getAllActors());
    }

    private void deleteActor(Scanner scanner) {
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

    private void updateActor(Scanner scanner) {
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
