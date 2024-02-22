///*package com.imdb.repository.impl;
//
//import com.imdb.dto.MovieDTO;
//import com.imdb.model.Movie;
//import com.imdb.repository.IMovieRepository;
//import com.imdb.util.FileHandler;
//import com.imdb.util.ModelConvertUtil;
//import com.imdb.util.expections.AlreadExist.MovieAlreadyExist;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//public class MovieRepositoryImpl implements IMovieRepository {
//
//  private static final String FILE_PATH =
//          "src/main/java/com/imdb/util/resources/movies.txt";
//  private static MovieRepositoryImpl instance;
//  private static List<Movie> moviesList;
//  private final ModelConvertUtil converter = new ModelConvertUtil();
//  private int idGenerator;
//
//  private MovieRepositoryImpl() {
//    moviesList = new ArrayList<>(10);
//    moviesList = FileHandler.loadMoviesFromFile(FILE_PATH);
//    idGenerator = moviesList.isEmpty() ? 1 : moviesList.getLast().getId() + 1;
//  }
//
//
//  public static synchronized MovieRepositoryImpl getInstance() {
//    if (instance == null) {
//      instance = new MovieRepositoryImpl();
//    }
//    return instance;
//  }
//
//  @Override
//  public void create(MovieDTO movieDTO) {
//    Movie movie = isMoviePresent(movieDTO);
//    movie.setId(idGenerator++);
//    moviesList.add(movie);
//    FileHandler.updateFile(moviesList, FILE_PATH);
//  }
//
//  @Override
//  public MovieDTO update(MovieDTO movieDTO, MovieDTO newmMovieDTO) {
//    Movie movie = isMoviePresent(movieDTO);
//    movie.setTitle(movie.getTitle());
//    movie.setReleaseDate(movie.getReleaseDate());
//    movie.setBudget(movie.getBudget());
//    movie.setCurrency(movie.getCurrency());
//    movie.setDescription(movie.getDescription());
//
//    FileHandler.updateFile(moviesList, FILE_PATH);
//    return converter.convertObjToDTO(movie);
//  }
//
//  @Override
//  public void delete(MovieDTO movieDTO) {
//    Movie movie = isMoviePresent(movieDTO);
//    moviesList.remove(movie);
//    FileHandler.updateFile(moviesList, FILE_PATH);
//  }
//
//  @Override
//  public List<MovieDTO> getAll() {
//    return converter.convertObjToDTO(moviesList);
//  }
//
//  @Override
//  public MovieDTO readById(int id) {
//    Optional<Movie> movie = moviesList.stream()
//            .filter(aux -> aux.getId() == id)
//            .findFirst();
//
//    if (movie.isPresent()) {
//      return converter.convertObjToDTO(movie.get());
//    } else {
//      throw new IllegalStateException("Paulo");//paulo
//    }
//  }
//
//  @Override
//  public MovieDTO readByName(String title) {
//    Optional<Movie> movie = moviesList
//            .stream()
//            .filter(aux -> aux.getTitle()
//                    .equalsIgnoreCase(title)).findFirst();
//    return converter.convertObjToDTO(movie.get());
//  }
//
//  private Movie isMoviePresent(MovieDTO movieDTO) {
//    Movie movie = converter.convertDTOToObjt(movieDTO);
//    Optional<Movie> existingMovie = moviesList
//            .stream()
//            .filter(aux -> aux.equals(movie))
//            .findFirst();
//
//    if (existingMovie.isEmpty()) {
//      return movie;
//    } else {
//      throw new MovieAlreadyExist(existingMovie.get());
//    }
//  }
//}*/
//
//package com.imdb.repository.impl;
//
//import com.imdb.dto.MovieDTO;
//import com.imdb.model.Movie;
//import com.imdb.repository.IMovieRepository;
//import com.imdb.util.FileHandler;
//import com.imdb.util.ModelConvertUtil;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.atomic.AtomicInteger;
//import java.util.stream.Collectors;
//
//public class MovieRepositoryImpl implements IMovieRepository {
//   /* private static final String FILE_PATH = "src/main/resources/movies.txt";
//    private static MovieRepositoryImpl instance;
//    private List<MovieDTO> movies;
//    private AtomicInteger idGenerator;
//
//    private MovieRepositoryImpl() {
//        this.movies = FileHandler.loadMoviesFromFile(FILE_PATH);
//        this.idGenerator = new AtomicInteger(movies.stream().mapToInt(MovieDTO::id).max().orElse(0) + 1);
//    }
//
//    public static synchronized MovieRepositoryImpl getInstance() {
//        if (instance == null) {
//            instance = new MovieRepositoryImpl();
//        }
//        return instance;
//    }*/
//
//private static final String FILE_PATH =
//        "src/main/java/com/imdb/util/resources/movies.txt";
//private static MovieRepositoryImpl instance;
//private static List<Movie> moviesList;
//private final ModelConvertUtil converter = new ModelConvertUtil();
//private int idGenerator;
//
//private MovieRepositoryImpl() {
//    moviesList = new ArrayList<>(10);
//    moviesList = FileHandler.loadMoviesFromFile(FILE_PATH);
//    idGenerator = moviesList.isEmpty() ? 1 : moviesList.getLast().getId() + 1;
//}
//
//
//public static synchronized MovieRepositoryImpl getInstance() {
//    if (instance == null) {
//        instance = new MovieRepositoryImpl();
//    }
//    return instance;
//}
//    @Override
//    public void create(MovieDTO movie) {
//        MovieDTO newMovie = new MovieDTO(idGenerator.getAndIncrement(), movie.title(), movie.releaseDate(), movie.budget(), movie.currency(), movie.description(), movie.actors(), movie.directors());
//        movies.add(newMovie);
//        FileHandler.saveMoviesToFile(movies, FILE_PATH);
//    }
//
//
//    /*@Override
//    public void create(MovieDTO movie) {
//        MovieDTO newMovie = new MovieDTO(idGenerator.getAndIncrement(), movie.title(), movie.releaseDate(), movie.budget(), movie.currency(), movie.description(), movie.actors(), movie.directors());
//        movies.add(newMovie);
//        FileHandler.saveMoviesToFile(movies, FILE_PATH);
//    }*/
//
//    @Override
//    public MovieDTO update(MovieDTO original, MovieDTO updated) {
//        int index = movies.indexOf(original);
//        if (index != -1) {
//            movies.set(index, updated);
//            FileHandler.saveMoviesToFile(movies, FILE_PATH);
//            return updated;
//        }
//        return null;
//    }
//
//    @Override
//    public void delete(MovieDTO movie) {
//        movies.remove(movie);
//        FileHandler.saveMoviesToFile(movies, FILE_PATH);
//    }
//
//    @Override
//    public List<MovieDTO> getAll() {
//        return movies;
//    }
//
//    @Override
//    public MovieDTO readById(int id) {
//        return movies.stream().filter(movie -> movie.id() == id).findFirst().orElse(null);
//    }
//
//    @Override
//    public MovieDTO readByName(String title) {
//        return movies.stream().filter(movie -> movie.title().equalsIgnoreCase(title)).findFirst().orElse(null);
//    }
//
//    @Override
//    public List<MovieDTO> searchByTitle(String title) {
//        return movies.stream().filter(movie -> movie.title().toLowerCase().contains(title.toLowerCase())).collect(Collectors.toList());
//    }
//}
