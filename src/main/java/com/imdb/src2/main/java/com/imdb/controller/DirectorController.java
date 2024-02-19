package com.imdb.application.controller;

import com.imdb.application.impl.DirectorUseCaseImpl;
import com.imdb.model.Director;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class DirectorController {
    private final DirectorUseCaseImpl directorUseCaseImpl;

    private DirectorController(DirectorUseCaseImpl directorUseCaseImpl) {
        this.directorUseCaseImpl = directorUseCaseImpl;
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("1. Create Director");
            System.out.println("2. View Director");
            System.out.println("3. View All Directors");
            System.out.println("4. Update Director");
            System.out.println("5. Delete Director");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

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
        Optional<Director> director = directorService.searchDirector(name);

        if (director.isEmpty()) {
            System.out.print("Director not found, add nationality: ");
            String nationality = scanner.nextLine();
            Director newdirector = new Director(name, nationality);
            directorService.addDirector(newdirector);
            System.out.println("successfully");
        }
        System.out.println("Error...");
    }

    private void viewDirector(Scanner scanner) {
        System.out.print("Enter Director Name: ");
        String name = scanner.nextLine();

        Optional<Director> director = directorService.searchDirector(name);

        if (director.isPresent()) {
            System.out.println("Director Details:");
            System.out.println("ID: " + director.get().getId());
            System.out.println("Name: " + director.get().getName());
            System.out.println("Nationality: " + director.get().getNationality());
        } else {
            System.out.println("Director not found.");
        }
    }

    private void viewAllDirectors() {
        List<Director> directors = directorService.getAllDirectors();
        System.out.println("All Directors:");

        for (Director director : directors) {
            System.out.println(
                    "ID: " +
                            director.getId() +
                            ", Name: " +
                            director.getName() +
                            ", Nationality: " +
                            director.getNationality()
            );
        }
    }

    private void updateDirector(Scanner scanner) {
        System.out.print("Enter Director Name: ");
        String name = scanner.nextLine();
        Optional<Director> existingDirector = directorService.searchDirector(name);

        if (existingDirector.isPresent()) {
            System.out.print("Enter New Director Name: ");
            String newName = scanner.nextLine();

            System.out.print("Enter New Director Nationality: ");
            String newNationality = scanner.nextLine();

            Director updatedDirector = new Director(newName, newNationality);
            updatedDirector.setId(existingDirector.get().getId());
            directorService.updateDirector(updatedDirector);
        } else {
            System.out.println("Director not found.");
        }
    }

    private void deleteDirector(Scanner scanner) {
        System.out.print("Enter Director ID: ");
        String name = scanner.nextLine();
        directorService.removeDirector(directorService.searchDirector(name).get());
    }

}
