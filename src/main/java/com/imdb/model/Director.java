package com.imdb.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a director within the IMDb system.
 * This class contains details about a director, such as their ID, name, nationality and birthdate.
 */

public class Director {
  private int id;
  private String name;
  private String nationality;
  private LocalDate birthDate;
  private List<Movie> movies;

  /**
   * Constructor for creating a director instance.
   *
   * @param id          Unique identifier for the director.
   * @param name        Name of the director.
   * @param nationality Nationality of the director.
   * @param birthDate   Birthdate of the director.
   */

  public Director(int id, String name, String nationality, LocalDate birthDate) {
    this.id = id;
    this.name = name;
    this.nationality = nationality;
    this.birthDate = birthDate;
    this.movies = new ArrayList<>(10);
  }

  /**
   * Gets the director's ID.
   *
   * @return The director's unique identifier.
   */

  public int getId() {
    return id;
  }

  /**
   * Sets the director's ID.
   *
   * @param id The new ID for the director.
   */

  public void setId(int id) {
    this.id = id;
  }

  /**
   * Gets the director's name.
   *
   * @return The name of the director.
   */

  public String getName() {
    return name;
  }

  /**
   * Sets the director's name.
   *
   * @param name The new name for the director.
   */

  public void setName(String name) {
    this.name = name;
  }

  /**
   * Gets the director's nationality.
   *
   * @return The nationality of the director.
   */

  public String getNationality() {
    return nationality;
  }

  /**
   * Sets the director's nationality.
   *
   * @param nationality The new nationality for the director.
   */

  public void setNationality(String nationality) {
    this.nationality = nationality;
  }

  /**
   * Gets the director's birthdate.
   *
   * @return The birthdate of the director.
   */

  public LocalDate getBirthDate() {
    return birthDate;
  }

  /**
   * Sets the director's birthdate.
   *
   * @param birthDate The new birthdate for the director.
   */

  public void setBirthDate(LocalDate birthDate) {
    this.birthDate = birthDate;
  }

  /**
   * Gets the list of movies the director has participated in.
   *
   * @return A list of movies associated with the director.
   */

  public List<Movie> getMovies() {
    return movies;
  }

  /**
   * Sets the list of movies the director has participated in.
   *
   * @param movies The new list of movies for the director.
   */

  public void setMovies(List<Movie> movies) {
    this.movies = movies;
  }
}