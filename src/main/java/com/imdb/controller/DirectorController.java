package com.imdb.controller;

import com.imdb.DTO.DirectorDTO;
import com.imdb.repository.IDirectorRepository;
import com.imdb.util.view.message.DirectorMessage;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

/**
 * Responsible for handling director-related operations such as
 * creating, updating, deleting, and searching for directors. It interfaces with
 * the director repository for persistence and utilizes a scanner for user input.
 */

public final class DirectorController {

  private final IDirectorRepository directorRepository;
  private final Scanner scanner;

  /**
   * Initializes the DirectorController with a director repository and a scanner for input.
   *
   * @param directorRepository Implementation of IDirectorRepository for director data access.
   * @param scanner            Scanner instance for reading console input.
   */

  public DirectorController(
    IDirectorRepository directorRepository,
    Scanner scanner
  ) {
    this.directorRepository = directorRepository;
    this.scanner = scanner;
  }

  /**
   * Registers a specified number of new directors based on user input.
   *
   * @return A list of DirectorDTO objects representing the newly registered directors.
   */

  public List<DirectorDTO> createDirector() {
    System.out.print(DirectorMessage.HOW_MANY_DIRECTORS.get());
    int qntDirectors = scanner.nextInt();
    scanner.nextLine();
    List<DirectorDTO> directors = new ArrayList<>(10);

    for (int i = 1; i <= qntDirectors; i++) {
      DirectorDTO newDirectorDTO = inputDirector();
      directors.add(directorRepository.create(newDirectorDTO));
      System.out.println(DirectorMessage.REGISTERED.get());
    }
    return directors;
  }

  /**
   * Displays a list of all registered directors to the user.
   */

  public void readListOfDirectors() {
    List<DirectorDTO> directors = directorRepository.read();
    String formattedDirectors = DirectorDTO.formatDirectors(directors);
    System.out.println(formattedDirectors);
    System.out.println(
      "Would you like to see details of an Director? (yes/no)"
    );

    if (scanner.nextLine().trim().equalsIgnoreCase("yes")) {
      System.out.println("Enter actor ID:");
      String id = scanner.nextLine();

      Optional<DirectorDTO> directorToSee = directors
        .stream()
        .filter(director -> director.id() == Integer.parseInt(id))
        .findFirst();

      if (directorToSee.isPresent()) {
        DirectorDTO directorDetails = directorToSee.get();
        System.out.println(DirectorDTO.actorDetailed(directorDetails));
      } else {
        System.out.println("Actor Id: " + id + " is not on the list");
      }
    }
  }

  /**
   * Updates the information of an existing director based on their ID.
   */

  public void updateDirector(List<DirectorDTO> directors) {
    System.out.println("Current directors in the movie:");
    directors.forEach(director ->
      System.out.println(director.id() + " - " + director.name())
    );

    System.out.println("Do you want to add or remove directors? (add/remove)");
    String action = scanner.nextLine().trim();

    switch (action) {
      case "add":
        List<DirectorDTO> addedDirectors = createDirector();
        directors.addAll(addedDirectors);
        break;
      case "remove":
        deleteDirector(directors);
        break;
    }
  }

  /**
   * Deletes a director from the system based on their ID.
   */

  public void deleteDirector(List<DirectorDTO> directors) {
    System.out.println("Enter director IDs to remove:");
    String[] idsToRemove = scanner.nextLine().split(",");
    for (String idStr : idsToRemove) {
      int idToRemove = Integer.parseInt(idStr.trim());
      directors.removeIf(director -> director.id() == idToRemove);
    }
  }

  /**
   * Searches for and displays directors whose names match a given keyword.
   */

  public void searchDirectors() {
    System.out.print(DirectorMessage.ENTER_SEARCH_KEYWORD_DIRECTOR.get());
    String keyword = scanner.nextLine();
    DirectorDTO directorName = new DirectorDTO(
      0,
      keyword,
      null,
      null,
      List.of()
    );
    List<DirectorDTO> directorDTOList = directorRepository.search(directorName);
    if (directorDTOList.isEmpty()) {
      System.out.println(DirectorMessage.DIRECTOR_FOUND_NAME);
    } else {
      directorDTOList.forEach(directorDTO ->
        System.out.println(DirectorDTO.actorDetailed(directorDTO))
      );
    }
  }

  /**
   * Collects user input for a new director, including their name and nationality, and creates a new DirectorDTO object.
   *
   * @return A new DirectorDTO object based on user input.
   */

  public DirectorDTO inputDirector() {
    System.out.print(DirectorMessage.ENTER_DIRECTOR_NAME.get());
    String name = scanner.nextLine();
    System.out.print(DirectorMessage.ENTER_DIRECTOR_NATIONALITY.get());
    String nationality = scanner.nextLine();
    System.out.print(DirectorMessage.ENTER_DIRECTOR_BIRTHDATE.get());
    LocalDate birthdate = LocalDate.parse(scanner.nextLine());
    return new DirectorDTO(0, name, nationality, birthdate, List.of());
  }
}
