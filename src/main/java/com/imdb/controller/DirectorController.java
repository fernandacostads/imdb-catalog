package com.imdb.controller;

import com.imdb.DTO.DirectorDTO;
import com.imdb.repository.IDirectorRepository;
import com.imdb.util.view.message.DirectorMessage;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

  public DirectorController(IDirectorRepository directorRepository, Scanner scanner) {
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
  }

  /**
   * Updates the information of an existing director based on their ID.
   */

  public void updateDirector() {
    System.out.print(DirectorMessage.ENTER_DIRECTOR_ID_UPDATE.get());
    int id = scanner.nextInt();
    scanner.nextLine();
    DirectorDTO directorDTOId = new DirectorDTO(id, null, null, null);
    DirectorDTO directorToUpdate = directorRepository.readById(directorDTOId);

    if (directorToUpdate == null) {
      System.out.println(DirectorMessage.DIRECTOR_ID_NOT_FOUND.get());
    } else {
      DirectorDTO updatedDirectorDTO = inputDirector();
      directorRepository.update(directorToUpdate, updatedDirectorDTO);
      System.out.println(DirectorMessage.UPDATED.get());
    }
  }

  /**
   * Deletes a director from the system based on their ID.
   */

  public void deleteDirector() {
    System.out.print(DirectorMessage.ENTER_DIRECTOR_ID_DELETE.get());
    int id = scanner.nextInt();
    scanner.nextLine();
    DirectorDTO directorDTO = new DirectorDTO(id, null, null, null);

    DirectorDTO directorToDelete = directorRepository.readById(directorDTO);

    if (directorToDelete == null) {
      System.out.println(DirectorMessage.DIRECTOR_ID_NOT_FOUND.get());
    } else {
      directorRepository.delete(directorToDelete);
      System.out.println(DirectorMessage.DELETED.get());
    }
  }

  /**
   * Searches for and displays directors whose names match a given keyword.
   */

  public void searchDirectors() {
    System.out.print(DirectorMessage.ENTER_SEARCH_KEYWORD_DIRECTOR.get());
    String keyword = scanner.next();
    DirectorDTO directorName = new DirectorDTO(0, keyword, null, null);
    List<DirectorDTO> directorDTOList = directorRepository.search(directorName);
    System.out.println(directorDTOList.isEmpty() ? DirectorMessage.DIRECTOR_FOUND_NAME : directorDTOList);
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
    return new DirectorDTO(0, name, nationality, birthdate);
  }
}