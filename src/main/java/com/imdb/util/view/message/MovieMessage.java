package com.imdb.util.view.message;

public enum MovieMessage {
    LIST_NOT_FOUND(Colors.RED + "No movies found. Returning to the view..." + Colors.RESET),
    REGISTERED(Colors.GREEN + "Movie registered successfully." + Colors.RESET),
    UPDATED(Colors.GREEN + "Movie updated successfully." + Colors.RESET),
    DELETED(Colors.GREEN + "Movie deleted successfully." + Colors.RESET);

    private final String message;

    MovieMessage(String message) {
        this.message = message;
    }

    public String get() {
        return message;
    }
}

