package com.imdb.controller;

import com.imdb.DTO.DirectorDTO;
import com.imdb.repository.IDirectorRepository;
import com.imdb.util.view.message.Colors;
import com.imdb.util.view.message.DirectorMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles director-related actions including creating, updating, deleting,
 * and searching for directors within the application.
 */

public final class DirectorController {
    private final IDirectorRepository directorRepository;
    private final Scanner scanner;

    /**
     * Constructs a DirectorController with a specified director repository and scanner for input.
     *
     * @param directorRepository An implementation of the director repository interface for data access.
     * @param scanner A Scanner instance to read user input from the console.
     */

    public DirectorController(IDirectorRepository directorRepository, Scanner scanner) {
        this.directorRepository = directorRepository;
        this.scanner = scanner;
    }

    /**
     * Displays a list of all directors currently registered in the repository.
     */

    public void showListOfDirectors() {
        List<DirectorDTO> directors = directorRepository.getAll();
        if (directors.isEmpty()) {
            System.out.println(DirectorMessage.LIST_NOT_FOUND.get());
            return;
        }
        System.out.println(formatDirectors(directors));
    }

    /**
     * Prompts the user to input details for registering one or more new directors.
     * Registers each new director in the repository.
     */

    public List<DirectorDTO> registerDirector() {
        System.out.println("How many directors would you like to register?");
        int qntDirectors = scanner.nextInt();
        scanner.nextLine();
        List<DirectorDTO> directors = new ArrayList<>(10);

        for (int i = 1; i <= qntDirectors; i++) {
            System.out.println("Enter the name of director number " + i + " : ");
            DirectorDTO newDirectorDTO = inputDirector();
            directors.add(directorRepository.create(newDirectorDTO));
            System.out.println(DirectorMessage.REGISTERED.get());
        }
        return directors;
    }

    /**
     * Updates the information of an existing director identified by ID.
     */

    public void updateDirector() {
        System.out.print("Enter the ID of the director to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        DirectorDTO directorDTOId = new DirectorDTO(id, null, null);
        DirectorDTO directorToUpdate = directorRepository.readById(directorDTOId);

        DirectorDTO updatedDirectorDTO = inputDirector();
        directorRepository.update(directorToUpdate, updatedDirectorDTO);
        System.out.println(DirectorMessage.UPDATED.get());
    }

    /**
     * Deletes a director from the repository based on the provided ID.
     */

    public void deleteDirector() {
        System.out.print("Enter the ID of the director to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        DirectorDTO directorDTO = new DirectorDTO(id, null, null);

        DirectorDTO directorToDelete = directorRepository.readById(directorDTO);
        directorRepository.delete(directorToDelete);
        System.out.println(DirectorMessage.DELETED.get());
    }

    /**
     * Searches for directors based on a given name keyword.
     */

    public void searchDirectors() {
        System.out.println("Enter the name keyword to search for a director:");
        String keyword = scanner.next();
        DirectorDTO directorName = new DirectorDTO(0, keyword, null);
        directorRepository.readByName(directorName).forEach(System.out::println);
    }

    /**
     * Utility method to create a new DirectorDTO based on user input.
     *
     * @return A new instance of DirectorDTO with input details.
     */

    public DirectorDTO inputDirector() {
        System.out.print("Enter the name of the director: ");
        String name = scanner.nextLine();
        System.out.print("Enter the nationality of the director: ");
        String nationality = scanner.nextLine();
        return new DirectorDTO(0, name, nationality);
    }

    private String formatDirectors(List<DirectorDTO> directors) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Colors.YELLOW).append("List of directors\n").append(Colors.RESET);
        int index = 1;
        for (DirectorDTO director : directors) {
            stringBuilder.append(Colors.YELLOW).append(index).append(" - ").append(Colors.RESET).append(director).append("\n");
            index++;
        }
        return stringBuilder.toString();
    }
}