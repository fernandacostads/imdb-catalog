package com.imdb.controller;

import com.imdb.dto.DirectorDTO;
import com.imdb.repository.IDirectorRepository;
import com.imdb.util.ValidationInputService;

import java.util.List;


public final class DirectorController {
  private final IDirectorRepository directorRepository;
  private final ValidationInputService inputValidation;


  public DirectorController(IDirectorRepository directorRepository, ValidationInputService textValidation) {
    this.directorRepository = directorRepository;
    this.inputValidation = textValidation;
  }

  /*public void start() {
    Scanner scanner = new Scanner(System.in);
    int choice;

    do {
      Menu.directorMenu();
      choice = inputValidation.isInputInt();
      //scanner.nextLine(); // Consume newline

      switch (choice) {
        case 1 -> createDirector(scanner, 1);
        case 2 -> viewDirector();
        case 3 -> viewAllDirector();
        case 4 -> updateDirector();
        case 5 -> deleteDirector();
        case 0 -> System.out.println("Exiting...");
        default -> System.out.println("Invalid choice. Please try again.");
      }
    } while (choice != 0);
  }*/

  public DirectorDTO createDirector(int qnt) {
    System.out.print("Enter the name of director " + (qnt + 1) + ": ");
    String name = inputValidation.isValidPersonName();
    System.out.print("add nationality: ");
    String nationality = inputValidation.isValidNationality();
    DirectorDTO newDirectorDTO = new DirectorDTO(name, nationality);

    try {
      directorRepository.create(newDirectorDTO);
      System.out.println("successfully");
      return newDirectorDTO;
    } catch (IllegalArgumentException e) {
      return newDirectorDTO;
    }
  }

  private void viewDirector() {
    System.out.print("Enter Director Name: ");
    String name = inputValidation.isValidPersonName();

    try {
      DirectorDTO directorDTO = directorRepository.readByName(name);
      System.out.println(directorDTO);
    } catch (Exception e) {
      System.err.println("Actor not found!");
    }
  }

  private void viewAllDirector() {
    List<DirectorDTO> directorDTOList = directorRepository.getAll();
    System.out.println("Director list\n");
    directorDTOList.forEach(System.out::println);
  }

  private void deleteDirector() {
    System.out.print("Enter Director name: ");
    String name = inputValidation.isValidPersonName();

    try {
      DirectorDTO directorDTO = directorRepository.readByName(name);
      directorRepository.delete(directorDTO);
      System.out.println("successfully");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }

  }

  private void updateDirector() {

    System.out.print("Enter Director Name: ");
    String name = inputValidation.isValidPersonName();

    try {
      DirectorDTO directorDTO = directorRepository.readByName(name);

      System.out.print("Enter New Director Name: ");
      String newName = inputValidation.isValidPersonName();

      System.out.print("Enter New Director Nationality: ");
      String newNationality = inputValidation.isValidDescription();
      DirectorDTO newDirectorDTO = new DirectorDTO(newName, newNationality);

      directorRepository.update(directorDTO, newDirectorDTO);
      System.out.println("successfully");
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}