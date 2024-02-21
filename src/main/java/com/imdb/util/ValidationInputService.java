package com.imdb.util;

import com.imdb.util.view.ErrorMessagesRegisterUpdateMovie;
import com.imdb.util.view.RegisterUpdateMovie;

import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;


public class ValidationInputService {
    private final Scanner scanner;

    public ValidationInputService(Scanner scanner) {
        this.scanner = scanner;
    }

    private String getInput() {
        return scanner.nextLine();
    }

    public int isInputInt() {
        try {
            return Integer.parseInt(getInput());
        } catch (NumberFormatException e) {
            System.out.println("This option is invalid, must be a integer number.");
            return -1;
        }
    }

    public static final Pattern DATE_PATTERN = Pattern.compile("^[1-9]\\d{3}$");
    public static final int MAX_ACTORS = 15;
    public static final int MAX_DIRECTORS = 10;
    public static final int MAX_DESCRIPTION_WORDS = 500;


    public double isValidBudget() {
        System.out.print("Budget: ");
        try {
            return Double.parseDouble(getInput());
        } catch (NumberFormatException e) {
            System.out.println("This input is invalid, must be a number.");
            return -1;
        }
    }


    public String enterCurrency() {
        System.out.println(
                "Enter the movie Currency. Will it be in Euro, Dollar or Real?"
        );
        System.out.println("1 - Euro");
        System.out.println("2 - Dollar");
        System.out.println("3 - Real");
        System.out.print("Enter your choice: ");
        while (true) {
            try {
                int currencyChoice = isValidOption(3, 1);
                switch (currencyChoice) {
                    case 1 -> {
                        return "Euro";
                    }
                    case 2 -> {
                        return "Dollar";
                    }
                    case 3 -> {
                        return "Real";
                    }
                }
                ;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public String isValidDescription() {
        System.out.print(
                "Enter the description of the movie (must not exceed 500 words): "
        );
        final String userInput = getInput();

        if (userInput != null && !userInput.trim().isEmpty() && userInput.length() <= 500) {
            return userInput;
        } else {
            System.out.println("Invalid Description! The description name must be at most 500 characters long.");
            return isValidTitle();
        }
    }

    public String isValidPersonName() {
        final String userInput = getInput();

        if (userInput != null && !userInput.trim().isEmpty()) {
            return userInput;
        } else {
            System.out.println("Invalid name!");
            return isValidPersonName();
        }
    }

    public String isValidNationality() {
        System.out.print("Add nationality: ");
        final String userInput = getInput();

        if (userInput != null && !userInput.trim().isEmpty() && userInput.length() >= 3) {
            return userInput;
        } else {
            System.out.println("Invalid nationality! The nationality must have the full name");
            return isValidNationality();
        }
    }

    public boolean isValidReleaseDate(String errorMessage, String invalidReleaseDateLength, String invalidReleaseDateStart) {
        System.out.println(RegisterUpdateMovie.ENTER_RELEASE_DATE);
        String releaseDate = getInput();

        if (!DATE_PATTERN.matcher(releaseDate).matches()) {
            System.out.println(errorMessage);
            return false;
        }
        return true;
    }

    public boolean yesOrNoValidation() {
        String input = getInput();
        return input.equalsIgnoreCase("yes");
    }

    //faz a mesma coisa que isInputInt(), mas com double


    public int isValidOption(int rangeMax, int rangeMin) {
        // Checks if the entered option is a number and if it is within the range of options

        //não precisa ser uma constante
        //isInputInt retorna um -1 caso entre no catch
        final int userChoice = isInputInt();

        //se for -1, ou seja, se não foi um inteiro válido
        if (userChoice == -1) {
            return -1;
            //verifica se é alguma das opções do view
        } else if (userChoice >= rangeMin && userChoice <= rangeMax) {
            //retorna a opção caso ela esteja no intervalo
            return userChoice;
            //era um número inteiro válido, mas não estava dentro do intervalo das opções
        } else {
            System.out.println("This option is invalid, you must choose a number from " + rangeMin + " to " + rangeMax);
            return -1;
        }
    }
    public String isValidTitle() {
        System.out.println(RegisterUpdateMovie.ENTER_MOVIE_NAME);
        final String userInput = getInput();

            if (userInput != null && !userInput.trim().isEmpty() && userInput.length() >= 3) {
            return userInput;
        } else {
            System.out.println("Invalid title! The movie name must be at least 3 characters long.");
            return isValidTitle();
        }
    }


//    public static class ErrorMessagesGeneral {
//        public static final String ERROR_INVALID_OPTION = "This option is invalid, you must choose a number from %d to %d.";
//        public static final String ERROR_NOT_INTEGER = "This option is invalid, must be an integer number.";
//        public static final String ERROR_ID_NOT_EXIST = "This ID does not exist.";
//        public static final String ERROR_RETRIEVING_MOVIES = "Failed to retrieve movies: ";
//        public static final String FAILED_TO_SAVE_MOVIE = "Failed to save movie: ";
//    }
//
//
//
//    public static class ErrorMessagesRegisterUpdateMovie {
//        public static final String MOVIE_TITLE_ALREADY_EXISTS = "Movie title already exists. Do you want to edit this movie? (yes/no):";
//        public static final String INVALID_RELEASE_DATE_LETTERS = "Invalid release year: must contain only digits.";
//        public static final String INVALID_RELEASE_DATE_LENGTH = "Invalid release year: must have exactly 4 digits.";
//        public static final String INVALID_RELEASE_DATE_START = "Invalid release year: cannot start with 0.";
//        public static final String INVALID_CURRENCY = "Invalid currency option. Please choose from the available options.";
//        public static final String INVALID_DESCRIPTION_LENGTH = "Invalid description length: must not exceed " + MAX_DESCRIPTION_WORDS + " words.";
//        public static final String ACTOR_ALREADY_EXISTS = "Actor already exists.";
//        public static final String DIRECTOR_ALREADY_EXISTS = "Director already exists.";
//        public static final String MAX_DIRECTORS_EXCEEDED = "Cannot add more than " + MAX_DIRECTORS + " directors.";
//        public static final String MAX_ACTORS_EXCEEDED = "Cannot add more than " + MAX_ACTORS + " actors.";
//    }

    public static boolean isActorNameValid(String actorName, Set<String> existingActors) {
        if (existingActors.contains(actorName)) {
            System.out.println(ErrorMessagesRegisterUpdateMovie.ACTOR_ALREADY_EXISTS);
            return false;
        }
        return true;
    }

    public static boolean isDirectorNameValid(String directorName, Set<String> existingDirectors) {
        if (existingDirectors.contains(directorName)) {
            System.out.println(ErrorMessagesRegisterUpdateMovie.DIRECTOR_ALREADY_EXISTS);
            return false;
        }
        return true;
    }

    public static boolean isNumberOfDirectorsValid(int numberOfDirectors) {
        if (numberOfDirectors > MAX_DIRECTORS) {
            System.out.println(ErrorMessagesRegisterUpdateMovie.MAX_DIRECTORS_EXCEEDED);
            return false;
        }
        return true;
    }

    public static boolean isNumberOfActorsValid(int numberOfActors) {
        if (numberOfActors > MAX_ACTORS) {
            System.out.println(ErrorMessagesRegisterUpdateMovie.MAX_ACTORS_EXCEEDED);
            return false;
        }
        return true;
    }
}