package com.imdb.controller;


import com.imdb.DTO.ActorDTO;
import com.imdb.repository.IActorRepository;
import com.imdb.util.view.message.Colors;
import com.imdb.util.view.message.ActorMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * The ActorController class manages actor-related operations such as
 * displaying, registering, updating, deleting, and searching for actors.
 */

public final class ActorController {
    private final IActorRepository actorRepository;
    private final Scanner scanner;

    /**
     * Constructor for ActorController.
     *
     * @param actorRepository The repository interface for actor data operations.
     * @param scanner         The Scanner object for input operations.
     */

    public ActorController(IActorRepository actorRepository, Scanner scanner) {
        this.actorRepository = actorRepository;
        this.scanner = scanner;
    }

    /**
     * Displays a list of all actors in the repository.
     */

    public void showListOfActors() {
        List<ActorDTO> actors = actorRepository.getAll();
        String formattedActors = actors.isEmpty() ? ActorMessage.LIST_NOT_FOUND.get(): format(actors);
        System.out.println(formattedActors);
    }

    /**
     * Registers new actors based on user input.
     *
     * @return A list of registered ActorDTO objects.
     */

    public List<ActorDTO> registerActor() {
        System.out.println("How many actors would you like to register?");
        int qntActors = scanner.nextInt();
        scanner.nextLine();
        List<ActorDTO> actors = new ArrayList<>(10);

        for (int i = 1; i <= qntActors; i++) {
            System.out.println("Enter the name of actor number " + i + " : ");
            ActorDTO newActorDTO = inputActor();
            actors.add(actorRepository.create(newActorDTO));
            System.out.println(ActorMessage.REGISTERED.get());
        }
        return actors;
    }

    /**
     * Updates an existing actor's information.
     */

    public void updateActor() {
        System.out.print("Enter the ID of the actor to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        ActorDTO actorDTOId = new ActorDTO(id, null, null);
        ActorDTO actorToUpdate = actorRepository.readById(actorDTOId);

        ActorDTO updatedActorDTO = inputActor();
        actorRepository.update(actorToUpdate, updatedActorDTO);
        System.out.println(ActorMessage.UPDATED.get());
    }

    /**
     * Deletes an actor based on ID.
     */

    public void deleteActor() {
        System.out.print("Enter the ID of the actor to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        ActorDTO actorDTO = new ActorDTO(id, null, null);

        ActorDTO actorToDelete = actorRepository.readById(actorDTO);
        actorRepository.delete(actorToDelete);
        System.out.println(ActorMessage.DELETED.get());
    }

    /**
     * Searches for actors by name keyword.
     */

    public void searchActors() {
        System.out.println("Enter the name keyword to search for an actor:");
        String keyword = scanner.next();
        ActorDTO actorName = new ActorDTO(0, keyword, null);
        actorRepository.readByName(actorName).forEach(System.out::println);
    }

    /**
     * Collects input for a new actor's name and nationality, creating a new ActorDTO.
     *
     * @return A new ActorDTO with inputted name and nationality.
     */

    public ActorDTO inputActor() {
        System.out.print("Enter the name of the actor: ");
        String name = scanner.nextLine();
        System.out.print("Enter the nationality of the actor: ");
        String nationality = scanner.nextLine();
        return new ActorDTO(0, name, nationality);
    }

    private String format(List<ActorDTO> actors){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Colors.YELLOW).append("List of actors\n").append(Colors.RESET);
        int index = 1;
        for (ActorDTO actor : actors) {
            stringBuilder.append(Colors.YELLOW).append(index).append(" - ").append(Colors.RESET).append(actor).append("\n");
            index++;
        }
        return stringBuilder.toString();
    }
}
