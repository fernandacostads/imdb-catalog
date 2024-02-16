package com.imdb.model;

import java.time.LocalDate;
import java.util.List;

public class Movie {

  private int id;
  private String title;
  private String releaseDate;
  private double budget;
  private String currency;
  private String description;
  private List<Director> directors;
  private List<Actor> actors;

  //CONSTRUCTOR
  public Movie(String title, String releaseDate, double budget, String currency, String description, List<Actor> actors, List<Director> directors) {
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

  public String getReleaseDate() {
    return releaseDate;
  }

  public void setReleaseDate(String releaseDate) {
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

  public void setActors(List<Actor> actors) {
    this.actors = actors;
  }

  public List<Director> getDirectors() {
    return directors;
  }

  public void setDirectors(List<Director> directors) {
    this.directors = directors;
  }
}

//  public class Example {
//
//    public static void main(String[] args) {
//      String originalString =
//        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin euismod.";
//
//      StringBuilder stringBuilder = new StringBuilder();
//      int lineLength = 50;
//
//      // Loop para percorrer a string original e adicionar \n a cada 50 caracteres
//      for (int i = 0; i < originalString.length(); i++) {
//        stringBuilder.append(originalString.charAt(i));
//
//        // Adiciona \n a cada 50 caracteres
//        if ((i + 1) % lineLength == 0) {
//          stringBuilder.append("\n");
//        }
//      }
//
//      // Converte o StringBuilder de volta para uma String
//      String result = stringBuilder.toString();
//
//      // Imprime o resultado
//      System.out.println(result);
//    }
//  }
//  final int LINE_LENGTH = 80;
//  @Override
//  public String toString() {
//    String separator = "/".concat("-".repeat(LINE_LENGTH)).concat("\n");
//    String idLine =
//      "Id= " + id +
//      " ".repeat(LINE_LENGTH - (String.valueOf(id).length() + 13)) +
//      "★: " + String.format("%.1f", 3.974) + "/5" + "\n";
//
//    String titleLine =
//      "Title= " + formatString(title) +
//      " ".repeat(LINE_LENGTH - (title.length() + 8)) + "\n";
//
//    String releaseLine =
//      "Release Date= " + releaseDate +
//      " ".repeat(
//          LINE_LENGTH -
//          (
//            String.valueOf(releaseDate).length() +
//            String.valueOf(budget).length() +
//            32
//          )
//        ) +
//      "Budget ≈ R$ " + String.format("%.0f", budget) + "\n";
//
//    String synopsisLine = "Description:\n" + formatString(synopsis) + "\n";
//
//    String actorsLine = "Actor= " + listOfActors + "\n";
//
//    String genresLine =
//      "Genre= " + genres +
//      " ".repeat(
//          (
//            LINE_LENGTH -
//            (
//              String.valueOf(genres).length() +
//              String.valueOf(duration).length() +
//              language.length() +
//              29
//            )
//          ) /
//          2
//        ) +
//      "Duration= " + duration +
//      " min" +
//      " ".repeat(
//          (
//            LINE_LENGTH -
//            (
//              String.valueOf(genres).length() +
//              String.valueOf(duration).length() +
//              language.length() +
//              29
//            )
//          ) /
//          2
//        ) +
//      "Language= " + language + "\n";
//
//    String closingLine = "-".repeat(LINE_LENGTH).concat("/");
//
//    return (
//      separator +
//      idLine +
//      titleLine +
//      releaseLine +
//      synopsisLine +
//      actorsLine +
//      genresLine +
//      closingLine
//    );
//  }
//
//  private String formatString(String original) {
//    StringBuilder formatted = new StringBuilder();
//    int count = 0;
//
//    for (char currentChar : original.toCharArray()) {
//      formatted.append(currentChar);
//      count++;
//
//      if (count % LINE_LENGTH == 0) {
//        formatted.append("\n");
//      }
//    }
//    return formatted.toString();
//  }
//}
