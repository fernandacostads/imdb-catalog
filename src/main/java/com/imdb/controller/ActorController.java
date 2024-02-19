package com.imdb.controller;

import com.imdb.appServices.ActorService;
import com.imdb.model.Actor;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class ActorController {
    //o controller deveria receber todos os inputs e passar pro empl

    private final ActorService actorService;

    public ActorController() {
        actorService = new ActorService();
    }

    public void start() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            //poderia ter um função na pasta menu que printaria isso;
            //ficaria mais limpo
            System.out.println("1. Create Actor");
            System.out.println("2. View Actor");
            System.out.println("3. View All Actors");
            System.out.println("4. Update Actor");
            System.out.println("5. Delete Actor");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            /*ta recebendo um int, mas nao verifica se é válido
            pode gerar exceção e não tratar.
            posso chamar isInputInt pra receber o int, porque ele valida que é um int
            posso chamar outra função de ValidationService pra verificar se esta no intervalo de opções
            */
            choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

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

        /*iria chamar uma função de impl que pega o nome e
        verifica pra saber se o nome já existe retornando true ou false.*/

        Optional<Actor> actor = actorService.searchActor(name);

        //se ator não foi encontrado, ou seja, é um ator que ainda não esta na base de dados
        if (actor.isEmpty()) {
            System.out.print("Actor not found, add nationality: ");
            String nationality = scanner.nextLine();
          /*deveria chamar a função createAtor do empl e passar nationality e name
          como parametro e a função do impl geraria o ator.
          isso iria garantir que aqui eu só recebo inputs.
          poderia aqui chamar as funções de ValidationService para verificar os inputs
          */
            Actor newactor = new Actor(name, nationality);
            actorService.addActor(newactor);
            System.out.println("successfully");
        }
        System.out.println("Error...");
    }

    private void viewActor(Scanner scanner) {
    /*iria chamar uma função de impl que pega o nome e
    verifica pra saber se o nome já existe retornando true ou false.

    if (true), chama a função de impli que retorna os detalhes do ator como uma string ja formatada
    em actorRepository eu poderia ter essa função que formata os dados e retorna como uma string formatada

    chamo impl -> impl chamava gateway -> gateway acessava actorRepository ->
    actorRepository retorna a string com detalhes do ator pra gateway -> gateway retorna pra impl ->
    impl retorna a string ->
    System.out.println(actorDetails);

    else {
      System.out.println("Actor not found.");
    }
    */
        System.out.print("Enter Actor Name: ");
        String name = scanner.nextLine();

        Optional<Actor> actor = actorService.searchActor(name);

        if (actor.isPresent()) {
            System.out.println("Actor Details:");
            System.out.println("ID: " + actor.get().getId());
            System.out.println("Name: " + actor.get().getName());
            System.out.println("Nationality: " + actor.get().getNationality());
        } else {
            System.out.println("Actor not found.");
        }
    }

    /*iria chamar a função de impli que pegaria a lista de ator e retornaria ele formada
    aqui ele só printaria a string
    */
    private void viewAllActors() {
        List<Actor> actors = actorService.getAllActors();
        System.out.println("All Actors:");

        for (Actor actor : actors) {
            System.out.println("ID: " + actor.getId() + ", Name: " + actor.getName() + ", Nationality: " + actor.getNationality());
        }
    }

    private void updateActor(Scanner scanner) {
        /*iria chamar uma função de impl que pega o nome e
        verifica pra saber se o nome já existe retornando true ou false.
        */
        System.out.print("Enter Actor Name: ");
        String name = scanner.nextLine();
        Optional<Actor> existingActor = actorService.searchActor(name);

        //if esta presente
        if (existingActor.isPresent()) {
            /*recebe a string novo nome e nova nacinalidade
            retorn as duas strings pra impli atualizar */
            System.out.print("Enter New Actor Name: ");
            String newName = scanner.nextLine();

            System.out.print("Enter New Actor Nationality: ");
            String newNationality = scanner.nextLine();

            /*chamo impl -> impl chamava gateway -> gateway acessava actorRepository ->
            actorRepository retorna o objeto ator -> gateway retorna pra impl ->
            impl atualiza o nome e nacinalidade -> retorna true se atualizou

            System.out.print("Ator atualizado com sucesso");
            */
            Actor updatedActor = new Actor(newName, newNationality);
            actorService.updateActor(updatedActor);
        } else {
            System.out.println("Actor not found.");
        }
    }

    private void deleteActor(Scanner scanner) {
        /*iria chamar uma função de impl que pega o nome e
        verifica pra saber se o nome já existe retornando true ou false.
        */

        /*
        if(true){
        recebo a string name
        chamo impl e passo a string -> impl chamava gateway passando a string -> gateway acessava actorRepository ->
            actorRepository remove a ator com esse nome
        }
        */
        System.out.print("Enter Actor name: ");
        String name = scanner.nextLine();
        actorService.removeActor(actorService.searchActor(name).get());
    }
  /* public void editActors(Movie selectedMovie, Scanner scanner) {
    List<Actor> actors = selectedMovie.getActors();
    if (actors.isEmpty()) {
      System.out.println("No actors available to edit.");
      return;
    } else {
      System.out.println("List of Actors:");
      for (int i = 0; i < actors.size(); i++) {
        System.out.println((i + 1) + " - " + actors.get(i).getName());
      }
    }

    System.out.println("Do you want to edit or remove?");
    System.out.println("1 - Edit");
    System.out.println("2 - Remove");
    System.out.print("Enter your choice: ");
    int choice = scanner.nextInt();
    scanner.nextLine();

    switch (choice) {
      case 1:
        editActorDetails(selectedMovie);
        break;
      case 2:
        removeActor(selectedMovie);
        break;
      default:
        System.out.println("Invalid choice. Please enter either 1 or 2.");
    }
  }

  private void editActorDetails(Movie selectedMovie, Scanner scanner) {
    System.out.println("List of Actors:");
    List<Actor> actors = selectedMovie.getActors();
    for (int i = 0; i < actors.size(); i++) {
      System.out.println(
        (i + 1) +
        " - " +
        actors.get(i).getName() +
        " (ID: " +
        actors.get(i).getId() +
        ")"
      );
    }

    System.out.print("Which actor do you want to edit? Enter the ID: ");
    int actorId = scanner.nextInt();
    scanner.nextLine();

    Actor actorToUpdate = actorService.findActorById(actorId);
    if (actorToUpdate == null) {
      System.out.println("Actor with ID " + actorId + " not found.");
      return;
    }

    System.out.print("Add new name: ");
    String newName = scanner.nextLine();
    System.out.print("Add new nationality: ");
    String newNationality = scanner.nextLine();

    if (
      actorService.findActorByName(newName) != null &&
      actorService.findActorByNationality(newNationality) != null
    ) {
      System.out.println(
        "Actor with the same name and nationality already exists."
      );
      return;
    }

    actorToUpdate.setName(newName);
    actorToUpdate.setNationality(newNationality);

    if (actorService.updateActor(actorToUpdate)) {
      System.out.println("Actor updated successfully!");
    } else {
      System.out.println("Failed to update actor.");
    }
  }

  private void removeActor(Movie selectedMovie) {
    if (selectedMovie.getActors().size() == 1) {
      System.out.println(
        "You cannot remove the only actor of the movie. The movie must have at least one actor."
      );
      return;
    }

    System.out.print("Which actor do you want to remove? Enter the ID: ");
    int actorIdToRemove = scanner.nextInt();
    scanner.nextLine();

    if (actorService.deleteActor(actorIdToRemove)) {
      System.out.println("Actor removed successfully!");
    } else {
      System.out.println("Failed to remove actor.");
    }
  }*/
}