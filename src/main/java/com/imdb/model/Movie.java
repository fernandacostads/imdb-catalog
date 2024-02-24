package com.imdb.model;

import java.util.List;

/**
 * Represents a movie within the IMDb system, encapsulating comprehensive details about the movie.
 * It includes information such as the movie's ID, title, release date, budget, currency, description,
 * and lists of associated actors and directors.
 */

public class Movie {
  private List<Actor> actors;
  private List<Director> directors;
  private int id;
  private String title;
  private int releaseDate;
  private double budget;
  private String currency;
  private String description;

  /**
   * Constructs a new Movie instance with specified details and associations.
   *
   * @param id          The unique identifier for the movie.
   * @param title       The title of the movie.
   * @param releaseDate The release date of the movie.
   * @param budget      The budget allocated for the movie.
   * @param currency    The currency in which the budget is specified.
   * @param description A brief synopsis or description of the movie.
   * @param actors      A list of actors associated with the movie.
   * @param directors   A list of directors of the movie.
   */

  public Movie(int id, String title, int releaseDate, double budget, String currency, String description, List<Actor> actors, List<Director> directors) {
    this.id = id;
    this.title = title;
    this.releaseDate = releaseDate;
    this.budget = budget;
    this.currency = currency;
    this.description = description;
    this.actors = actors;
    this.directors = directors;

    for (Actor actor : actors) {
      actor.getMovies().add(this);
    }

    for (Director director : directors) {
      director.getMovies().add(this);
    }
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
  public void setActors(List<Actor> actors) {
    this.actors = actors;
  }

  public void setDirectors(List<Director> directors) {
    this.directors = directors;
  }


  /**
   * Gets the list of directors of the movie.
   *
   * @return A list of directors of the movie.
   */

  public List<Director> getDirectors() {
    return directors;
  }

}
