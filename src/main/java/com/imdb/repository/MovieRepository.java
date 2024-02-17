package com.imdb.repository;

import com.imdb.model.Movie;
import com.imdb.model.Actor;
import com.imdb.model.Director;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MovieRepository {
  private static final String FILE_PATH = "resources/movies.txt";

  public List<Movie> getAllMovies() {
    List<Movie> movies = new ArrayList<>();
    try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
      String line;
      while ((line = br.readLine()) != null) {
        String[] parts = line.split(",");
        int id = Integer.parseInt(parts[0]);
        String title = parts[1];
        int releaseDate = Integer.parseInt(parts[2]);
        double budget = Double.parseDouble(parts[3]);
        String currency = parts[4];
        String description = parts[5];
        List<Actor> actors = parseActors(parts[6]);
        List<Director> directors = parseDirectors(parts[7]);
        movies.add(new Movie(id, title, releaseDate, budget, currency, description, actors, directors));
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return movies;
  }

  public Movie getMovieById(int id) {
    List<Movie> movies = getAllMovies();
    for (Movie movie : movies) {
      if (movie.getId() == id) {
        return movie;
      }
    }
    return null;
  }

  public void addMovie(Movie movie) {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
      bw.write(movieToCsvString(movie));
      bw.newLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void deleteMovie(int id) {
    List<Movie> movies = getAllMovies();
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
      for (Movie movie : movies) {
        if (movie.getId() != id) {
          bw.write(movieToCsvString(movie));
          bw.newLine();
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private List<Actor> parseActors(String actorsString) {
    List<Actor> actors = new ArrayList<>();
    String[] actorInfo = actorsString.split(";");
    for (String info : actorInfo) {
      String[] parts = info.split(",");
      int id = Integer.parseInt(parts[0]);
      String name = parts[1];
      String nationality = parts[2];
      actors.add(new Actor(name, nationality));
    }
    return actors;
  }

  private List<Director> parseDirectors(String directorsString) {
    List<Director> directors = new ArrayList<>();
    String[] directorInfo = directorsString.split(";");
    for (String info : directorInfo) {
      String[] parts = info.split(",");
      int id = Integer.parseInt(parts[0]);
      String name = parts[1];
      String nationality = parts[2];
      directors.add(new Director(name, nationality));
    }
    return directors;
  }

  private String movieToCsvString(Movie movie) {
    StringBuilder sb = new StringBuilder();
    sb.append(movie.getId()).append(",");
    sb.append(movie.getTitle()).append(",");
    sb.append(movie.getReleaseDate()).append(",");
    sb.append(movie.getBudget()).append(",");
    sb.append(movie.getCurrency()).append(",");
    sb.append(movie.getDescription()).append(",");
    sb.append(actorsToCsvString(movie.getActors())).append(",");
    sb.append(directorsToCsvString(movie.getDirectors()));
    return sb.toString();
  }

  private String actorsToCsvString(List<Actor> actors) {
    StringBuilder sb = new StringBuilder();
    for (Actor actor : actors) {
      sb.append(actor.getId()).append(",");
      sb.append(actor.getName()).append(",");
      sb.append(actor.getNationality()).append(";");
    }
    return sb.toString();
  }

  private String directorsToCsvString(List<Director> directors) {
    StringBuilder sb = new StringBuilder();
    for (Director director : directors) {
      sb.append(director.getId()).append(",");
      sb.append(director.getName()).append(",");
      sb.append(director.getNationality()).append(";");
    }
    return sb.toString();
  }
}


  /*public static Optional<List<Movie>> searchMovieByGenre(String genre) {
    List<Movie> collect = movieMap
      .values()
      .stream()
      .filter(movie ->
        movie
          .getGenres()
          .stream()
          .map(Enum::name)
          .anyMatch(genre::equalsIgnoreCase)
      ) // Verifica se há algum gênero que corresponde à entrada, ignorando maiúsculas e minúsculas
      .collect(Collectors.toList());
    return Optional.ofNullable(collect.isEmpty() ? null : collect);
  }*/
}
