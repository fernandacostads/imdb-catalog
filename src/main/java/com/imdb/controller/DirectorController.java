package com.imdb.controller;


import com.imdb.util.Menu;
import com.imdb.dto.DirectorDTO;
import com.imdb.model.Director;
import com.imdb.repository.IDirectorRepository;

import java.util.Optional;
import java.util.Scanner;

public class DirectorController {
  //mesma lógica do actorController

  private final IDirectorRepository directorRepository;

  private final Scanner scanner;

  public DirectorController(IDirectorRepository directorRepository, Scanner scanner) {
    this.directorRepository = directorRepository;
    this.scanner = scanner;
  }

  public void start() {
    Scanner scanner = new Scanner(System.in);
    int choice;

    do {
      Menu.directorMenu();

      choice = scanner.nextInt();
      scanner.nextLine();

      switch (choice) {
        case 1 -> createDirector(scanner, 1);
        case 2 -> viewDirector(scanner);
        case 3 -> viewAllDirectors();
        case 4 -> updateDirector(scanner);
        case 5 -> deleteDirector(scanner);
        case 0 -> System.out.println("Exiting...");
        default -> System.out.println("Invalid choice. Please try again.");
      }
    } while (choice != 0);
  }

  public void createDirector(Scanner scanner, int qnt) {
    System.out.print("Enter the name of director " + (qnt + 1) + ": ");
    String name = scanner.nextLine();

    Optional<Director> director = directorRepository.searchDirector(name);
    //se o ator já existe
    if (director.isPresent()) {
      directorRepository.directorPresent(name); //chama a exception
    } else {
      System.out.print("Director not found, add nationality: ");
      String nationality = scanner.nextLine();
      DirectorDTO directorDTO = new DirectorDTO(name, nationality);
      directorRepository.addDirector(directorDTO);
      System.out.println("successfully");
    }
  }


  private void viewDirector(Scanner scanner) {
    System.out.print("Enter Director Name: ");
    String name = scanner.nextLine();

    Optional<Director> director = directorRepository.searchDirector(name);

    if (director.isPresent()) {
      System.out.println(director.get());
    } else {
      directorRepository.directorNotFound(name);
    }
  }


  private void viewAllDirectors() {
    System.out.println(directorRepository.getAllDirectors());
  }


  private void updateDirector(Scanner scanner) {
    System.out.print("Enter Director Name: ");
    String name = scanner.nextLine();
    Optional<Director> director = directorRepository.searchDirector(name);

    if (director.isPresent()) {
      System.out.print("Enter New Director Name: ");
      String newName = scanner.nextLine();

      System.out.print("Enter New Director Nationality: ");
      String newNationality = scanner.nextLine();

      directorRepository.updateDirector(director.get(), newName, newNationality);
    } else {
      directorRepository.directorNotFound(name);
    }
  }


  private void deleteDirector(Scanner scanner) {
    System.out.print("Enter Director name: ");
    String name = scanner.nextLine();
    Optional<Director> director = directorRepository.searchDirector(name);

    if (director.isPresent()) {
      directorRepository.removeDirector(director.get());
      System.out.println("successfully");
    } else {
      directorRepository.directorNotFound(name);
    }
  }
}
