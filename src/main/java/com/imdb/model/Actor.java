package com.imdb.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents an actor within the IMDb system.
 * This class holds information about an actor, including their ID, name, nationality and birthdate.
 */

public class Actor {
  private int id;
  private String name;
  private String nationality;
  private LocalDate birthDate;
  private List<Movie> movies;

  /**
   * Constructor for creating an Actor instance.
   *
   * @param id          Unique identifier for the actor.
   * @param name        Name of the actor.
   * @param nationality Nationality of the actor.
   * @param birthDate   Birthdate of the actor.
   */

  public Actor(int id, String name, String nationality, LocalDate birthDate) {
    this.id = id;
    this.name = name;
    this.nationality = nationality;
    this.birthDate = birthDate;
    this.movies = new ArrayList<>(10);
  }

  /**
   * Gets the actor's ID.
   *
   * @return The actor's unique identifier.
   */

  public int getId() {
    return id;
  }

  /**
   * Sets the actor's ID.
   *
   * @param id The new ID for the actor.
   */

  public void setId(int id) {
    this.id = id;
  }

  /**
   * Gets the actor's name.
   *
   * @return The name of the actor.
   */

  public String getName() {
    return name;
  }

  /**
   * Sets the actor's name.
   *
   * @param name The new name for the actor.
   */

  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the actor's nationality.
   *
   * @return The nationality of the actor.
   */

  public String getNationality() {
    return nationality;
  }

  /**
   * Sets the actor's nationality.
   *
   * @param nationality The new nationality for the actor.
   */

  public void setNationality(String nationality) {
    this.nationality = nationality;
  }

  /**
   * Gets the actor's birthdate.
   *
   * @return The birthdate of the actor.
   */

  public LocalDate getBirthDate() {
    return birthDate;
  }

  /**
   * Sets the actor's birthdate.
   *
   * @param birthDate The new birthdate for the actor.
   */

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  /**
   * Gets the list of movies the actor has participated in.
   *
   * @return A list of movies associated with the actor.
   */

  public List<Movie> getMovies() {
    return movies;
  }

  /**
   * Sets the list of movies the actor has participated in.
   *
   * @param movies The new list of movies for the actor.
   */

  public void setMovies(List<Movie> movies) {
    this.movies = movies;
  }
}
