package com.imdb.controller;

import com.imdb.dto.DirectorDTO;
import com.imdb.repository.IDirectorRepository;

import java.util.Scanner;

public final class DirectorController {
  private final IDirectorRepository directorRepository;
  private final Scanner scanner;

  public DirectorController(IDirectorRepository directorRepository, Scanner scanner) {
    this.directorRepository = directorRepository;
    this.scanner = scanner;
  }

  public DirectorDTO registerDirector() {
    System.out.println("Enter director details (name, nationality):");
    String name = scanner.nextLine();
    String nationality = scanner.nextLine();
    // Você pode adicionar mais campos relevantes aqui, se necessário

    return new DirectorDTO(0, name, nationality);
  }
}