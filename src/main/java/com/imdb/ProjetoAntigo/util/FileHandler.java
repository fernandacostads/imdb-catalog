//package com.imdb.util;
//
//import com.imdb.dto.MovieDTO;
//import com.imdb.model.Actor;
//import com.imdb.model.Director;
//import com.imdb.model.Movie;
//
//import java.io.*;
//import java.util.ArrayList;
//import java.util.List;
//
//public class FileHandler {
//
//  private static final String SEPARATOR = System.lineSeparator();
//
//  public static List<MovieDTO> loadMoviesFromFile(String filePath) {
//    List<MovieDTO> movies = new ArrayList<>();
//    File file = new File(filePath);
//
//    if (!file.exists()) {
//      System.out.println("File not found: " + filePath);
//      return movies;
//    }
//
//    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
//      String line;
//      while ((line = reader.readLine()) != null) {
//        try {
//          MovieDTO movie = parseMovieFromCsvLine(line);
//          if (movie != null) {
//            movies.add(movie);
//          }
//        } catch (Exception e) {
//          System.out.println("Error parsing line: " + line);
//          e.printStackTrace();
//        }
//      }
//    } catch (IOException e) {
//      System.err.println("Error reading movies from file: " + filePath);
//      e.printStackTrace();
//    }
//    return movies;
//  }
//
//  public static void saveMoviesToFile(List<MovieDTO> movies, String filePath) {
//    try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
//      for (MovieDTO movie : movies) {
//        String movieLine = convertMovieToCsvLine(movie);
//        writer.write(movieLine);
//        writer.newLine();
//      }
//    } catch (IOException e) {
//      System.err.println("Error saving movies to file: " + filePath);
//      e.printStackTrace();
//    }
//  }
//
//  private static MovieDTO parseMovieFromCsvLine(String line) {
//    String[] data = line.split(",");
//    if (data.length < 6) {
//      System.out.println("Invalid movie data: " + line);
//      return null;
//    }
//
//    int id = Integer.parseInt(data[0].trim());
//    String title = data[1].trim();
//    int releaseDate = Integer.parseInt(data[2].trim());
//    double budget = Double.parseDouble(data[3].trim());
//    String currency = data[4].trim();
//    String description = data[5].trim();
//
//    List<ActorDTO> actors = new ArrayList<>();
//    List<DirectorDTO> directors = new ArrayList<>();
//
//    return new MovieDTO(id, title, releaseDate, budget, currency, description, actors, directors);
//  }
//
//  private static String convertMovieToCsvLine(MovieDTO movie) {
//    return String.format("%d,%s,%d,%.2f,%s,%s",
//            movie.id(), movie.title(), movie.releaseDate(), movie.budget(), movie.currency(), movie.description());
//  }
//
//  public static void updateFile(List<Movie> moviesList, String FILE_NAME) {
//    try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, false))) {
//      for (Movie movie : moviesList) {
//        String movieInfo = createMovieInfo(movie);
//        writer.write(movieInfo);
//        writer.write(SEPARATOR);
//      }
//    } catch (IOException e) {
//      System.err.println("Error updating file: " + e.getMessage());
//    }
//  }
//
//  private static String createMovieInfo(Movie movie) {
//    return String.format("%d,%s,%d,%.2f,%s,%s",
//            movie.getId(), movie.getTitle(), movie.getReleaseDate(),
//            movie.getBudget(), movie.getCurrency(), movie.getDescription());
//  }
//
//  private static void updateActorsFile(List<Actor> actors, BufferedWriter writer) throws IOException {
//    for (Actor actor : actors) {
//      String actorInfo = String.format("%d,%s,%s", actor.getId(), actor.getName(), actor.getNationality());
//      writer.write(actorInfo);
//      writer.write(SEPARATOR);
//    }
//  }
//
//  private static void updateDirectorsFile(List<Director> directors, BufferedWriter writer) throws IOException {
//    for (Director director : directors) {
//      String directorInfo = String.format("%d,%s,%s", director.getId(), director.getName(), director.getNationality());
//      writer.write(directorInfo);
//      writer.write(SEPARATOR);
//    }
//  }
//
//  /*private static String createMovieInfo(Movie movie) {
//    return "Id: " + movie.getId() + SEPARATOR +
//           "Title: " + movie.getTitle() + SEPARATOR +
//           "Release Date: " + movie.getReleaseDate() + SEPARATOR +
//           "Budget: " + movie.getBudget() + SEPARATOR +
//           "Currency: " + movie.getCurrency() + SEPARATOR +
//           "Description: " + movie.getDescription() + SEPARATOR;
//  }
//
//  private static void updateActorsFile(List<Actor> actorsList, FileWriter writer) throws IOException {
//    writer.write("Actors: " + SEPARATOR);
//    for (Actor actor : actorsList) {
//      writer.write("Id: " + actor.getId() +
//                   ", Name: " + actor.getName() +
//                   ", Nationality: " + actor.getNationality() + SEPARATOR);
//    }
//  }
//
//  private static void updateDirectorsFile(List<Director> directorsList, FileWriter writer) throws IOException {
//    writer.write("Directors: " + SEPARATOR);
//    for (Director director : directorsList) {
//      writer.write("Id: " + director.getId() +
//                   ", Name: " + director.getName() +
//                   ", Nationality: " + director.getNationality() + SEPARATOR);
//    }
//  }
//
//  public static void updateFileA(List<Actor> actorsList, String FILE_NAME) {
//
//    try (FileWriter writer = new FileWriter(FILE_NAME, false)) {
//      createActorsInfo(actorsList, writer);
//    } catch (Exception e) {
//      System.err.println("Error updating file: " + e.getMessage());
//    }
//  }
//
//  public static void updateFileD(List<Director> directorsList, String FILE_NAME
//  ) {
//    try (FileWriter writer = new FileWriter(FILE_NAME, false)) {
//      createDirectorsInfo(directorsList, writer);
//    } catch (Exception e) {
//      System.err.println("Error updating file: " + e.getMessage());
//    }
//  }
//
//  private static void createActorsInfo(List<Actor> actorsList, FileWriter writer) throws IOException {
//    StringBuilder sb = new StringBuilder();
//    sb.append("Actors: ").append(SEPARATOR);
//    for (Actor actor : actorsList) {
//      sb.append("Id: ").append(actor.getId())
//              .append(", Name: ").append(actor.getName())
//              .append(", Nationality: ").append(actor.getNationality())
//              .append(SEPARATOR);
//    }
//    writer.write(sb.toString());
//  }
//
//  private static void createDirectorsInfo(List<Director> directorsList, FileWriter writer) throws IOException {
//    StringBuilder sb = new StringBuilder();
//    sb.append("Directors: ").append(SEPARATOR);
//    for (Director director : directorsList) {
//      sb.append("Id: ").append(director.getId())
//              .append(", Name: ").append(director.getName())
//              .append(", Nationality: ").append(director.getNationality())
//              .append(SEPARATOR);
//    }
//    writer.write(sb.toString());
//  }
//
//  public static List<Movie> loadMoviesFromFile(String fileName) {
//    List<Movie> movies = new ArrayList<>();
//
//    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
//      String line;
//
//      while ((line = br.readLine()) != null) {
//        if (line.startsWith("Id:")) {
//          int id = Integer.parseInt(line.split(":")[1].trim());
//          Movie movie = createMovieFromLines(br);
//          movie.setId(id);
//          movies.add(movie);
//        }
//      }
//    } catch (FileNotFoundException e) {
//      System.err.println("File not found: " + fileName);
//    } catch (IOException e) {
//      System.err.println("Error reading file: " + e.getMessage());
//    }
//
//    System.out.println("All movies loaded.");
//    return movies;
//  }
//
//  private static Movie createMovieFromLines(BufferedReader reader) throws IOException {
//    int id = Integer.parseInt(getValue(reader.readLine()));
//    String title = getValue(reader.readLine());
//    int year = Integer.parseInt(getValue(reader.readLine()));
//    double rating = Double.parseDouble(getValue(reader.readLine()));
//    String genre = getValue(reader.readLine());
//    String director = getValue(reader.readLine());
//    List<Actor> actors = readActors(reader);
//    List<Director> directors = readDirectors(reader);
//
//    return new Movie(id, title, year, rating, genre, director, actors, directors);
//  }
//
//  private static String getValue(String line) {
//    String[] parts = line.split(": ");
//    return parts.length > 1 ? parts[1].trim() : "";
//  }
//
//  private static List<Actor> readActors(BufferedReader br) throws IOException {
//    List<Actor> actorsList = new ArrayList<>();
//    String line;
//
//    while ((line = br.readLine()) != null && line.startsWith("Id:")) {
//
//      String[] tokens = line.split(", ");
//      int id = Integer.parseInt(tokens[0].split(": ")[1]);
//      String name = tokens[1].split(": ")[1];
//      String nationality = tokens[2].split(": ")[1];
//      Actor actor = new Actor(id, name, nationality);
//      actor.setId(id);
//      actorsList.add(actor);
//    }
//
//    return actorsList;
//  }
//
//  private static List<Director> readDirectors(BufferedReader reader) throws IOException {
//    List<Director> directorsList = new ArrayList<>();
//    String line;
//
//    while ((line = reader.readLine()) != null && line.startsWith("Id:")) {
//      String[] tokens = line.split(", ");
//      int id = Integer.parseInt(tokens[0].split(": ")[1]);
//      String name = tokens[1].split(": ")[1];
//      String nationality = tokens[2].split(": ")[1];
//      Director director = new Director(id, name, nationality);
//      director.setId(id);
//      directorsList.add(director);
//    }
//
//    return directorsList;
//  }
//
//  public static List<Actor> loadActorsFromFile(String fileName) {
//    List<Actor> actorsList = new ArrayList<>();
//    String line;
//
//    try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
//      while ((line = br.readLine()) != null && line.startsWith("Id:")) {
//        String[] tokens = line.split(", ");
//        Actor actor = new Actor(
//                Integer.parseInt(tokens[0].split(": ")[1]),
//                tokens[1].split(": ")[1],
//                tokens[2].split(": ")[1]
//        );
//
//        actorsList.add(actor);
//      }
//    } catch (FileNotFoundException e) {
//      System.err.println("File not found: " + fileName);
//    } catch (IOException e) {
//      System.err.println("Error reading file: " + e.getMessage());
//    }
//
//    return actorsList;
//  }
//
//  public static List<Director> loadDirectorsFromFile(String fileName) {
//    List<Director> directorsList = new ArrayList<>();
//    String line;
//
//    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
//      while ((line = reader.readLine()) != null && line.startsWith("Id:")) {
//        String[] tokens = line.split(", ");
//        Director director = new Director(
//                Integer.parseInt(tokens[0].split(": ")[1]),
//                tokens[1].split(": ")[1],
//                tokens[2].split(": ")[1]
//        );
//        directorsList.add(director);
//      }
//    } catch (FileNotFoundException e) {
//      System.err.println("File not found: " + fileName);
//    } catch (IOException e) {
//      System.err.println("Error reading file: " + e.getMessage());
//    }
//
//    return directorsList;
//  }*/
//}
//
