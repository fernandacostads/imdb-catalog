package com.imdb.repository.impl;

import com.imdb.DTO.ActorDTO;
import com.imdb.DTO.DirectorDTO;
import com.imdb.DTO.MovieDTO;
import com.imdb.model.Movie;
import com.imdb.repository.IMovieRepository;
import com.imdb.util.FileHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MovieRepositoryImpl implements IMovieRepository {
    private static final String FILE_PATH =
            "src/main/java/com/imdb/util/resources/movies.txt";
    private static MovieRepositoryImpl instance;
    private static List<Movie> moviesList;
    private int idGenerator;

    private MovieRepositoryImpl() {
        moviesList = new ArrayList<>(10);
        moviesList = FileHandler.loadMoviesFromFile(FILE_PATH);
        idGenerator = moviesList.isEmpty() ? 1 : moviesList.getLast().getId() + 1;
    }

    public static synchronized MovieRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new MovieRepositoryImpl();
        }
        return instance;
    }

    @Override
    public MovieDTO create(MovieDTO entry) {
        Movie newMovie = new Movie(
                idGenerator++,
                entry.title(),
                entry.releaseDate(),
                entry.budget(),
                entry.currency(),
                entry.description(),
                entry.actors().stream()
                        .map(ActorDTO::toActor)
                        .collect(Collectors.toList()),
                entry.directors().stream()
                        .map(DirectorDTO::toDirector)
                        .collect(Collectors.toList())
        );

        moviesList.add(newMovie);
        FileHandler.updateFile(moviesList, FILE_PATH);
        return MovieDTO.fromMovie(newMovie);
    }

    @Override
    public MovieDTO update(MovieDTO entry, MovieDTO entry2) {
        Movie existingMovie = findMovieById(entry.id());

        if (existingMovie != null) {
            existingMovie.setTitle(entry2.title());
            existingMovie.setReleaseDate(entry2.releaseDate());
            existingMovie.setBudget(entry2.budget());
            existingMovie.setCurrency(entry2.currency());
            existingMovie.setDescription(entry2.description());

            FileHandler.updateFile(moviesList, FILE_PATH);
            return MovieDTO.fromMovie(existingMovie);
        }
      return null;
    }

    @Override
    public void delete(MovieDTO entry) {
        Movie movieToDelete = findMovieById(entry.id());

        if (movieToDelete != null) {
            moviesList.remove(movieToDelete);
            FileHandler.updateFile(moviesList, FILE_PATH);
        }
    }

    @Override
    public List<MovieDTO> getAll() {
        if(moviesList.isEmpty()){
            return new ArrayList<>();
        }else {
            return moviesList.stream()
                    .map(MovieDTO::fromMovie)
                    .collect(Collectors.toList());
        }
    }

    @Override
    public MovieDTO readById(MovieDTO id) {
        Movie movie = findMovieById(id.id());
        return MovieDTO.fromMovie(movie);
    }

    @Override
    //ReadByTitle
    public List<MovieDTO> readByName(MovieDTO entry) {
        List<Movie> movies = findMoviesByTitle(entry.title());
        return movies.stream()
                .map(MovieDTO::fromMovie)
                .collect(Collectors.toList());
    }

    private Movie findMovieById(int id) {
        return moviesList.stream()
                .filter(movie -> movie.getId() == id)
                .findFirst()
                .orElse(null);
    }

    private List<Movie> findMoviesByTitle(String title) {
        return moviesList.stream()
                .filter(movie -> movie.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
    }

    /*private List<Movie> findMoviesByReleaseData(int releaseDate) {
        return moviesList.stream()
                .filter(movie -> movie.getReleaseDate() == releaseDate)
                .collect(Collectors.toList());
    }*/
}
