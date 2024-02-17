package com.imdb.model;

import java.util.List;

public class Movie {
  private int id;
  private String title;
  private int releaseDate;
  private double budget;
  private String currency;
  private String description;
  private final List<Director> directors;
  private final List<Actor> actors;

  public Movie(String title, int releaseDate, double budget, String currency, String description, List<Actor> actors, List<Director> directors) {
    this.title = title;
    this.releaseDate = releaseDate;
    this.budget = budget;
    this.currency = currency;
    this.description = description;
    this.actors = actors;
    this.directors = directors;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public int getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(int releaseDate) {
    this.releaseDate = releaseDate;
  }

  public double getBudget() {
    return budget;
  }

  public void setBudget(double budget) {
    this.budget = budget;
  }

  public String getCurrency() {
    return currency;
  }

  public void setCurrency(String currency) {
    this.currency = currency;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public List<Actor> getActors() {
    return actors;
  }

  public List<Director> getDirectors() {
    return directors;
  }
}