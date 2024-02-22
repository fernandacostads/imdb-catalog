//package com.imdb.util;
//
//import com.imdb.dto.ActorDTO;
//import com.imdb.dto.DirectorDTO;
//import com.imdb.dto.MovieDTO;
//import com.imdb.model.Actor;
//import com.imdb.model.Director;
//import com.imdb.model.Movie;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//public class ModelConvertUtil {
//
//public static MovieDTO convertToMovieDTO(Movie movie) {
//        List<ActorDTO> actorDTOs = movie.getActors().stream()
//                .map(ModelConvertUtil::convertToActorDTO)
//                .collect(Collectors.toList());
//
//        List<DirectorDTO> directorDTOs = movie.getDirectors().stream()
//                .map(ModelConvertUtil::convertToDirectorDTO)
//                .collect(Collectors.toList());
//
//        return new MovieDTO(
//                movie.getId(),
//                movie.getTitle(),
//                movie.getReleaseDate(),
//                movie.getBudget(),
//                movie.getCurrency(),
//                movie.getDescription(),
//                actorDTOs,
//                directorDTOs
//        );
//    }
//
//    // Convert MovieDTO to Movie model
//    public static Movie convertToMovieModel(MovieDTO movieDTO) {
//        List<Actor> actors = movieDTO.actors().stream()
//                .map(ModelConvertUtil::convertToActorModel)
//                .collect(Collectors.toList());
//
//        List<Director> directors = movieDTO.directors().stream()
//                .map(ModelConvertUtil::convertToDirectorModel)
//                .collect(Collectors.toList());
//
//        return new Movie(
//                movieDTO.id(),
//                movieDTO.title(),
//                movieDTO.releaseDate(),
//                movieDTO.budget(),
//                movieDTO.currency(),
//                movieDTO.description(),
//                actors,
//                directors
//        );
//    }
//
//    // Convert Actor model to ActorDTO
//    public static ActorDTO convertToActorDTO(Actor actor) {
//        return new ActorDTO(actor.getId(), actor.getName(), actor.getNationality());
//    }
//
//    // Convert ActorDTO to Actor model
//    public static Actor convertToActorModel(ActorDTO actorDTO) {
//        return new Actor(actorDTO.id(), actorDTO.name(), actorDTO.nationality());
//    }
//
//    // Convert Director model to DirectorDTO
//    public static DirectorDTO convertToDirectorDTO(Director director) {
//        return new DirectorDTO(director.getId(), director.getName(), director.getNationality());
//    }
//
//    // Convert DirectorDTO to Director model
//    public static Director convertToDirectorModel(DirectorDTO directorDTO) {
//        return new Director(directorDTO.id(), directorDTO.name(), directorDTO.nationality());
//    }
//
//
//    /*public List<MovieDTO> convertObjToDTO(List<Movie> movies) {
//        return movies.stream().map(
//                movie -> new MovieDTO(
//                        movie.getId(),
//                        movie.getTitle(),
//                        movie.getReleaseDate(),
//                        movie.getBudget(),
//                        movie.getCurrency(),
//                        movie.getDescription(),
//                        convertActorListObjToDTO(movie.getActors()),
//                        convertDirectorListObjToDTO(movie.getDirectors())
//                )).collect(Collectors.toList());
//    }
//
//    public List<ActorDTO> convertActorListObjToDTO(List<Actor> actorsList) {
//        return actorsList.stream().map(actor -> new ActorDTO(actor.getId(),actor.getName(), actor.getNationality())).collect(Collectors.toList());
//    }
//
//    public List<DirectorDTO> convertDirectorListObjToDTO(List<Director> directorList) {
//        return directorList.stream().map(director -> new DirectorDTO(director.getId(), director.getName(), director.getNationality())).collect(Collectors.toList());
//    }
//
//    public Movie convertDTOToObjt(MovieDTO movieDTO) {
//        return new Movie(
//                movieDTO.id(),
//                movieDTO.title(),
//                movieDTO.releaseDate(),
//                movieDTO.budget(),
//                movieDTO.currency(),
//                movieDTO.description(),
//                movieDTO.actors().stream().map(actorDTO -> new Actor(actorDTO.id(),actorDTO.name(), actorDTO.nationality())).collect(Collectors.toList()),
//                movieDTO.directors().stream().map(directorDTO -> new Director(directorDTO.id(), directorDTO.name(), directorDTO.nationality())).collect(Collectors.toList())
//        );
//    }
//
//    public MovieDTO convertObjToDTO(Movie movie) {
//        return new MovieDTO(
//                movie.getId(),
//                movie.getTitle(),
//                movie.getReleaseDate(),
//                movie.getBudget(),
//                movie.getCurrency(),
//                movie.getDescription(),
//                movie.getActors().stream()
//                        .map(actorDTO -> new ActorDTO(actorDTO.getId(), actorDTO.getName(), actorDTO.getNationality())).collect(Collectors.toList()),
//                movie.getDirectors().stream()
//                        .map(directorDTO -> new DirectorDTO(directorDTO.getId(), directorDTO.getName(), directorDTO.getNationality())).collect(Collectors.toList())
//        );
//    }
//
//    public Actor convertDTOToObjt(ActorDTO actorDTO) {
//        return new Actor(
//                actorDTO.id(),
//                actorDTO.name(),
//                actorDTO.nationality()
//        );
//    }
//
//    public ActorDTO convertObjToDTO(Actor actor) {
//        return new ActorDTO(
//                actor.getId(),
//                actor.getName(),
//                actor.getNationality()
//        );
//    }
//
//    public Director convertDTOToObjt(DirectorDTO directorDTO) {
//        return new Director(
//                directorDTO.id(),
//                directorDTO.name(),
//                directorDTO.nationality()
//        );
//    }
//
//    public DirectorDTO convertObjToDTO(Director director) {
//        return new DirectorDTO(
//                director.getId(),
//                director.getName(),
//                director.getNationality()
//        );
//    }*/
//}
