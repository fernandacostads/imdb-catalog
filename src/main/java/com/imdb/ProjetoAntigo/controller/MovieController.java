///*package com.imdb.controller;
//
//import com.imdb.dto.ActorDTO;
//import com.imdb.dto.DirectorDTO;
//import com.imdb.dto.MovieDTO;
//import com.imdb.model.Director;
//import com.imdb.model.Movie;
//import com.imdb.repository.IActorRepository;
//import com.imdb.repository.IDirectorRepository;
//import com.imdb.repository.IMovieRepository;
//import com.imdb.util.ValidationInputService;
//
//import java.util.ArrayList;
//import java.util.InputMismatchException;
//import java.util.List;
//import java.util.Optional;
//
//public class MovieController {
//
//    private final IMovieRepository movieRepository;
//    private final IActorRepository actorRepository;
//    private final IDirectorRepository directorRepository;
//    private final ValidationInputService inputValidation;
//
//    public MovieController(
//            IMovieRepository movieRepository,
//            IDirectorRepository directorRepository,
//            IActorRepository actorRepository
//  ) {
//
//        this.actorRepository = actorRepository;
//        this.directorRepository = directorRepository;
//        this.movieRepository = movieRepository;
//
//    }
//
//    public void showListOfMovies() {
//        try {
//            List<MovieDTO> movies = movieRepository.getAll();
//            movies.forEach(movie -> System.out.println("ID " + movie.id() + ": " + movie.title()));
//
//            System.out.print("Enter your choice: ");
//            int movieId = inputValidation.isInputInt();
//
//            Optional<MovieDTO> selectedMovie = movieRepository.searchMovieById(movieId);
//            if (selectedMovie.isPresent()) {
//                System.out.println(selectedMovie);
//                System.out.print("Enter your choice: ");
//                if (inputValidation.yesOrNoValidation()) {
//                    editMovie(selectedMovie);
//                }
//            } else {
//                System.out.println("Invalid ID. Type it again.");
//            }
//        } catch (InputMismatchException e) {
//            System.out.println("This option is invalid, must be an integer number.");
//        }
//    }
//
//
//    public void registerNewMovie() {
//        try {
//            String name = inputValidation.isValidTitle();
//            int releaseDate = inputValidation.isValidReleaseDate();
//            double budget = inputValidation.isValidBudget();
//            String currency = inputValidation.enterCurrency();
//            String description = inputValidation.isValidDescription();
//            List<ActorDTO> actors = enterActors();
//            List<DirectorDTO> directors = enterDirectors();
//
//            MovieDTO newMovie = new MovieDTO(
//                    name,
//                    releaseDate,
//                    budget,
//                    currency,
//                    description,
//                    actors,
//                    directors
//            );
//            movieRepository.create(newMovie);
//        } catch (IllegalArgumentException e) {
//            System.out.println("This movie title already exists.");
//            System.out.print("Do you want to edit it? (Yes or No): ");
//
//            if (inputValidation.yesOrNoValidation()) {
//                //editMovie(); AQUIIIIIIIIIIIIIIIIIIIIIIIIII
//            } else return;
//        }
//
//        System.out.print("Do you want to add a new movie? (Yes or No): ");
//        if (inputValidation.yesOrNoValidation()) {
//            registerNewMovie();
//        } else {
//            System.out.println("Returning to main view...");
//        }
//    }
//
//    private List<ActorDTO> enterActors() {
//        List<ActorDTO> actors = new ArrayList<>();
//        int numberOfActors;
//        String nationality;
//
//        while (true) {
//            System.out.print("How many actors do you want to add? (Up to 15 actors): ");
//            numberOfActors = inputValidation.isInputInt();
//
//            if (numberOfActors < 1 || numberOfActors > 15) {
//                System.err.println("You can enter up to 15 actors.");
//            } else {
//                for (int i = 1; i <= numberOfActors; i++) {
//                    System.out.print("Enter the name of actor " + i + ": ");
//
//                    ActorDTO newActor = new ActorDTO(
//                            inputValidation.isValidPersonName(),
//                            inputValidation.isValidNationality()
//                    );
//
//                    try {
//                        actorRepository.create(newActor);
//                        actors.add(newActor);
//                    } catch (IllegalArgumentException e) {
//                        actors.add(newActor);
//                    }
//                }
//                return actors;
//            }
//        }
//    }
//
//    private static List<DirectorDTO> enterDirectors() {
//        List<DirectorDTO> directors = new ArrayList<>();
//        int numberOfDirectors;
//        String nationality;
//
//        while (true) {
//            System.out.print("How many directors do you want to add? (Up to 15 directors): ");
//            numberOfDirectors = ValidationInputService.isInputInt();
//
//            if (numberOfDirectors < 1 || numberOfDirectors > 15) {
//                System.err.println("You can enter up to 15 directors.");
//            } else {
//                for (int i = 1; i <= numberOfDirectors; i++) {
//                    System.out.print("Enter the name of director " + i + ": ");
//
//                    DirectorDTO newDirector = new DirectorDTO(
//                            ValidationInputService.isValidPersonName(),
//                            ValidationInputService.isValidNationality()
//                    );
//
//                    try {
//                        directorRepository.addDirector(newDirector);
//                        directors.add(newDirector);
//                    } catch (IllegalArgumentException e) {
//                        directors.add(newDirector);
//                    }
//                }
//                return directors;
//            }
//        }
//    }
//
//    public void editMovie(Optional<MovieDTO> selectedMovie) {
//        showListOfMovies();
//        System.out.println("Which movie do you want to edit?");
//        int movieIdToEdit = safeNextInt();
//
//        Optional<MovieDTO> movieToEdit = movieRepository.searchMovieById(movieIdToEdit);
//        if (movieToEdit.isPresent()) {
//            System.out.println("Editing movie: " + movieToEdit.get().title());
//            System.out.println("What would you like to edit?");
//            System.out.println("1. Title");
//            System.out.println("2. Release Date");
//            System.out.println("3. Budget");
//            System.out.println("4. Description");
//            System.out.println("5. Actors");
//            System.out.println("6. Directors");
//            System.out.println("7. Cancel");
//            System.out.print("Enter your choice: ");
//
//            int choice = safeNextInt();
//            switch (choice) {
//                case 1:
//                    editTitle(movieToEdit.get());
//                    break;
//                case 2:
//                  //  editReleaseDate(movieToEdit.get());
//                    break;
//                case 3:
//                   // editBudget(movieToEdit.get());
//                    break;
//                case 4:
//                   // editDescription(movieToEdit.get());
//                    break;
//                case 5:
//                    //actorService.updateActor(movieToEdit.get().getActors());
//                    break;
//                case 6:
//                    //directorService.updateDirector(movieToEdit.get().getActors());
//                    break;
//                case 7:
//                    System.out.println("Cancelling movie edit.");
//                    break;
//                default:
//                    System.out.println("Invalid choice.");
//                    break;
//            }
//        } else {
//            System.out.println("Movie with ID " + movieIdToEdit + " not found.");
//        }
//    }
//
//    public void editTitle(MovieDTO movie) {
//        System.out.print("Enter the new title: ");
//        String newTitle = inputValidation.isValidTitle();
//        movie.setTitle(newTitle);
//        movieRepository.updatee(movie);
//        System.out.println("Title updated successfully!");
//    }
//
//    public void editReleaseDate(Movie movie) {
//        System.out.print("Enter the new release date: ");
//        int newReleaseDate = inputValidation.isValidReleaseDate();
//        movie.setReleaseDate(newReleaseDate);
//        System.out.println("Release date updated successfully!");
//    }
//
//    public void editBudget(Movie movie) {
//        System.out.print("Enter the new budget: ");
//        double newBudget = inputValidation.isValidBudget();
//        movie.setBudget(newBudget);
//        System.out.println("Budget updated successfully!");
//    }
//
//    public void editDescription(Movie movie) {
//        System.out.print("Enter the new description: ");
//        String newDescription = inputValidation.isValidDescription();
//        movie.setDescription(newDescription);
//        System.out.println("Description updated successfully!");
//    }
//
//    public void deleteMovie() {
//        List<MovieDTO> movies = movieRepository.getAll();
//        if (movies.isEmpty()) {
//            System.out.println("No movies available for deletion.");
//            return;
//        }
//
//        System.out.println("Movie List:");
//        for (MovieDTO movie : movies) {
//            System.out.println("ID " + movie.id() + ": " + movie.title());
//        }
//
//        System.out.print("Enter the ID of the movie you want to delete: ");
//        int movieIdToDelete = inputValidation.isInputInt();
//
//        movieRepository.remove(
//                movieRepository.searchMovieById(movieIdToDelete).get()
//        );
//    }
//
//    private int safeNextInt() {
//        while (true) {
//            try {
//                int i = inputValidation.isInputInt();
//                return i;
//            } catch (InputMismatchException e) {
//                System.out.println("Invalid input. Please enter a valid number.");
//            }
//        }
//    }
//
//    public void searchMovie() {
//        System.out.println("Menu - Search");
//        System.out.println(
//                "Do you want to search for a Movie Title, Actor, Director or Release Date?"
//        );
//        System.out.println("1 - Movie Title");
//        System.out.println("2 - Actor");
//        System.out.println("3 - Director");
//        System.out.println("4 - Release Date");
//        System.out.print("Enter your choice: ");
//        int searchChoice = inputValidation.isValidOption(4, 1);
//
//        switch (searchChoice) {
//            case 1:
//                searchByTitle();
//                break;
//            case 2:
//                searchByActor();
//                break;
//            case 3:
//                searchByDirector();
//                break;
//            case 4:
//                searchByReleaseDate();
//                break;
//            default:
//                System.out.println("Invalid choice. Returning to main view...");
//        }
//    }
//
//    private void searchByTitle() {
//        System.out.print("What is the title of the movie you want to search for? ");
//        String title = inputValidation.isValidMovieName();
//        ArrayList<Movie> foundMovies = new ArrayList<>();
//        for (MovieDTO movie : movieRepository.getAll()) {
//            if (movie.title().equalsIgnoreCase(title)) {
//                foundMovies.add(movie);
//            }
//        }
//        displayMovieTitleSearchResult(foundMovies);
//    }
//
//    private void displayMovieTitleSearchResult(List<MovieDTO> movies) {
//        if (movies.isEmpty()) {
//            System.out.println("No movies found.");
//        } else {
//            for (MovieDTO movie : movies) {
//                System.out.println("Movie title: " + movie.title());
//                System.out.println("Release Date: " + movie.releaseDate());
//                System.out.println(
//                        "Budget: " + movie.budget() + " " + movie.currency()
//                );
//                System.out.println("Description: " + movie.description());
//                System.out.println("List of Actors:");
//                for (ActorDTO actor : movie.actors()) {
//                    System.out.println(actor.name());
//                }
//                System.out.println("List of Directors:");
//                for (DirectorDTO director : movie.directors()) {
//                    System.out.println(director.name());
//                }
//                System.out.println(
//                        "Do you want to look for another movie? (Yes or No): "
//                );
//
//                if (inputValidation.yesOrNoValidation()) {
//                    System.out.println("Returning to the main view...");
//                    break;
//                }
//            }
//        }
//    }
//
//    private void searchByActor() {
//        System.out.print("Which actor do you want to look for? ");
//        String actorName = inputValidation.isValidPersonName();
//        List<MovieDTO> foundMovies = new ArrayList<>();
//        for (MovieDTO movie : movieRepository.getAll()) {
//            for (ActorDTO actor : movie.actors()) {
//                if (actor.name().equalsIgnoreCase(actorName)) {
//                    foundMovies.add(movie);
//                    break;
//                }
//            }
//        }
//        displayActorSearchResult(foundMovies);
//    }
//
//    public void searchByDirector() {
//        System.out.print("Which director do you want to look for? ");
//        String directorName = inputValidation.isValidPersonName();
//        Optional<Director> director = directorRepository.searchDirector(directorName);
//        if (director.isEmpty()) {
//            System.out.println("Director not found.");
//            return;
//        }
//        searchByDirector(movieRepository.getAll(), director.get());
//    }
//
//    private void searchByDirector(List<MovieDTO> allMovies, Director director) {
//        List<MovieDTO> foundMovies = new ArrayList<>();
//        for (MovieDTO movie : allMovies) {
//            for (Director dir : movie.directors()) {
//                if (dir.getName().equalsIgnoreCase(director.getName())) {
//                    foundMovies.add(movie);
//                    break;
//                }
//            }
//        }
//        displayDirectorSearchResult(foundMovies);
//    }
//
//    private void searchByReleaseDate() {
//       System.out.print("What release year are you looking for? ");
//        int releaseDate = inputValidation.isValidReleaseDate();
//        List<MovieDTO> foundMovies = new ArrayList<>();
//        for (MovieDTO movie : movieRepository.getAll()) {
//            if (String.valueOf(movie.getReleaseDate()).equals(releaseDate)) {
//                foundMovies.add(movie);
//            }
//        }
//        displayReleaseDateSearchResult(foundMovies);
//    }
//
//    private void displayActorSearchResult(List<Movie> movies) {
//        if (movies.isEmpty()) {
//            System.out.println("No movies found for this actor.");
//        } else {
//            for (Movie movie : movies) {
//                System.out.println(
//                        "Movie title: " +
//                                movie.getTitle() +
//                                "\tRelease Date: " +
//                                movie.getReleaseDate()
//                );
//            }
//            System.out.println(
//                    "Do you want to see more details about any movie on the list? (Yes or No): "
//            );
//            if (inputValidation.yesOrNoValidation()) {
//                System.out.print("Enter the movie ID: ");
//                int movieId = safeNextInt();
//                ArrayList<MovieDTO> aux = new ArrayList<>();
//                aux.add(movieRepository.searchMovieById(movieId).get());
//                displayMovieTitleSearchResult(aux);
//            } else {
//                System.out.println("Returning to the main view...");
//            }
//        }
//    }
//
//    private void displayDirectorSearchResult(List<MovieDTO> movies) {
//        if (movies.isEmpty()) {
//            System.out.println("No movies found for this director.");
//        } else {
//            for (MovieDTO movie : movies) {
//                System.out.println(
//                        "Movie title: " +
//                                movie.title() +
//                                "\tRelease Date: " +
//                                movie.releaseDate()
//                );
//            }
//            System.out.println(
//                    "Do you want to see more details about any movie on the list? (Yes or No): "
//            );
//            if (inputValidation.yesOrNoValidation()) {
//                System.out.print("Enter the movie ID: ");
//                int movieId = inputValidation.isInputInt();
//
//                Optional<MovieDTO> selectedMovie = movieRepository.searchMovieById(movieId);
//                if (selectedMovie.isPresent()) {
//                   // printMovieDetails(selectedMovie.get());
//                } else {
//                    System.out.println("Movie with ID " + movieId + " not found.");
//                }
//            } else {
//                System.out.println("Returning to the main view...");
//            }
//        }
//    }
//
//    private void displayReleaseDateSearchResult(List<MovieDTO> movies) {
//        if (movies.isEmpty()) {
//            System.out.println("No movies found for this release year.");
//        } else {
//            for (MovieDTO movie : movies) {
//                System.out.println("Movie title: " + movie.title());
//            }
//            System.out.println(
//                    "Do you want to see more details about any movie on the list? (Yes or No): "
//            );
//
//
//            if (inputValidation.yesOrNoValidation()) {
//                System.out.print("Enter the movie ID: ");
//                int movieId = inputValidation.isInputInt();
//                ArrayList<MovieDTO> aux = new ArrayList<>();
//                aux.add(movieRepository.searchMovieById(movieId).get());
//                displayMovieTitleSearchResult(aux);
//            } else {
//                System.out.println("Returning to the main view...");
//            }
//        }
//    }*/
//
//package com.imdb.controller;
//
//import com.imdb.dto.ActorDTO;
//import com.imdb.dto.DirectorDTO;
//import com.imdb.dto.MovieDTO;
//import com.imdb.repository.IMovieRepository;
//
//import java.util.List;
//import java.util.Scanner;
//
//public class MovieController {
//    private final IMovieRepository movieRepository;
//
//
//    public MovieController(IMovieRepository movieRepository) {
//
//        this.movieRepository = movieRepository;
//    }
//    private Scanner scanner = new Scanner(System.in);
//
//    public void showListOfMovies() {
//        movieRepository.getAll().forEach(System.out::println);
//    }
//
//    public void registerMovie() {
//        System.out.println("Enter the details of the movie (id, title, releaseDate, budget, currency, description):");
//        int id = scanner.nextInt();
//        String title = scanner.next();
//        int releaseDate = scanner.nextInt();
//        double budget = scanner.nextDouble();
//        String currency = scanner.next();
//        scanner.nextLine();
//        String description = scanner.nextLine();
//        MovieDTO newMovie = new MovieDTO(id, title, releaseDate, budget, currency, description, null, null);
//        movieRepository.create(newMovie);
//    }
//
//    public void updateMovie() {
//        System.out.println("Enter the ID of the movie to update:");
//        int id = scanner.nextInt();
//        scanner.nextLine();
//        MovieDTO movieToUpdate = movieRepository.readById(id);
//        if (movieToUpdate != null) {
//            System.out.println("Enter the new title:");
//            String title = scanner.nextLine();
//
//            System.out.println("Enter the new release date:");
//            int releaseDate = scanner.nextInt();
//
//            System.out.println("Enter the new budget:");
//            double budget = scanner.nextDouble();
//
//            scanner.nextLine();
//            System.out.println("Enter the new currency:");
//            String currency = scanner.nextLine();
//
//            System.out.println("Enter the new description:");
//            String description = scanner.nextLine();
//
//            List<ActorDTO> actors = movieToUpdate.actors();
//            List<DirectorDTO> directors = movieToUpdate.directors();
//
//            MovieDTO updatedMovie = new MovieDTO(
//                    movieToUpdate.id(),
//                    title,
//                    releaseDate,
//                    budget,
//                    currency,
//                    description,
//                    actors,
//                    directors
//            );
//
//            movieRepository.update(movieToUpdate, updatedMovie);
//            System.out.println("Movie updated successfully.");
//        } else {
//            System.out.println("Movie not found.");
//        }
//    }
//
//    public void delete() {
//        System.out.println("Enter the ID of the movie to delete:");
//        int id = scanner.nextInt();
//        movieRepository.delete(id);
//    }
//
//    public void searchMovies() {
//        System.out.println("Enter the title keyword to search for:");
//        String keyword = scanner.next();
//        movieRepository.searchByTitle(keyword).forEach(System.out::println);
//    }
//}
