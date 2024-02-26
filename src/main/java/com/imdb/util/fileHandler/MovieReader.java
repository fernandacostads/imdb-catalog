package com.imdb.util.fileHandler;

import com.imdb.DTO.ActorDTO;
import com.imdb.DTO.DirectorDTO;
import com.imdb.DTO.MovieDTO;
import com.imdb.repository.IActorRepository;
import com.imdb.repository.IDirectorRepository;
import com.imdb.repository.IMovieRepository;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MovieReader {

  private final IMovieRepository movieRepository;
  private final IActorRepository actorRepository;
  private final IDirectorRepository directorRepository;

  public MovieReader(
    IMovieRepository movieRepository,
    IActorRepository actorRepository,
    IDirectorRepository directorRepository
  ) {
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
          int releaseDate = Integer.parseInt(
            br.readLine().split(":")[1].trim()
          );
          double budget = Double.parseDouble(
            br.readLine().split(":")[1].trim()
          );
          String currency = br.readLine().split(":")[1].trim();
          String description = br.readLine().split(":")[1].trim();
          List<ActorDTO> actors = processActors(br);
          List<DirectorDTO> directors = processDirectors(br);

          MovieDTO movie = new MovieDTO(
            id,
            title,
            releaseDate,
            budget,
            currency,
            description,
            actors,
            directors
          );
          movieRepository.create(movie);
        }
      }
    } catch (IOException e) {
      System.err.println("File loading failed");
    }
  }

  /**
   * Processes actors from the BufferedReader and adds them to the Movie object.
   *
   * @param br The BufferedReader for reading lines from the file.
   * @throws IOException If an I/O error occurs.
   */
  private List<ActorDTO> processActors(BufferedReader br) throws IOException {
    List<ActorDTO> actors = new ArrayList<>();

    String line;
    // Read and process "Actors:" section
    while (!(line = br.readLine()).equals("Directors:") && !line.isEmpty()) {
      if (line.startsWith("    Id:")) {
        String[] actorParts = line.split(",");
        int actorId = Integer.parseInt(actorParts[0].split(":")[1].trim());
        String actorName = actorParts[1].split(":")[1].trim();
        String actorNationality = actorParts[2].split(":")[1].trim();
        LocalDate birthDate = LocalDate.parse(
          actorParts[3].split(":")[1].trim()
        );

        actors.add(
          actorRepository.create(
            new ActorDTO(
              actorId,
              actorName,
              actorNationality,
              birthDate,
              List.of()
            )
          )
        );
      }
    }
    return actors;
  }

  /**
   * Processes directors from the BufferedReader and adds them to the Movie object.
   *
   * @param br The BufferedReader for reading lines from the file.
   * @throws IOException If an I/O error occurs.
   */
  private List<DirectorDTO> processDirectors(BufferedReader br)
    throws IOException {
    List<DirectorDTO> directors = new ArrayList<>();
    String line;
    // Read and process "Directors:" section
    while ((line = br.readLine()) != null && !line.isEmpty()) {
      String[] directorsParts = line.split(",");
      if (line.startsWith("    Id:") && directorsParts.length >= 4) {
        int directorId = Integer.parseInt(
          directorsParts[0].split(":")[1].trim()
        );
        String directorName = directorsParts[1].split(":")[1].trim();
        String directorNationality = directorsParts[2].split(":")[1].trim();
        LocalDate birthDate = LocalDate.parse(
          directorsParts[3].split(":")[1].trim()
        );

        directors.add(
          directorRepository.create(
            new DirectorDTO(
              directorId,
              directorName,
              directorNationality,
              birthDate,
              List.of()
            )
          )
        );
      }
    }
    return directors;
  }
}
