/*
package com.imdb.util.view;

public enum ActorMenuOption {


    ACTOR_MENU_OPTION("""
            1. Create Actor
            2. View Actor
            3. View All Actors
            4. Update Actor
            5. Delete Actor
            0. Exit
            Enter your choice:\s"""),
    SHOW_ACTOR_LIST("Showing list of actors..."),


    ACTOR_ALREADY_EXISTS("The actor already exists on the film list. Do you want to add a new one? (yes/no)"), */
/*--------------> (If yes, ask again. If no, go back to the edit view)*//*

    REGISTER_ACTOR("Registering a new actor..."),
    ERROR_NO_ACTOR("You must add at least 1 actor."),
    REGISTER_ACTOR_NAME("Enter actor's name:"),
    REGISTER_ACTOR_NATIONALITY("Enter actor's nationality:"),
    REGISTER_ACTOR_CHOICE("Actor registered successfully!"),
    EDIT_ACTOR("Editing an actor..."),
    EDIT_ACTOR_CHOICE("Which actor do you want to edit? Enter the ID."),
    ACTOR_ALREADY_EXISTS_EDITING("This actor already exists in this film."), */
/*---------------> (if user try to add an actor which is already there on actors list.
    Go back to adding an actor asking for a new name different from the existing one or give the option to go to add actors without adding any other actors, as long as there is already at least one actor added)*//*

    EDIT_ACTOR_MESSAGE("Actor edited successfully!"),
    DELETE_ACTOR("Deleting an actor..."),
    DELETE_ACTOR_CHOICE("Which actor do you want to remove? Enter the ID."),
    DELETE_ACTOR_MESSAGE("Actor removed successfully!"),
    ERROR_REMOVE_ACTOR("You cannot remove this actor. The film must contain at least one actor. Edit the existing actor!");  */
/*-----------------> (switch to the existing actor's edit in the list)*//*

    private final String description;

    ActorMenuOption(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
*/
