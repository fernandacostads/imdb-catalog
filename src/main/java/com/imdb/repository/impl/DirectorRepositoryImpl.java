package com.imdb.repository.impl;

import com.imdb.DTO.DirectorDTO;
import com.imdb.model.Director;
import com.imdb.repository.IDirectorRepository;
import com.imdb.util.FileHandler;
import com.imdb.util.exceptions.DirectorException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DirectorRepositoryImpl implements IDirectorRepository {
    private static final String FILE_PATH =
            "src/main/java/com/imdb/util/resources/directors.txt";
    private static DirectorRepositoryImpl instance;
    private static List<Director> directorsList;
    private int idGenerator;

    private DirectorRepositoryImpl() {
        directorsList = new ArrayList<>(10);
        directorsList = FileHandler.loadDirectorsFromFile(FILE_PATH);
        idGenerator = directorsList.isEmpty() ? 1 : directorsList.getLast().getId() + 1;
    }

    public static synchronized DirectorRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new DirectorRepositoryImpl();
        }
        return instance;
    }

    @Override
    public DirectorDTO create(DirectorDTO entry) {
        Director newDirector = new Director(
                idGenerator++,
                entry.name(),
                entry.nationality()
        );
        directorsList.add(newDirector);
        FileHandler.updateFileD(directorsList, FILE_PATH);
        return DirectorDTO.fromDirector(newDirector);
    }

    @Override
    public DirectorDTO update(DirectorDTO entry, DirectorDTO entry2) throws DirectorException.DirectorNotFoundException {
        Director director = findDirectorById(entry.id());
        director.setName(entry2.name());
        director.setNationality(entry2.nationality());
        FileHandler.updateFileD(directorsList, FILE_PATH);
        return DirectorDTO.fromDirector(director);
    }

    @Override
    public void delete(DirectorDTO entry) throws DirectorException.DirectorNotFoundException {
        Director director = findDirectorById(entry.id());
        directorsList.remove(director);
        FileHandler.updateFileD(directorsList, FILE_PATH);
    }

    @Override
    public List<DirectorDTO> getAll() {
        if (directorsList.isEmpty()) {
           /* throw new DirectorException.DirectorListIsEmpty();*/
            return new ArrayList<>();
        }
        return directorsList.stream()
                .map(DirectorDTO::fromDirector)
                .collect(Collectors.toList());
    }

    @Override
    public DirectorDTO readById(DirectorDTO directorDTO) throws DirectorException.DirectorNotFoundException {
        Director director = findDirectorById(directorDTO.id());
        return DirectorDTO.fromDirector(director);
    }

    @Override
    public List<DirectorDTO> readByName(DirectorDTO entry) {
        List<DirectorDTO> list = directorsList.stream()
                .filter(director -> director.getName().equalsIgnoreCase(entry.name()))
                .map(DirectorDTO::fromDirector)
                .collect(Collectors.toList());
        if (list.isEmpty()) {
            throw new DirectorException.DirectorListIsEmpty();
        }
        return list;
    }

    private Director findDirectorById(int id) {
        Director director = directorsList.stream()
                .filter(director1 -> director1.getId() == id)
                .findFirst()
                .orElse(null);
        if (director == null) {
            throw new DirectorException.DirectorNotFoundException();
        }
        return director;
    }

    //  Needs to be implemented
    private List<Director> findDirectorByName(String name) {
        List<Director> list = directorsList.stream()
                .filter(director -> director.getName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
        if (list.isEmpty()) {
            throw new DirectorException.DirectorListIsEmpty();
        }
        return list;
    }

    //  Needs to be implemented
    private List<Director> findDirectorByNationality(String nationality) {
        List<Director> list = directorsList.stream()
                .filter(director -> director.getNationality().toLowerCase().contains(nationality.toLowerCase()))
                .collect(Collectors.toList());
        if (list.isEmpty()) {
            throw new DirectorException.DirectorListIsEmpty();
        }
        return list;
    }

}
