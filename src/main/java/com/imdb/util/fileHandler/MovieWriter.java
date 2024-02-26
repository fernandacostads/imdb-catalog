package com.imdb.util.fileHandler;

import com.imdb.DTO.ActorDTO;
import com.imdb.DTO.DirectorDTO;
import com.imdb.DTO.MovieDTO;
import com.imdb.repository.IActorRepository;
import com.imdb.repository.IDirectorRepository;
import com.imdb.repository.IMovieRepository;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class MovieWriter {

  private final IMovieRepository movieRepository;
  private final IActorRepository actorRepository;
  private final IDirectorRepository directorRepository;

  public MovieWriter(
    IMovieRepository movieRepository,
    IActorRepository actorRepository,
    IDirectorRepository directorRepository
  ) {
    this.movieRepository = movieRepository;
    this.actorRepository = actorRepository;
    this.directorRepository = directorRepository;
  }

  public void updateMoviesToFile(String filePath) {
    List<MovieDTO> movies = movieRepository.read();

    try (
      BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, false))
    ) {
      for (MovieDTO movie : movies) {
        bw.write("Id:" + movie.id());
        bw.newLine();
        bw.write("Title:" + movie.title());
        bw.newLine();
        bw.write("Release Date:" + movie.releaseDate());
        bw.newLine();
        bw.write("Budget:" + movie.budget());
        bw.newLine();
        bw.write("Currency:" + movie.currency());
        bw.newLine();
        bw.write("Description:" + movie.description());
        bw.newLine();

        writeActors(bw, movie.actors());
        writeDirectors(bw, movie.directors());

        // Separador entre filmes (opcional)
        bw.newLine();
      }
    } catch (IOException e) {
      System.err.println("File update failed");
    }
  }

  private void writeActors(BufferedWriter bw, List<ActorDTO> actors)
    throws IOException {
    bw.write("Actors:");
    bw.newLine();

    for (ActorDTO actor : actors) {
      bw.write(
        "    Id:" +
        actor.id() +
        ",Name:" +
        actor.name() +
        ",Nationality:" +
        actor.nationality() +
        ",BirthDate:" +
        actor.birthDate()
      );
      bw.newLine();
    }
  }

  private void writeDirectors(BufferedWriter bw, List<DirectorDTO> directors)
    throws IOException {
    bw.write("Directors:");
    bw.newLine();

    for (DirectorDTO director : directors) {
      bw.write(
        "    Id:" +
        director.id() +
        ",Name:" +
        director.name() +
        ",Nationality:" +
        director.nationality() +
        ",BirthDate:" +
        director.birthDate()
      );
      bw.newLine();
    }
  }

  public void updateActorsToFile(String filePath) throws IOException {
    try (
      BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, false))
    ) {
      List<ActorDTO> actors = actorRepository.read();

      bw.write("Actors:");
      bw.newLine();
      for (ActorDTO actor : actors) {
        bw.write(
          "    Id:" +
          actor.id() +
          ",Name:" +
          actor.name() +
          ",Nationality:" +
          actor.nationality() +
          ",BirthDate:" +
          actor.birthDate()
        );
        bw.newLine();

        bw.write("    Movies:");
        bw.newLine();
        for (MovieDTO movie : actor.movies()) {
          bw.write(
            "        Id: " +
            movie.id() +
            ", Title: " +
            movie.title() +
            ", Release Date: " +
            movie.releaseDate()
          );
          bw.newLine();
        }

        bw.newLine(); // Adiciona uma linha em branco para separar atores
      }
    }
  }

  public void updateDirectorsToFile(String filePath) throws IOException {
    try (
      BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, false))
    ) {
      List<DirectorDTO> directors = directorRepository.read();

      bw.write("Directors:");
      bw.newLine();
      for (DirectorDTO director : directors) {
        bw.write(
          "    Id:" +
          director.id() +
          ",Name:" +
          director.name() +
          ",Nationality:" +
          director.nationality() +
          ",BirthDate:" +
          director.birthDate()
        );
        bw.newLine();

        bw.write("    Movies:");
        bw.newLine();
        for (MovieDTO movie : director.movies()) {
          bw.write(
            "        Id: " +
            movie.id() +
            ", Title: " +
            movie.title() +
            ", Release Date: " +
            movie.releaseDate()
          );
          bw.newLine();
        }

        bw.newLine(); // Adiciona uma linha em branco para separar diretores
      }
    }
  }
}
