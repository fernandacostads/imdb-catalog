<<<<<<< HEAD
package com.imdb.appServices;// Assuming Movie, Actor, Director, and Phone classes are properly defined

import com.imdb.model.Actor;
import com.imdb.model.Director;
import com.imdb.model.Movie;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MovieService1 {
  private static final String FILE_NAME = "../data/dados.txt";
  private static final String SEPARATOR = System.lineSeparator();
  private static final Map<Integer, Movie> movieMap = new HashMap<>();

  public static void updateFile() throws IOException {
    try (FileWriter writer = new FileWriter(FILE_NAME, false)) {
      for (Movie movie : movieMap.values()) {
        writer.write("Id: " + movie.getId() + SEPARATOR);
        writer.write("Title: " + movie.getTitle() + SEPARATOR);
        writer.write("Release Date: " + movie.getReleaseDate() + SEPARATOR);
        writer.write("Budget: " + movie.getBudget() + SEPARATOR);
        writer.write("Currency: " + movie.getCurrency() + SEPARATOR);

        for (Actor actor : movie.getActors()) {
          writer.write("Nome: " + actor.getName() + SEPARATOR);
        }

        for (Director director : movie.getDirectors()) {
          writer.write("Nome: " + director.getName() + SEPARATOR);
        }

        writer.write(SEPARATOR);
      }
    }
  }

  public static void loadData() throws IOException {
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

            movie = new Movie(null,null,0,null,null,null,null);
            movie.setId(Integer.parseInt(line.substring(4).trim()));
          } else if (line.startsWith("Title: ")) {
            movie.setTitle(line.substring(7).trim());
          } else if (line.startsWith("Release Date: ")) {
            movie.setReleaseDate(line.substring(14).trim());
          } else if (line.startsWith("Budget: ")) {
            movie.setBudget(Double.parseDouble(line.substring(8).trim()));
          } else if (line.startsWith("Currency: ")) {
            movie.setCurrency(line.substring(10).trim());
          } else if (line.startsWith("Nome: ")) {
            Actor actor = new Actor(0,null, null);
            actor.setName(line.substring(6).trim());
            movie.getActors().add(actor);
          } else if (line.startsWith("Nome: ")) {
            Director director = new Director(0,null, null);
            director.setName(line.substring(6).trim());
            movie.getDirectors().add(director);
          } else if (line.equals(SEPARATOR)) {
            // End of movie details, add it to the map
            if (movie != null) {
              movieMap.put(movie.getId(), movie);
              movie = null;
            }
          }
        }

        // Check if there is a movie left to add after reading all lines
        if (movie != null) {
          movieMap.put(movie.getId(), movie);
        }
      }
    }
  }

}








=======
package com.imdb.appServices;

import com.imdb.model.Movie;
import com.imdb.repository.MovieRepository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MovieService {
    private List<Movie> movies;

    public MovieService() {
        movies = new ArrayList<>();
    }
    private int nextId = 1;
    private final MovieRepository movieRepository = new MovieRepository();

    public List<Movie> getAllMovies() {
        return movieRepository.getAllMovies();
    }

    public Movie getMovieById(int id) {
        return movieRepository.getMovieById(id);
    }

    public Movie getMovieByName(String name) {
        List<Movie> movies = getAllMovies();
        for (Movie movie : movies) {
            if (movie.getTitle().equalsIgnoreCase(name)) {
                return movie;
            }
        }
        return null;
    }

    public void addMovie(Movie movie) {
        movie.setId(nextId++);
        movieRepository.addMovie(movie);
    }

    public void deleteMovie(Movie movie) {
        movieRepository.deleteMovie(movie.getId());
    }

    public boolean deleteMovieById(int movieId) {
        for (Iterator<Movie> iterator = movies.iterator(); iterator.hasNext(); ) {
            Movie movie = iterator.next();
            if (movie.getId() == movieId) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }
}
>>>>>>> origin/develop
