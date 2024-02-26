package com.imdb.util.fileHandler;

import com.imdb.DTO.ActorDTO;
import com.imdb.DTO.DirectorDTO;
import com.imdb.DTO.MovieDTO;
import com.imdb.controller.ActorController;
import com.imdb.controller.DirectorController;
import com.imdb.repository.IActorRepository;
import com.imdb.repository.IDirectorRepository;
import com.imdb.repository.IMovieRepository;
import com.imdb.repository.impl.ActorRepositoryImpl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MovieReader {
  private static String filePath;

  private final IMovieRepository movieRepository;
  private final IActorRepository actorRepository;
  private final IDirectorRepository directorRepository;

  public MovieReader(IMovieRepository movieRepository, IActorRepository actorRepository, IDirectorRepository directorRepository) {
    this.movieRepository = movieRepository;
    this.actorRepository = actorRepository;
    this.directorRepository = directorRepository;
  }

  public void readMoviesFromFile(String filePath) {

    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
      String line;

      while ((line = br.readLine()) != null) {
        if (line.startsWith("Id:")) {
          // Read movie information
          int id = Integer.parseInt(line.split(":")[1].trim());
          String title = br.readLine().split(":")[1].trim();
          int releaseDate = Integer.parseInt(br.readLine().split(":")[1].trim());
          double budget = Double.parseDouble(br.readLine().split(":")[1].trim());
          String currency = br.readLine().split(":")[1].trim();
          String description = br.readLine().split(":")[1].trim();
          List<ActorDTO> actors = processActors(br);
          List<DirectorDTO> directors = processDirectors(br);

          MovieDTO movie = new MovieDTO(id, title, releaseDate, budget, currency, description, actors, directors);
          movieRepository.create(movie);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

    /**
   * Processes actors from the BufferedReader and adds them to the Movie object.
   *
   * @param br    The BufferedReader for reading lines from the file.
   * @throws IOException If an I/O error occurs.
   */
  private List<ActorDTO> processActors(BufferedReader br) throws IOException {
    List<ActorDTO> actors = new ArrayList<>();

    String line;
    // Read and process "Actors:" section
    while (!(line = br.readLine()).equals("Directors:") && line.length() > 0) {
      if (line.startsWith("    Id:")) {
        String[] actorParts = line.split(",");
        int actorId = Integer.parseInt(actorParts[0].split(":")[1].trim());
        String actorName = actorParts[1].split(":")[1].trim();
        String actorNationality = actorParts[2].split(":")[1].trim();
        LocalDate birthDate = LocalDate.parse(actorParts[3].split(":")[1].trim());

        ActorDTO actor = new ActorDTO(actorId, actorName, actorNationality, birthDate);
        actorRepository.create(actor);
        actors.add(actor);
      }
    }
    return actors;
  }

  /**
   * Processes directors from the BufferedReader and adds them to the Movie object.
   *
   * @param br    The BufferedReader for reading lines from the file.
   * @throws IOException If an I/O error occurs.
   */
  private List<DirectorDTO> processDirectors(BufferedReader br) throws IOException {
    List<DirectorDTO> directors = new ArrayList<>();
    String line;
    // Read and process "Directors:" section
    while ((line = br.readLine()) != null && !line.isEmpty()) {
      String[] directorsParts = line.split(",");
      if (line.startsWith("    Id:") && directorsParts.length >= 4) {
        int directorId = Integer.parseInt(directorsParts[0].split(":")[1].trim());
        String directorName = directorsParts[1].split(":")[1].trim();
        String directorNationality = directorsParts[2].split(":")[1].trim();
        LocalDate birthDate = LocalDate.parse(directorsParts[3].split(":")[1].trim());

        DirectorDTO director = new DirectorDTO(directorId, directorName, directorNationality, birthDate);
        directorRepository.create(director);
        directors.add(director);
      }
    }
    return directors;
  }


//
//  /**
//   * Reads movies from a file and returns a list of Movie objects.
//   *
//   * @return A list of Movie objects.
//   */
//  public static List<Movie> readMoviesFromFile() {
//    List<Movie> movies = new ArrayList<>();
//
//    try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//      String line;
//
//      while ((line = br.readLine()) != null) {
//        if (line.startsWith("Id:")) {
//          Movie movie = readMovieInfo(br);
//          movies.add(movie);
//        }
//      }
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//
//    return movies;
//  }
//
//  /**
//   * Reads movie information from the BufferedReader and constructs a Movie object.
//   *
//   * @param br The BufferedReader for reading lines from the file.
//   * @return A Movie object with parsed information.
//   * @throws IOException If an I/O error occurs.
//   */
//  private static Movie readMovieInfo(BufferedReader br) throws IOException {
//    int id = Integer.parseInt(extractValue(br.readLine()));
//    String title = extractValue(br.readLine());
//    int releaseDate = Integer.parseInt(extractValue(br.readLine()));
//    double budget = Double.parseDouble(extractValue(br.readLine()));
//    String currency = extractValue(br.readLine());
//    String description = extractValue(br.readLine());
//
//    Movie movie = new Movie(id, title, releaseDate, budget, currency, description);
//    processActors(br, movie);
//    processDirectors(br, movie);
//
//    return movie;
//  }
//
//  /**
//   * Processes actors from the BufferedReader and adds them to the Movie object.
//   *
//   * @param br    The BufferedReader for reading lines from the file.
//   * @param movie The Movie object to which actors will be added.
//   * @throws IOException If an I/O error occurs.
//   */
//  private static void processActors(BufferedReader br, Movie movie) throws IOException {
//    String line;
//    while (!(line = br.readLine()).equals("Directors:") && line.length() > 0) {
//      if (line.startsWith("    Id:")) {
//        String[] actorParts = line.split(",");
//        int actorId = Integer.parseInt(extractValue(actorParts[0]));
//        String actorName = extractValue(actorParts[1]);
//        String actorNationality = extractValue(actorParts[2]);
//        LocalDate birthDate = LocalDate.parse(extractValue(actorParts[3]));
//
//        Actor actor = new Actor(actorId, actorName, actorNationality, birthDate);
//        movie.addActor(actor);
//      }
//    }
//  }
//
//  /**
//   * Processes directors from the BufferedReader and adds them to the Movie object.
//   *
//   * @param br    The BufferedReader for reading lines from the file.
//   * @param movie The Movie object to which directors will be added.
//   * @throws IOException If an I/O error occurs.
//   */
//  private static void processDirectors(BufferedReader br, Movie movie) throws IOException {
//    String line;
//    while ((line = br.readLine()) != null && !line.isEmpty()) {
//      String[] directorsParts = line.split(",");
//      if (line.startsWith("    Id:") && directorsParts.length >= 4) {
//        int directorId = Integer.parseInt(extractValue(directorsParts[0]));
//        String directorName = extractValue(directorsParts[1]);
//        String directorNationality = extractValue(directorsParts[2]);
//        LocalDate birthDate = LocalDate.parse(extractValue(directorsParts[3]));
//
//        Director director = new Director(directorId, directorName, directorNationality, birthDate);
//        movie.addDirector(director);
//      }
//    }
//  }
//
//  /**
//   * Extracts the value part from a line (e.g., "Id: 123" -> "123").
//   *
//   * @param line The input line.
//   * @return The extracted value.
//   */
//  private static String extractValue(String line) {
//    return line.split(":")[1].trim();
//  }
}
