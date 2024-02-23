package com.imdb.util;

import com.imdb.model.Actor;
import com.imdb.model.Director;
import com.imdb.model.Movie;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles file operations for movies, actors, and directors.
 * Provides functionality to update and read from files.
 */

public class FileHandler {

    private static final String SEPARATOR = System.lineSeparator();

    public FileHandler() {
    }

    /**
     * Updates the file with the current list of movies.
     * Each movie's details are written to the file along with associated actors and directors.
     *
     * @param moviesList The list of movies to write to the file.
     * @param FILE_NAME The path to the file to be updated.
     */

    public static void updateFile(List<Movie> moviesList, String FILE_NAME) {
        try (FileWriter writer = new FileWriter(FILE_NAME, false)) {
            for (Movie movie : moviesList) {
                String movieInfo = createMovieInfo(movie);
                writer.write(movieInfo);

                updateActorsFile(movie.getActors(), writer);
                updateDirectorsFile(movie.getDirectors(), writer);

                writer.write(SEPARATOR);
            }
        } catch (Exception e) {
            System.err.println("Error updating file: " + e.getMessage());
        }
    }

    /**
     * Creates a string representation of a movie's information.
     *
     * @param movie The movie object.
     * @return A string containing the movie's details.
     */

    private static String createMovieInfo(Movie movie) {
        return "Id: " + movie.getId() + SEPARATOR +
                "Title: " + movie.getTitle() + SEPARATOR +
                "Release Date: " + movie.getReleaseDate() + SEPARATOR +
                "Budget: " + movie.getBudget() + SEPARATOR +
                "Currency: " + movie.getCurrency() + SEPARATOR;
    }

    /**
     * Writes actor information for a movie to the file.
     *
     * @param actorsList The list of actors to be written.
     * @param writer     The FileWriter object used to write to the file.
     * @throws IOException If an I/O error occurs.
     */

    private static void updateActorsFile(List<Actor> actorsList, FileWriter writer) throws IOException {
        writer.write("Actors: " + SEPARATOR);
        for (Actor actor : actorsList) {
            writer.write("Id: " + actor.getId() +
                    ", Name: " + actor.getName() +
                    ", Nationality: " + actor.getNationality() + SEPARATOR);
        }
    }

    /**
     * Writes director information for a movie to the file.
     *
     * @param directorsList The list of directors to be written.
     * @param writer        The FileWriter object used to write to the file.
     * @throws IOException If an I/O error occurs.
     */

    private static void updateDirectorsFile(List<Director> directorsList, FileWriter writer) throws IOException {
        writer.write("Directors: " + SEPARATOR);
        for (Director director : directorsList) {
            writer.write("Id: " + director.getId() +
                    ", Name: " + director.getName() +
                    ", Nationality: " + director.getNationality() + SEPARATOR);
        }
    }

    public static void updateFileA(List<Actor> actorsList, String FILE_NAME) {

        try (FileWriter writer = new FileWriter(FILE_NAME, false)) {
            createActorsInfo(actorsList, writer);
        } catch (Exception e) {
            System.err.println("Error updating file: " + e.getMessage());
        }
    }

    public static void updateFileD(List<Director> directorsList, String FILE_NAME
    ) {
        try (FileWriter writer = new FileWriter(FILE_NAME, false)) {
            createDirectorsInfo(directorsList, writer);
        } catch (Exception e) {
            System.err.println("Error updating file: " + e.getMessage());
        }
    }

    private static void createActorsInfo(List<Actor> actorsList, FileWriter writer) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("Actors: ").append(SEPARATOR);
        for (Actor actor : actorsList) {
            sb.append("Id: ").append(actor.getId())
                    .append(", Name: ").append(actor.getName())
                    .append(", Nationality: ").append(actor.getNationality())
                    .append(SEPARATOR);
        }
        writer.write(sb.toString());
    }

    private static void createDirectorsInfo(List<Director> directorsList, FileWriter writer) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("Directors: ").append(SEPARATOR);
        for (Director director : directorsList) {
            sb.append("Id: ").append(director.getId())
                    .append(", Name: ").append(director.getName())
                    .append(", Nationality: ").append(director.getNationality())
                    .append(SEPARATOR);
        }
        writer.write(sb.toString());
    }

    /**
     * Reads movies from a file and converts them into a list of Movie objects.
     *
     * @param fileName The path to the file to be read.
     * @return A list of Movie objects.
     */

    public static List<Movie> loadMoviesFromFile(String fileName) {
        List<Movie> movies = new ArrayList<>(10);

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;

            while ((line = br.readLine()) != null) {
                if (line.startsWith("Id:")) {
                    int id = Integer.parseInt(line.split(":")[1].trim());
                    Movie movie = createMovieFromLines(br, id);

                    movies.add(movie);
                }
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + fileName);
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }

        System.out.println("All movies loaded." + movies);
        return movies;
    }


    private static Movie createMovieFromLines(BufferedReader reader, int id) throws IOException {
        String title = getValue(reader.readLine());
        int year = Integer.parseInt(getValue(reader.readLine()));
        double rating = Double.parseDouble(getValue(reader.readLine()));
        String genre = getValue(reader.readLine());
        String director = getValue(reader.readLine());
        List<Actor> actors = readActors(reader);
        List<Director> directors = readDirectors(reader);

        return new Movie(id, title, year, rating, genre, director, actors, directors);
    }

    private static String getValue(String line) {
        String[] parts = line.split(": ");
        return parts.length > 1 ? parts[1].trim() : "";
    }

    private static List<Actor> readActors(BufferedReader br) throws IOException {
        List<Actor> actorsList = new ArrayList<>();
        String line;

        while ((line = br.readLine()) != null && line.startsWith("Id:")) {

            String[] tokens = line.split(", ");
            int id = Integer.parseInt(tokens[0].split(": ")[1]);
            String name = tokens[1].split(": ")[1];
            String nationality = tokens[2].split(": ")[1];
            Actor actor = new Actor(id, name, nationality);
            actor.setId(id);
            actorsList.add(actor);
        }

        return actorsList;
    }

    private static List<Director> readDirectors(BufferedReader reader) throws IOException {
        List<Director> directorsList = new ArrayList<>();
        String line;

        while ((line = reader.readLine()) != null && line.startsWith("Id:")) {
            String[] tokens = line.split(", ");
            int id = Integer.parseInt(tokens[0].split(": ")[1]);
            String name = tokens[1].split(": ")[1];
            String nationality = tokens[2].split(": ")[1];
            Director director = new Director(id, name, nationality);
            director.setId(id);
            directorsList.add(director);
        }

        return directorsList;
    }

    /**
     * Reads actor information from a file and constructs a list of Actor objects.
     *
     * @param fileName The path to the file containing actor information.
     * @return A list of Actor objects constructed from the file.
     */

    public static List<Actor> loadActorsFromFile(String fileName) {
        List<Actor> actorsList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0].split(": ")[1].trim());
                    String name = parts[1].split(": ")[1].trim();
                    String nationality = parts[2].split(": ")[1].trim();
                    Actor actor = new Actor(id, name, nationality);
                    actorsList.add(actor);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return actorsList;
    }

    public static List<Director> loadDirectorsFromFile(String fileName) {
        List<Director> directorsList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(", ");
                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0].split(": ")[1].trim());
                    String name = parts[1].split(": ")[1].trim();
                    String nationality = parts[2].split(": ")[1].trim();
                    Director director = new Director(id, name, nationality);
                    directorsList.add(director);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
        return directorsList;
    }
}