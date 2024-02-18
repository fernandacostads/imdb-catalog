package com.imdb.resources;

import com.imdb.model.Actor;
import com.imdb.model.Director;
import com.imdb.model.Movie;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DataCollector {

  private static final String SEPARATOR = System.lineSeparator();

  public DataCollector() {}

  public static void updateFile(List<Movie> moviesList, String FILE_NAME)
    throws IOException {
    try (FileWriter writer = new FileWriter(FILE_NAME, false)) {
      for (Movie movie : moviesList) {
        String sb = "Id: " + movie.getId() + SEPARATOR +
                    "Title: " + movie.getTitle() + SEPARATOR +
                    "Release Date: " +
                    movie.getReleaseDate() +
                    SEPARATOR +
                    "Budget: " + movie.getBudget() + SEPARATOR +
                    "Currency: " + movie.getCurrency() + SEPARATOR;
        writer.write(sb);

        updateFileA(movie.getActors(), writer);
        updateFileD(movie.getDirectors(), writer);

        writer.write(SEPARATOR);
      }
    } catch (Exception e) {
      System.out.println("Erro to update File..");
    }
  }

  public static void updateFileA(List<Actor> actorsList, FileWriter writer)
    throws IOException {
    mainString(actorsList, writer);
  }

  public static void updateFileD(
    List<Director> directorsList,
    FileWriter writer
  ) throws IOException {
    MainString(directorsList, writer);
  }

  private static void mainString(List<Actor> actorsList, FileWriter writer) throws IOException {
    StringBuilder sb = new StringBuilder();
    try {
      sb.append("Actors: ").append(SEPARATOR);
      for (Actor actor : actorsList) {
        sb
                .append("Id: ")
                .append(actor.getId())
                .append(", ")
                .append("Name: ")
                .append(actor.getName())
                .append(", ")
                .append("Nationality: ")
                .append(actor.getNationality())
                .append(SEPARATOR);
      }
    } catch (NullPointerException e) {
      System.out.println("Null List");
    }
    writer.write(sb.toString());
  }

  private static void MainString(List<Director> directorsList, FileWriter writer) throws IOException {
    StringBuilder sb = new StringBuilder();
    try {
      sb.append("Directors: ").append(SEPARATOR);
      for (Director director : directorsList) {
        sb
          .append("Id: ")
          .append(director.getId())
          .append(", ")
          .append("Name: ")
          .append(director.getName())
          .append(", ")
          .append("Nationality: ")
          .append(director.getNationality())
          .append(SEPARATOR);
      }
    } catch (NullPointerException e) {
      System.out.println("Null List");
    }
    writer.write(sb.toString());
  }

  public static void updateFileA(List<Actor> actorsList, String FILE_NAME)
          throws IOException {

    try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
      mainString(actorsList, writer);
    }
  }

  public static void updateFileD(
          List<Director> directorsList,
          String FILE_NAME
  ) throws IOException {
    try (FileWriter writer = new FileWriter(FILE_NAME, true)) {
      MainString(directorsList, writer);
    }
  }

  public static List<Movie> loadFile(String fileName) throws IOException {
    List<Movie> movies = new ArrayList<>();

    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line;

      while ((line = br.readLine()) != null) {
        if (line.startsWith("Id:")) {
          int id = Integer.parseInt(line.split(":")[1].trim());
          String title = getValue(br.readLine());
          int releaseDate = Integer.parseInt(getValue(br.readLine()));
          double budget = Double.parseDouble(getValue(br.readLine()));
          String currency = getValue(br.readLine());
          String description = getValue(br.readLine());

          Movie movie = new Movie(
            title,
            releaseDate,
            budget,
            currency,
            description,
            readActors(br),
            readDirectors(br)
          );
          movie.setId(id);
          // Read actors

          movies.add(movie);
        }
      }
    } catch (IOException e) {
     e.printStackTrace();
    }

    return movies;
  }

  private static String getValue(String line) {
    String[] parts = line.split(": ");
    if (parts.length > 1) {
      return parts[1].trim();
    } else {
      return "0"; // ou return null; dependendo do comportamento desejado
    }
  }

  private static List<Director> readDirectors(BufferedReader br)
    throws IOException {
    List<Director> directors = new ArrayList<>();

    String line;
    Director currentDirector = null;

    while ((line = br.readLine()) != null) {
      if (line.startsWith("Directors:")) {
        currentDirector = new Director(null, null);
      } else if (line.startsWith("Id:") && (currentDirector != null)) {
        String[] parts = line.split(": ");
        currentDirector.setId(Integer.parseInt(parts[1].trim()));
      } else if (line.startsWith("Name:") && (currentDirector != null)) {
        String[] parts = line.split(": ");
        currentDirector.setName(parts[1].trim());
      } else if (line.startsWith("Nationality:") && currentDirector != null) {
        String[] parts = line.split(": ");
        currentDirector.setNationality(parts[1].trim());
      } else if (line.equals("") && currentDirector != null) {
        directors.add(currentDirector);
        currentDirector = null;
      }
    }
    return directors;
  }

  private static List<Actor> readActors(BufferedReader br) throws IOException {
    List<Actor> actors = new ArrayList<>();

    String line;
    Actor currentActor = null;

    while ((line = br.readLine()) != null) {
      if (line.startsWith("Actors:")) {
        currentActor = new Actor(null, null);
      } else if (line.startsWith("Id:") && currentActor != null) {
        String[] parts = line.split(": ");
        currentActor.setId(Integer.parseInt(parts[1].trim()));
      } else if (line.startsWith("Name:") && currentActor != null) {
        String[] parts = line.split(": ");
        currentActor.setName(parts[1].trim());
      } else if (line.startsWith("Nationality:") && currentActor != null) {
        String[] parts = line.split(": ");
        currentActor.setNationality(parts[1].trim());
      } else if (line.equals("") && currentActor != null) {
        actors.add(currentActor);
        currentActor = null;
      }
    }
    return actors;
  }
}
