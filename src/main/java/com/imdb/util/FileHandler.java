package com.imdb.util;

import com.imdb.model.Actor;
import com.imdb.model.Director;
import com.imdb.model.Movie;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages file operations for the application, facilitating the persistence of entity data
 * including movies, actors, and directors. Offers methods to serialize and deserialize entity data.
 */

public class FileHandler {

  private static final String SEPARATOR = System.lineSeparator();

  /**
   * Reads from a specified file and constructs a list of Movie objects based on the data.
   * Assumes a predefined format for the file's content.
   *
   * @param fileName The path to the file containing serialized movie data.
   * @return A list of Movie objects deserialized from the file.
   */

  public static List<Movie> readMoviesFromFile(String fileName) {
    List<Movie> movies = new ArrayList<>(10);

    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line;

      while ((line = br.readLine()) != null) {
        if (line.startsWith("Id:")) {
          int id = Integer.parseInt(line.split(":")[1].trim());
          Movie movie = createBufferedMovies(br, id);

          movies.add(movie);
        }
      }
    } catch (FileNotFoundException e) {
      System.err.println("File not found: " + fileName);
    } catch (IOException e) {
      System.err.println("Error reading file: " + e.getMessage());
    }

    System.out.println("All movies loaded.");
    return movies;
  }

  /**
   * Auxiliary method to construct a Movie object from consecutive lines in a file,
   * starting from a line indicating a new movie entity.
   *
   * @param reader The BufferedReader instance reading the file.
   * @param id     The id of the movie extracted from the file.
   * @return A Movie object constructed from the read data.
   * @throws IOException If an error occurs during file reading.
   */

  private static Movie createBufferedMovies(BufferedReader reader, int id) throws IOException {
    String title = getValue(reader.readLine());
    int year = Integer.parseInt(getValue(reader.readLine()));
    double rating = Double.parseDouble(getValue(reader.readLine()));
    String genre = getValue(reader.readLine());
    String director = getValue(reader.readLine());
    List<Actor> actors = readActors(reader);
    List<Director> directors = readDirectors(reader);

    return new Movie(id, title, year, rating, genre, director, actors, directors);
  }



  /**
   * Reads actor information from a specified file and constructs a list of Actor
   * entities. Assumes that the actor data in the file is formatted according to a
   * predefined structure.
   *
   * @param br The BufferedReader used to read the file.
   * @return A list of Actor entities constructed from the file data.
   * @throws IOException If an error occurs during file reading.
   */

  private static List<Actor> readActors(BufferedReader br) throws IOException {
    List<Actor> actorsList = new ArrayList<>();
    String line;

    while ((line = br.readLine()) != null && line.startsWith("Id:")) {

      String[] tokens = line.split(", ");
      int id = Integer.parseInt(tokens[0].split(": ")[1]);
      String name = tokens[1].split(": ")[1];
      String nationality = tokens[2].split(": ")[1];
      Actor actor = new Actor(id, name, nationality, null);
      actor.setId(id);
      actorsList.add(actor);
    }

    return actorsList;
}
  /**
   * Reads director data from subsequent lines in a file, constructing Director entities until
   * a new section in the file is encountered.
   *
   * @param reader The BufferedReader instance reading the file.
   * @return A list of Director entities constructed from the file data.
   * @throws IOException If an error occurs during file reading.
   */

  private static List<Director> readDirectors(BufferedReader reader) throws IOException {
    List<Director> directorsList = new ArrayList<>();
    String line;

    while ((line = reader.readLine()) != null && line.startsWith("Id:")) {
      String[] tokens = line.split(", ");
      int id = Integer.parseInt(tokens[0].split(": ")[1]);
      String name = tokens[1].split(": ")[1];
      String nationality = tokens[2].split(": ")[1];
      Director director = new Director(id, name, nationality, null);
      director.setId(id);
      directorsList.add(director);
    }

    return directorsList;
  }

  /**
   * Reads actor information from a specified file and constructs a list of Actor
   * entities. Assumes that the actor data in the file is formatted according to a
   * predefined structure.
   *
   * @param fileName The path to the file containing actor data.
   * @return A list of Actor entities constructed from the file data.
   */

  public static List<Actor> readActorsFromFile(String fileName) {
    List<Actor> actorsList = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] parts = line.split(", ");
        if (parts.length == 3) {
          int id = Integer.parseInt(parts[0].split(": ")[1].trim());
          String name = parts[1].split(": ")[1].trim();
          String nationality = parts[2].split(": ")[1].trim();
          Actor actor = new Actor(id, name, nationality, null);//*/arrumar
          actorsList.add(actor);
        }
      }
    } catch (IOException e) {
      System.err.println("Error reading file: " + e.getMessage());
    }
    return actorsList;
  }


  /**
   * Formats movie information into a string for file writing, including metadata and
   * details about associated actors and directors.
   *
   * @param movie The movie entity to be formatted.
   * @return A string representation of the movie's details.
   */

  private static String createMovieInfo(Movie movie) {
    return "Id: " + movie.getId() + SEPARATOR +
           "Title: " + movie.getTitle() + SEPARATOR +
           "Release Date: " + movie.getReleaseDate() + SEPARATOR +
           "Budget: " + movie.getBudget() + SEPARATOR +
           "Currency: " + movie.getCurrency() + SEPARATOR +
           "Descrição: " + movie.getDescription() + SEPARATOR;
  }

  /**
   * Serializes and writes detailed information about a list of Actor entities
   * into the specified FileWriter. This method structures actor data for file
   * persistence.
   *
   * @param actorsList The list of actors whose data is to be serialized.
   * @param writer     The FileWriter object used to write the data to a file.
   * @throws IOException If an I/O error occurs during writing.
   */

  private static void createActorsInfo(List<Actor> actorsList, FileWriter writer) throws IOException {
    StringBuilder sb = new StringBuilder();
    sb.append("Actors: ").append(SEPARATOR);
    for (Actor actor : actorsList) {
      sb.append("Id: ").append(actor.getId())
              .append(", Name: ").append(actor.getName())
              .append(", Nationality: ").append(actor.getNationality())
              .append(", Birthdate: ").append(actor.getBirthDate())
              .append(SEPARATOR);
    }
    writer.write(sb.toString());
  }

  /**
   * Serializes and writes detailed information about a list of Director entities
   * into the specified FileWriter. This method structures director data for file
   * persistence.
   *
   * @param directorsList The list of directors whose data is to be serialized.
   * @param writer        The FileWriter object used to write the data to a file.
   * @throws IOException If an I/O error occurs during writing.
   */

  private static void createDirectorsInfo(List<Director> directorsList, FileWriter writer) throws IOException {
    StringBuilder sb = new StringBuilder();
    sb.append("Directors: ").append(SEPARATOR);
    for (Director director : directorsList) {
      sb.append("Id: ").append(director.getId())
              .append(", Name: ").append(director.getName())
              .append(", Nationality: ").append(director.getNationality())
              .append(", Birthdate: ").append(director.getBirthDate())
              .append(SEPARATOR);
    }
    writer.write(sb.toString());
  }

  /**
   * Reads director information from a specified file and constructs a list of Director
   * entities. Assumes that the director data in the file is formatted according to a
   * predefined structure.
   *
   * @param fileName The path to the file containing director data.
   * @return A list of Director entities constructed from the file data.
   */

  public static List<Director> readDirectorsFromFile(String fileName) {
    List<Director> directorsList = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;
      while ((line = reader.readLine()) != null) {
        String[] parts = line.split(", ");
        if (parts.length == 3) {
          int id = Integer.parseInt(parts[0].split(": ")[1].trim());
          String name = parts[1].split(": ")[1].trim();
          String nationality = parts[2].split(": ")[1].trim();
          Director director = new Director(id, name, nationality, null);//*/arrumar
          directorsList.add(director);
        }
      }
    } catch (IOException e) {
      System.err.println("Error reading file: " + e.getMessage());
    }
    return directorsList;
  }

  /**
   * Serializes and writes a list of movies, along with associated actors and directors,
   * to a specified file. Each entity's data is formatted and written as plain text.
   *
   * @param moviesList The list of movies to be serialized and written.
   * @param FILE_NAME  The file path where the data is to be stored.
   */

  public static void updateFile(List<Movie> moviesList, String FILE_NAME) {
    try (FileWriter writer = new FileWriter(FILE_NAME, false)) {
      for (Movie movie : moviesList) {
        String movieInfo = createMovieInfo(movie);
        writer.write(movieInfo);

        updateActorsFile(movie.getActors(), writer);
        updateDirectorsFile(movie.getDirectors(), writer);

        writer.write(SEPARATOR);
      }
    } catch (Exception e) {
      System.err.println("Error updating file: " + e.getMessage());
    }
  }

  /**
   * Writes actor information for a movie to the file.
   *
   * @param actorsList The list of actors to be written.
   * @param writer     The FileWriter object used to write to the file.
   * @throws IOException If an I/O error occurs.
   */

  private static void updateActorsFile(List<Actor> actorsList, FileWriter writer) throws IOException {
    writer.write("Actors: " + SEPARATOR);
    for (Actor actor : actorsList) {
      writer.write("Id: " + actor.getId() +
                   ", Name: " + actor.getName() +
                   ", Nationality: " + actor.getNationality() + SEPARATOR);
    }
  }

  /**
   * Writes a list of Actor entities to a specified file. This method serializes
   * each actor's information into a structured format for persistence.
   *
   * @param actorsList The list of Actor entities to be serialized and written.
   * @param FILE_NAME  The path of the file where actor data will be saved.
   */

  public static void updateFileA(List<Actor> actorsList, String FILE_NAME) {

    try (FileWriter writer = new FileWriter(FILE_NAME, false)) {
      createActorsInfo(actorsList, writer);
    } catch (Exception e) {
      System.err.println("Error updating file: " + e.getMessage());
    }
  }

  /**
   * Writes director information for a movie to the file.
   *
   * @param directorsList The list of directors to be written.
   * @param writer        The FileWriter object used to write to the file.
   * @throws IOException If an I/O error occurs.
   */

  private static void updateDirectorsFile(List<Director> directorsList, FileWriter writer) throws IOException {
    writer.write("Directors: " + SEPARATOR);
    for (Director director : directorsList) {
      writer.write("Id: " + director.getId() +
                   ", Name: " + director.getName() +
                   ", Nationality: " + director.getNationality() + SEPARATOR);
    }
  }

  /**
   * Writes a list of Director entities to a specified file. This method serializes
   * each director's information into a structured format for persistence.
   *
   * @param directorsList The list of Director entities to be serialized and written.
   * @param FILE_NAME     The path of the file where director data will be saved.
   */

  public static void updateFileD(List<Director> directorsList, String FILE_NAME
  ) {
    try (FileWriter writer = new FileWriter(FILE_NAME, false)) {
      createDirectorsInfo(directorsList, writer);
    } catch (Exception e) {
      System.err.println("Error updating file: " + e.getMessage());
    }
  }

  /**
   * Parses a line to extract the value following a colon and whitespace, used for
   * extracting movie, actor, and director properties from movie file.
   *
   * @param line The line to parse.
   * @return The extracted value as a string.
   */

  private static String getValue(String line) {
    String[] parts = line.split(": ");
    return parts.length > 1 ? parts[1].trim() : "";
  }
}