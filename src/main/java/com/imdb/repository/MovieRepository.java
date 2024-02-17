package com.imdb.repository;

import com.imdb.model.Movie;
import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.HashMap;
import java.util.Map;

public class MovieRepository {

  private static final String FILE_NAME =
    "src/main/java/com/imdb/resources/movies.txt";
  private static final String SEPARATOR = System.lineSeparator();

  private static final Map<Integer, Movie> movieMap = new HashMap<>(); // Mapa para armazenar os filmes por ID
  private static int nextId = 1; // Contador para gerar IDs

  public static void addMovie(Movie movie) throws IOException {
    movie.setId(nextId++); // Atribua o próximo ID ao filme
    movieMap.put(movie.getId(), movie); // Adicione o filme ao mapa
    updateFile();
  }

  public static List<Movie> getAllMovies() {
    return new ArrayList<>(movieMap.values());
  }

  public static Movie getMovieById(int id) {
    return movieMap.get(id);
  }

  private static void updateFile() {
    try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
      for (Movie movie : movieMap.values()) {
        writer.println("Id: " + movie.getId());
        writer.println("Title: " + movie.getTitle());
        writer.println("Release Date: " + movie.getReleaseDate());
        writer.println("Budget: " + movie.getBudget());
        writer.println("Currency: " + movie.getCurrency());
        writer.println("Description: " + movie.getDescription());
        writer.println(SEPARATOR);
      }
    } catch (IOException e) {
      System.err.println("Error writing to file: " + e.getMessage());
    }
  }

  /*for (Actor actor : movie.getActors()) {
         writer.write("Nome: " + actor.getName() + SEPARATOR);
      }

     for (Director director : movie.getDirectors()) {
       writer.write("Nome: " + director.getName() + SEPARATOR);
  }*/

  public static void loadData() {
    File file = new File(FILE_NAME);

    if (file.exists()) {
      try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
        String line;
        Movie movie = null;

        while ((line = reader.readLine()) != null) {
          if (line.startsWith("Id: ")) {
            if (movie != null) {
              movieMap.put(movie.getId(), movie);
            }

            movie = new Movie(null, 0, 0, null, null, null, null);

            movie.setId(Integer.parseInt(line.substring(4).trim()));
          } else if (line.startsWith("Title: ")) {
            movie.setTitle(line.substring(7).trim());
          } else if (line.startsWith("Release Date: ")) {
            movie.setReleaseDate(Integer.parseInt(line.substring(14).trim()));
          } else if (line.startsWith("Budget: ")) {
            movie.setBudget(Double.parseDouble(line.substring(8).trim()));
          } else if (line.startsWith("Currency: ")) {
            movie.setCurrency(line.substring(10).trim());
          } else if (line.startsWith("Description: ")) {
            movie.setDescription(line.substring(13).trim());
          } else if (line.equals(SEPARATOR)) {
            if (movie != null) {
              movieMap.put(movie.getId(), movie);
              movie = null;
            }
          }
        }

        if (movie != null) {
          movieMap.put(movie.getId(), movie);
        }
      } catch (IOException e) {
        System.err.println("Error reading from file: " + e.getMessage());
      }
    }
  }
}
/*} else if (line.startsWith("Nome: ")) {
            Actor actor = new Actor(0,null, null);
            actor.setName(line.substring(6).trim());
            movie.getActors().add(actor);
          } else if (line.startsWith("Nome: ")) {
            Director director = new Director(0,null,null);
            director.setName(line.substring(6).trim());
            movie.getDirectors().add(director);*/
/*          } else if (line.equals(SEPARATOR)) {
            // End of movie details, add it to the map
            if (movie != null) {
              movieMap.put(movie.getId(), movie);
              movie = null;
            }
          }
        }*/
// Check if there is a movie left to add after reading all lines
/*        if (movie != null) {
          movieMap.put(movie.getId(), movie);*/
//############################################################
/* private List<Actor> parseActors(String actorsString) {
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
  }*/
/* private List<Director> parseDirectors(String directorsString) {
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
  }*/
