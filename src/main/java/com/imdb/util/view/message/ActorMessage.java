package com.imdb.util.view.message;

public enum ActorMessage {
        LIST_NOT_FOUND(Colors.RED + "No actors found. Returning to the view..." + Colors.RESET),
        REGISTERED(Colors.GREEN + "Actor registered successfully." + Colors.RESET),
        UPDATED(Colors.GREEN + "Actor updated successfully." + Colors.RESET),
        DELETED(Colors.GREEN + "Actor deleted successfully." + Colors.RESET);

        private final String message;

        ActorMessage(String message) {
            this.message = message;
        }

        public String get() {
            return message;
        }
}
