package com.imdb.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a movie within the IMDb system, encapsulating comprehensive details about the movie.
 * It includes information such as the movie's ID, title, release date, budget, currency, description,
 * and lists of associated actors and directors.
 */

public class Movie {

  private int id;
  private String title;
  private int releaseDate;
  private double budget;
  private String currency;
  private String description;
  private final List<Actor> actors;
  private final List<Director> directors;

  /**
   * Constructs a new Movie instance with specified details and associations.
   *
   * @param id          The unique identifier for the movie.
   * @param title       The title of the movie.
   * @param releaseDate The release date of the movie.
   * @param budget      The budget allocated for the movie.
   * @param currency    The currency in which the budget is specified.
   * @param description A brief synopsis or description of the movie.
   */

  public Movie(
    int id,
    String title,
    int releaseDate,
    double budget,
    String currency,
    String description
  ) {
    this.id = id;
    this.title = title;
    this.releaseDate = releaseDate;
    this.budget = budget;
    this.currency = currency;
    this.description = description;
    this.actors = new ArrayList<>();
    this.directors = new ArrayList<>();
  }

  /**
   * Gets the unique identifier for the movie.
   *
   * @return The movie's unique identifier.
   */

  public int getId() {
    return id;
  }

  /**
   * Sets the unique identifier for the movie.
   *
   * @param id The unique identifier to set.
   */

  public void setId(int id) {
    this.id = id;
  }

  /**
   * Gets the title of the movie.
   *
   * @return The title of the movie.
   */

  public String getTitle() {
    return title;
  }

  /**
   * Sets the title of the movie.
   *
   * @param title The title to set for the movie.
   */

  public void setTitle(String title) {
    this.title = title;
  }

  /**
   * Gets the release date of the movie.
   *
   * @return The release date of the movie.
   */

  public int getReleaseDate() {
    return releaseDate;
  }

  /**
   * Sets the release date of the movie.
   *
   * @param releaseDate The release date to set for the movie.
   */

  public void setReleaseDate(int releaseDate) {
    this.releaseDate = releaseDate;
  }

  /**
   * Gets the budget of the movie.
   *
   * @return The budget of the movie.
   */

  public double getBudget() {
    return budget;
  }

  /**
   * Sets the budget for the movie.
   *
   * @param budget The budget to set for the movie.
   */

  public void setBudget(double budget) {
    this.budget = budget;
  }

  /**
   * Gets the currency in which the movie's budget is specified.
   *
   * @return The currency of the movie's budget.
   */

  public String getCurrency() {
    return currency;
  }

  /**
   * Sets the currency for the movie's budget.
   *
   * @param currency The currency to set for the movie's budget.
   */

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  /**
   * Gets the description or synopsis of the movie.
   *
   * @return The description of the movie.
   */

  public String getDescription() {
    return description;
  }

  /**
   * Sets the description or synopsis for the movie.
   *
   * @param description The description to set for the movie.
   */

  public void setDescription(String description) {
    this.description = description;
  }

  /**
   * Gets the list of actors associated with the movie.
   *
   * @return A list of actors associated with the movie.
   */

  public List<Actor> getActors() {
    return actors;
  }

  /**
   * Gets the list of directors of the movie.
   *
   * @return A list of directors of the movie.
   */

  public List<Director> getDirectors() {
    return directors;
  }

  /**
   * Sets the list of actors associated with the movie.
   *
   * @param actors The new list of actors.
   */
  public void setActors(List<Actor> actors) {
    this.actors.clear();
    if (actors != null) {
      this.actors.addAll(actors);
    }
  }

  /**
   * Sets the list of directors associated with the movie.
   *
   * @param directors The new list of directors.
   */
  public void setDirectors(List<Director> directors) {
    this.directors.clear();
    if (directors != null) {
      this.directors.addAll(directors);
    }
  }

  public void addActor(Actor actor) {
    this.actors.add(actor);
    actor.addMovie(this);
  }

  public void addDirector(Director director) {
    this.directors.add(director);
    director.addMovie(this);
  }
}
