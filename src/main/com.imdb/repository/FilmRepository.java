package repository;

import model.Film;

import java.util.List;

public interface FilmRepository {
    public Film create(Film film);

    public List<Film> readByName(String name);

    public Film update(Film film);

    public void delete(Film film);
}
