package com.imdb.util;

import com.imdb.dto.ActorDTO;
import com.imdb.dto.DirectorDTO;
import com.imdb.dto.MovieDTO;
import com.imdb.dto.ShowMovieDTO;
import com.imdb.model.Actor;
import com.imdb.model.Director;
import com.imdb.model.Movie;

import java.util.List;
import java.util.stream.Collectors;

public class ModelConvertUtil {

    public List<ShowMovieDTO> convertObjToDTO(List<Movie> movies) {
        return movies.stream().map(
                movie -> new ShowMovieDTO(movie.getId(),
                        movie.getTitle(),
                        movie.getReleaseDate(),
                        movie.getBudget(),
                        movie.getCurrency(),
                        movie.getDescription(),
                        movie.getActors().stream().map(
                                actor -> new ActorDTO(actor.getName(), actor.getNationality())).collect(Collectors.toList()),
                        movie.getDirectors().stream().map(
                                actor -> new DirectorDTO(actor.getName(), actor.getNationality())).collect(Collectors.toList())
                )).collect(Collectors.toList());
    }

    public Movie convertDTOToObjt(MovieDTO movieDTO) {
        return new Movie(
                movieDTO.title(),
                movieDTO.releaseDate(),
                movieDTO.budget(),
                movieDTO.currency(),
                movieDTO.description(),
                movieDTO.actors().stream()
                        .map(actorDTO -> new Actor(actorDTO.name(), actorDTO.nationality())).collect(Collectors.toList()),
                movieDTO.directors().stream()
                        .map(actorDTO -> new Director(actorDTO.name(), actorDTO.nationality())).collect(Collectors.toList())
        );
    }

    public MovieDTO convertObjToDTO(Movie movie) {
        return new MovieDTO(
                movie.getTitle(),
                movie.getReleaseDate(),
                movie.getBudget(),
                movie.getCurrency(),
                movie.getDescription(),
                movie.getActors().stream()
                        .map(actorDTO -> new ActorDTO(actorDTO.getName(), actorDTO.getNationality())).collect(Collectors.toList()),
                movie.getDirectors().stream()
                        .map(actorDTO -> new DirectorDTO(actorDTO.getName(), actorDTO.getNationality())).collect(Collectors.toList())
        );
    }

    public Actor convertDTOToObjt(ActorDTO actorDTO) {
        return new Actor(
                actorDTO.name(),
                actorDTO.nationality()
        );
    }

    public ActorDTO convertObjToDTO(Actor actor) {
        return new ActorDTO(
                actor.getName(),
                actor.getNationality()
        );
    }

    public Director convertDTOToObjt(DirectorDTO directorDTO) {
        return new Director(
                directorDTO.name(),
                directorDTO.nationality()
        );
    }

    public DirectorDTO convertObjToDTO(Director director) {
        return new DirectorDTO(
                director.getName(),
                director.getNationality()
        );
    }

}