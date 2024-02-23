package com.imdb.util.view.message;

public enum MenuMessage {
    MAIN_MENU(Colors.YELLOW + " - Main Menu - \n" + Colors.RESET +
            "1 - Show lists (Movies, Actors, Directors)\n" +
            "2 - Register a new movie\n" +
            "3 - Edit movie\n" +
            "4 - Delete movie\n" +
            "5 - Search\n" +
            "0 - Close the program\n" +
            "Please enter your choice: "),

    LIST_MENU(Colors.YELLOW + "Which list do you want to display?\n" + Colors.RESET +
            "1 - Show list of movies\n" +
            "2 - Show list of actors\n" +
            "3 - Show list of directors\n" +
            "4 - Return to main view");

    private final String message;

    MenuMessage(String message) {
        this.message = message;
    }

    public String get() {
        return message;
    }

}

