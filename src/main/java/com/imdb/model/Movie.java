package com.imdb.model;

import java.time.LocalDate;
import java.util.List;

public class Movie {

  private int id;
  private String title;
  private LocalDate releaseDate;
  private double budget;
  private String synopsis;
  private List<Actor> listOfActors;
  private List<Director> listOfDirectors;
  private List<Rater> listOfRatings;
  private List<Genre> genres;
  private int duration;
  private String language;

  //CONSTRUCTOR
  public Movie(
    String title,
    LocalDate releaseDate,
    double budget,
    String synopsis,
    List<Genre> genres,
    int duration,
    String language
  ) {
    this.title = title;
    this.releaseDate = releaseDate;
    this.budget = budget;
    this.synopsis = synopsis;
    this.genres = genres;
    this.duration = duration;
    this.language = language;
  }

  //GETTERS E SETTERS
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

  public LocalDate getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(LocalDate releaseDate) {
    this.releaseDate = releaseDate;
  }

  public double getBudget() {
    return budget;
  }

  public void setBudget(double budget) {
    this.budget = budget;
  }

  public String getSynopsis() {
    return synopsis;
  }

  public void setSynopsis(String synopsis) {
    this.synopsis = synopsis;
  }

  public List<Actor> getListOfActors() {
    return listOfActors;
  }

  public void setListOfActors(List<Actor> listOfActors) {
    this.listOfActors = listOfActors;
  }

  public List<Rater> getListOfRatings() {
    return listOfRatings;
  }

  public void setListOfRatings(List<Rater> listOfRatings) {
    this.listOfRatings = listOfRatings;
  }

  public List<Genre> getGenres() {
    return genres;
  }

  public void setGenres(List<Genre> genres) {
    this.genres = genres;
  }

  public int getDuration() {
    return duration;
  }

  public void setDuration(int duration) {
    this.duration = duration;
  }

  public String getLanguage() {
    return language;
  }

  public void setLanguage(String language) {
    this.language = language;
  }

  @Override
  public String toString() {
    return (
      "/--------------------------------------------------------------------------" +
      "\n  id=" +
      id +
      "                                              ★: " +
      String.format("%.1f", 3.974) +
      "/5" +
      "\n  título= " +
      title +
      "\n  Data de Lançamento= " +
      releaseDate +
      "                    Orçamento ≈ R$ " +
      String.format("%.0f", budget) +
      "\n  Descrição= " +
      synopsis +
      "\n  Atores= " +
      listOfActors +
      "\n  Dietores= " +
      listOfRatings +
      "\n  Gênero= " +
      genres +
      "            Duração= " +
      duration +
      " min" +
      "           Idioma= " +
      language +
      "\n--------------------------------------------------------------------------/"
    );
  }
}
