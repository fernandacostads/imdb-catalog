package com.imdb.util.view.message;

public enum DirectorMessage {
    LIST_NOT_FOUND(Colors.RED + "No directors found. Returning to the view..." + Colors.RESET),
    REGISTERED(Colors.GREEN + "Director registered successfully." + Colors.RESET),
    UPDATED(Colors.GREEN + "Director updated successfully." + Colors.RESET),
    DELETED(Colors.GREEN + "Director deleted successfully." + Colors.RESET);

    private final String message;

    DirectorMessage(String message) {
        this.message = message;
    }

    public String get() {
        return message;
    }
}


