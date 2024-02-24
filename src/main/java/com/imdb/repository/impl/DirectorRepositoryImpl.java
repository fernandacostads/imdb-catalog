package com.imdb.repository.impl;

import com.imdb.DTO.DirectorDTO;
import com.imdb.model.Director;
import com.imdb.repository.IDirectorRepository;
import com.imdb.util.FileHandler;
import com.imdb.util.exceptions.ActorException;
import com.imdb.util.exceptions.DirectorException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Implements the IDirectorRepository interface to manage director details,
 * providing a file-based persistence mechanism. Supports operations such as creating,
 * updating, deleting, and querying director records. Adopts the Singleton design pattern
 * to ensure a single instance manages director details throughout the application.
 */

public class DirectorRepositoryImpl implements IDirectorRepository {
  private static final String FILE_PATH =
          "src/main/java/com/imdb/util/resources/directors.txt";
  private static DirectorRepositoryImpl instance;
  private static List<Director> directorsList;
  private int idGenerator;

  /**
   * Initializes the director list from a file and sets up the ID generator. This private
   * constructor prevents direct instantiation from outside the class to implement the
   * Singleton pattern.
   */

  private DirectorRepositoryImpl() {
    directorsList = new ArrayList<>(10);
    //directorsList = FileHandler.readDirectorsFromFile(FILE_PATH);
    idGenerator = directorsList.isEmpty() ? 1 : directorsList.getLast().getId() + 1;
  }

  /**
   * Ensures that only one instance of the DirectorRepositoryImpl is created. If the
   * instance does not exist, it initializes a new one.
   *
   * @return The single instance of DirectorRepositoryImpl.
   */

  public static synchronized DirectorRepositoryImpl getInstance() {
    if (instance == null) {
      instance = new DirectorRepositoryImpl();
    }
    return instance;
  }

  /**
   * Creates a new director based on the provided information, adds it to the directors list, and returns a DTO representing the new director.
   * @param entry The details of the director to be created.
   * @return A DTO representing the newly created director.
   */
  @Override
  public DirectorDTO create(DirectorDTO entry) {
    checkDirectorAlreadyExist(entry.name(), entry.nationality(), String.valueOf(entry.birthDate()));

    Director newDirector = new Director(
            idGenerator++,
            entry.name(),
            entry.nationality(),
            entry.birthDate()
    );

    directorsList.add(newDirector);

    return DirectorDTO.fromDirector(newDirector);
  }

  /**
   * Returns a list of DTOs representing all existing directors.
   * @return A list of DTOs representing all existing directors.
   */
  @Override
  public List<DirectorDTO> read() {
    List<DirectorDTO> directorDTOList = directorsList.stream()
            .map(DirectorDTO::fromDirector)
            .collect(Collectors.toList());

    checkEmptyListException(directorDTOList);

    return directorDTOList;
  }

  /**
   * Updates the information of an existing director based on the provided information and returns a DTO representing the updated director.
   * @param entry The DTO representing the director to be updated.
   * @param entry2 The DTO with the new information of the director.
   * @return A DTO representing the updated director.
   */
  @Override
  public DirectorDTO update(DirectorDTO entry, DirectorDTO entry2) {
    Director director = DirectorDTO.toDirector(entry);


    director.setName(entry2.name());
    director.setNationality(entry2.nationality());
    director.setBirthDate(entry2.birthDate());

    return DirectorDTO.fromDirector(director);
  }

  /**
   * Removes a director from the list based on the provided DTO.
   * @param entry The DTO representing the director to be removed.
   */
  @Override
  public void delete(DirectorDTO entry) {
    Director director = foundDirectorId(entry.id());

    directorsList.remove(director);
  }

  /**
   * Returns a list of directors that match the provided name.
   * @param entry The DTO with the name of the director to be searched.
   * @return A list of DTOs representing the found directors.
   */
  @Override
  public List<DirectorDTO> search(DirectorDTO entry) {
    List<DirectorDTO> list = directorsList.stream()
            .filter(director -> director.getName().equalsIgnoreCase(entry.name()))
            .map(DirectorDTO::fromDirector)
            .collect(Collectors.toList());

    checkEmptyListException(list);
    return list;
  }

  /**
   * Returns a DTO representing the director with the provided ID.
   * @param directorDTO The DTO with the ID of the director to be read.
   * @return A DTO representing the found director.
   */
  @Override
  public DirectorDTO readById(DirectorDTO directorDTO) {
    Director director = foundDirectorId(directorDTO.id());

    checkDirectorNotFoundException(director);

    return DirectorDTO.fromDirector(director);
  }

  /**
   * Finds a director in the list based on the provided ID.
   * @param id The ID of the director to be found.
   * @return The found director or null if not found.
   */
  private Director foundDirectorId(int id) {
    return directorsList.stream()
            .filter(director1 -> director1.getId() == id)
            .findFirst()
            .orElse(null);
  }

  /**
   * Checks if the DTO list is empty and throws an exception if it is.
   * @param list The list of DTOs to be checked.
   */
  private void checkEmptyListException(List<DirectorDTO> list) {
    if (list.isEmpty()) {
      throw new DirectorException.DirectorListIsEmpty();
    }
  }

  /**
   * Checks if a director with the same name, nationality, and birthDate already exists and throws an exception if it does.
   * @param name The name of the director.
   * @param nationality The nationality of the director.
   * @param birthDate The birthDate of the director.
   */
  private void checkDirectorAlreadyExist(String name, String nationality, String birthDate) {
    boolean exist = directorsList.stream()
            .anyMatch(director -> director.getName().equalsIgnoreCase(name) &&
                                  director.getNationality().equalsIgnoreCase(nationality) &&
                                  director.getBirthDate().equals(birthDate));
    if (exist) {
      throw new DirectorException.DirectorAlreadyExist(name);
    }
  }

  /**
   * Checks if the director was found and throws an exception if not.
   * @param director The director to be checked.
   */
  private void checkDirectorNotFoundException(Director director) {
    if (director == null) {
      throw new DirectorException.DirectorNotFoundException();
    }
  }

}
