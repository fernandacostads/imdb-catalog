package com.imdb.util;

import java.util.Scanner;

public class ValidationInputService {
    private Scanner sc;

    public ValidationInputService(Scanner scanner) {
        this.sc = scanner;
    }

    private String getInput() {
        return sc.nextLine();
    }

    public int isInputInt() {
        try {
            return Integer.parseInt(getInput());
        } catch (NumberFormatException e) {
            System.out.println("This option is invalid, must be a integer number.");
            return -1;
        }
    }

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
            return isValidMovieName();
        }
    }

    public String isValidPersonName() {
        final String userInput = getInput();

        if (userInput != null && !userInput.trim().isEmpty()) {
            return userInput;
        } else {
            System.out.println("Invalid name!");
            return isValidMovieName();
        }
    }

    public String isValidNationality() {
        System.out.print("Add nationality: ");
        final String userInput = getInput();

        if (userInput != null && !userInput.trim().isEmpty() && userInput.length() >= 3) {
            return userInput;
        } else {
            System.out.println("Invalid nationality! The nationality must have the full name");
            return isValidMovieName();
        }
    }

    public int isValidReleaseDate() {
        System.out.print(
                "Enter Movie Release Date (Year of release that must accept a number with only 4 digits and must not start with 0): "
        );
        while (true) {
            try {
                int releaseDate = Integer.parseInt(getInput());
                if (String.valueOf(releaseDate).length() != 4) {
                    throw new IllegalArgumentException(
                            "The year of release must contain only 4 digits. Type it again!"
                    );
                } else if (String.valueOf(releaseDate).startsWith("0")) {
                    throw new IllegalArgumentException(
                            "Release year must not start with 0. Enter again!"
                    );
                }
                return releaseDate;
            } catch (NumberFormatException e) {
                System.out.println(
                        "The year of release must not contain letters. Type it again!"
                );
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
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
            //verifica se é alguma das opções do menu
        } else if (userChoice >= rangeMin && userChoice <= rangeMax) {
            //retorna a opção caso ela esteja no intervalo
            return userChoice;
            //era um número inteiro válido, mas não estava dentro do intervalo das opções
        } else {
            System.out.println("This option is invalid, you must choose a number from " + rangeMin + " to " + rangeMax);
            return -1;
        }
    }

    public String isValidMovieName() {
        System.out.print("Enter the name of the movie: ");
        final String userInput = getInput();

        if (userInput != null && !userInput.trim().isEmpty() && userInput.length() >= 3) {
            return userInput;
        } else {
            System.out.println("Invalid title! The movie name must be at least 3 characters long.");
            return isValidMovieName();
        }
    }

    public static class ErrorMessagesGeneral {
        public static final String ERROR_INVALID_OPTION = "This option is invalid, you must choose a number from %d to %d.";
        public static final String ERROR_NOT_INTEGER = "This option is invalid, must be an integer number.";
        public static final String ERROR_ID_NOT_EXIST = "This ID does not exist.";
        public static final String ERROR_RETRIEVING_MOVIES = "Failed to retrieve movies: ";
        public static final String FAILED_TO_SAVE_MOVIE = "Failed to save movie: ";
    }

    public static class ErrorMessagesGeneral {
        public static final String ERROR_INVALID_OPTION = "This option is invalid, you must choose a number from %d to %d.";
        public static final String ERROR_NOT_INTEGER = "This option is invalid, must be an integer number.";
        public static final String ERROR_ID_NOT_EXIST = "This ID does not exist.";
    }


}