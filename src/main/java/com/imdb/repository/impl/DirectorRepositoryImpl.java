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
    directorsList = FileHandler.readDirectorsFromFile(FILE_PATH);
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
   * Creates a new director record based on the provided information within DirectorDTO.
   * Checks for existing directors to prevent duplicates. Updates the director list and persists
   * it to the file.
   *
   * @param entry The DirectorDTO containing information for creating a new director.
   * @return The DirectorDTO representing the created director.
   * @throws DirectorException.DirectorAlreadyExist if a director with the same identifying information already exists.
   */

  @Override
  public DirectorDTO create(DirectorDTO entry) {
    if (existingDirector(entry.name(), entry.nationality(), entry.birthDate())) {
      System.out.println("The director is already on the list!");
      return entry;
    } else {
      Director newDirector = new Director(
              idGenerator++,
              entry.name(),
              entry.nationality(),
              entry.birthDate(),
              entry.movies()
      );
      directorsList.add(newDirector);
      //     FileHandler.updateFileD(directorsList, FILE_PATH);
      return DirectorDTO.fromDirector(newDirector);
    }
  }

  /**
   * Retrieves all director records as a list of DirectorDTOs.
   *
   * @return A list of all directors in DirectorDTO form.
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
   * Updates an existing director record with new information provided in an DirectorDTO.
   *
   * @param entry  The original director details to identify the director to be updated.
   * @param entry2 The new director details for the update.
   * @return The updated DirectorDTO.
   * @throws DirectorException.DirectorNotFoundException if no director is found with the provided ID.
   */

  @Override
  public DirectorDTO update(DirectorDTO entry, DirectorDTO entry2) {
    Director director = DirectorDTO.toDirector(entry);
    director.setName(entry2.name());
    director.setNationality(entry2.nationality());
    director.setBirthDate(entry2.birthDate());
    director.setMovies(entry2.movies());

    //    FileHandler.updateFileD(directorsList, FILE_PATH);
    return DirectorDTO.fromDirector(director);
  }

  /**
   * Deletes a director based on the information provided in an DirectorDTO.
   *
   * @param entry The DirectorDTO identifying the director to be deleted.
   * @throws DirectorException.DirectorNotFoundException if the director to be deleted cannot be found.
   */


  @Override
  public void delete(DirectorDTO entry) {
    Director directorToDelete = foundDirectorId(entry.id());

    if (directorToDelete != null) {
      directorsList.remove(directorToDelete);
      //   FileHandler.updateFileD(directorsList, FILE_PATH);
    } else {
      throw new ActorException(entry.name() + "Do not existe");
    }
  }

  /**
   * Searches for directors matching a specific name.
   *
   * @param entry The DirectorDTO containing the name to search for.
   * @return A list of DirectorDTOs matching the search criteria.
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
   * Finds a director by their unique ID and returns their details as an DirectorDTO.
   *
   * @param directorDTO The DirectorDTO containing the ID of the director to read.
   * @return The DirectorDTO of the read director.
   * @throws DirectorException.DirectorNotFoundException if no director is read with the specified ID.
   */

  @Override
  public DirectorDTO readById(DirectorDTO directorDTO) {
    Director director = foundDirectorId(directorDTO.id());
    checkDirectorNotFoundException(director);
    return DirectorDTO.fromDirector(director);
  }

  /**
   * Searches for a director by ID within the list of directors.
   *
   * @param id The ID of the director to find.
   * @return The director if found, or null otherwise.
   */

  private Director foundDirectorId(int id) {
    return directorsList.stream()
            .filter(director -> director.getId() == id)
            .findFirst()
            .orElse(null);
  }

  /**
   * Searches for directors by their name.
   * This method filters all directors whose names contain the specified name substring, ignoring case.
   *
   * @param name The name or partial name to search for.
   * @return A list of directors whose names match the search criteria.
   */

  private List<DirectorDTO> searchDirectorName(String name) {
    return directorsList.stream()
            .filter(director -> director.getName().toLowerCase().contains(name.toLowerCase()))
            .map(DirectorDTO::fromDirector)
            .collect(Collectors.toList());
  }

  /**
   * Checks if the provided list of DirectorDTOs is empty and throws an {@link DirectorException.DirectorListIsEmpty} exception if it is.
   * This method is used to ensure that a list of directors is not empty before proceeding with further operations.
   *
   * @param list The list of DirectorDTOs to check for emptiness.
   */

  private void checkEmptyListException(List<DirectorDTO> list) {
    try {
      if (list.isEmpty()) {
        throw new DirectorException.DirectorListIsEmpty();
      }
    } catch (DirectorException.DirectorListIsEmpty e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Checks if the provided director is null and throws an {@link DirectorException.DirectorNotFoundException} exception if it is.
   * This method is used to ensure that a director is found before further processing.
   *
   * @param director The director object to check for null.
   */
  private void checkDirectorNotFoundException(Director director) {
    try {
      if (director == null) {
        throw new DirectorException.DirectorNotFoundException();
      }
    } catch (DirectorException.DirectorNotFoundException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Checks if a director already exists in the repository based on name, nationality, and birthdate.
   *
   * @param name        The name of the director.
   * @param nationality The nationality of the director.
   * @param birthDate   The birthdate of the director.
   * @return True if a director with the same name, nationality, and birthdate exists; false otherwise.
   */

  private boolean existingDirector(String name, String nationality, LocalDate birthDate) {
    return directorsList.stream()
            .anyMatch(director -> director.getName().equalsIgnoreCase(name) &&
                                  director.getNationality().equalsIgnoreCase(nationality) &&
                                  director.getBirthDate().equals(birthDate));
  }
}
