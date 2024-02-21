package com.imdb;

import com.imdb.controller.MovieController;
import com.imdb.repository.IActorRepository;
import com.imdb.repository.IDirectorRepository;
import com.imdb.repository.IMovieRepository;
import com.imdb.repository.impl.ActorRepositoryimpl;
import com.imdb.repository.impl.DirectorRepositoryimpl;
import com.imdb.repository.impl.MovieRepositoryimpl;
import com.imdb.util.Menu;
import com.imdb.util.ValidationInputService;
import java.util.Scanner;

public class IMDBApplication {

    public static void main(String[] args) {

        IActorRepository actorRepository = ActorRepositoryimpl.getInstance();
        IDirectorRepository directorRepository = DirectorRepositoryimpl.getInstance();
        IMovieRepository movieRepository = MovieRepositoryimpl.getInstance();
        Scanner scanner = new Scanner(System.in);
        ValidationInputService validation = new ValidationInputService(scanner);

        MovieController movieController = new MovieController(
                movieRepository,
                directorRepository,
                actorRepository,
                validation
        );


        int choice;
        do {
            System.out.println(Menu.MainMenuText.MAIN_MENU_TEXT);
            System.out.print(Menu.MainMenuText.ENTER_CHOICE);
            while (!scanner.hasNextInt()) {
                System.out.println(ValidationInputService.ErrorMessagesGeneral.ERROR_NOT_INTEGER);
                scanner.next();
                System.out.print(Menu.MainMenuText.ENTER_CHOICE);
            }
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    System.out.println(Menu.MainMenuText.SHOW_MOVIE_LIST);
                    movieController.showListOfMovies();
                    break;
                case 2:
                    System.out.println(Menu.MainMenuText.REGISTER_MOVIE);
                    movieController.registerNewMovie();
                    break;
                /*case 3:
                    System.out.println(Menu.MainMenuText.EDIT_MOVIE);
                    movieController.editMovie();
                    break;*/
                case 4:
                    System.out.println(Menu.MainMenuText.DELETE_MOVIE);
                    movieController.deleteMovie();
                    break;
                case 5:
                    System.out.println(Menu.MainMenuText.SEARCH);
                    movieController.searchMovie();
                    break;
                case 6:
                    System.out.println(Menu.ExitMessages.EXIT_PROGRAM);
                    break;
                default:
                    System.out.printf(ValidationInputService.ErrorMessagesGeneral.ERROR_INVALID_OPTION, 1, 6);
            }
        } while (choice != 6);
    }
}
