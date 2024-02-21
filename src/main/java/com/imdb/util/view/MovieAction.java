package com.imdb.util.view;

public enum MovieAction {
    MAIN_MENU_TEXT("""
            Main Menu - CRUD (Create, Read, Update, Delete) + Search
            1 - Show list of movies
            2 - Register a new movie
            3 - Edit movie
            4 - Delete movie
            5 - Search
            6 - Close the program
            """),
    SHOW_MOVIE_LIST("Showing list of movies..."),
    REGISTER_MOVIE("Registering a new movie..."),
    MOVIE_ALREADY_EXISTS("This movie title already exists, do you want to edit it? (open movie edit if yes, go back to enter the name of the movie if no"),
    EDIT_MOVIE("Editing a movie..."),
    DELETE_MOVIE("Deleting a movie..."),
    SEARCH("Searching..."),
    RETURN_TO_MAIN_MENU("Returning to the main view..."),
    ENTER_CHOICE("Enter your choice: ");


    /*"This movie title already exists, do you want to edit it? (open movie edit if yes, go back to enter the name of the movie if no)

    A("The year of release must not contain letters. Type it again!"),

    The year of release must contain only 4 digits. Type it again!

    Release year must not start with 0. Enter again!

    This currency does not exist. Enter a number from 1 to 3.

    The budget must contain only numbers. Type it again.

    The description cannot exceed 500 words. Type it again!

    You can enter up to 15 actors.
    You must add at least 1 actor.
    This actor already exists in this film. (go back to adding an actor asking for a new name different from the existing one or give the option to go to add directors without adding any other actors, as long as there is already at least one actor added)


    You can enter up to 10 directors.
    You must add at least 1 director.
    This director already exists in this film. (go back to adding a director asking for a new name different from the existing one or giving the option to save the film without adding another director, as long as there is already at least one director added)"
*/
    private final String message;

    MovieAction(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
