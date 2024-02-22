//package com.imdb.repository.impl;
//
//import com.imdb.dto.DirectorDTO;
//import com.imdb.model.Director;
//import com.imdb.repository.IDirectorRepository;
//import com.imdb.util.FileHandler;
//import com.imdb.util.ModelConvertUtil;
//import com.imdb.util.expections.AlreadExist.DirectorAlreadyExist;
//import com.imdb.util.expections.NotFoundException.DirectorNotFoundException;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//public class DirectorRepositoryImpl implements IDirectorRepository {
//
//  private static final String FILE_PATH =
//          "src/main/java/com/imdb/util/resources/directors.txt";
//  private static DirectorRepositoryImpl instance;
//  private static List<Director> directorsList;
//  private final ModelConvertUtil converter = new ModelConvertUtil();
//  private int idGenerator;
//
//  private DirectorRepositoryImpl() {
//    directorsList = new ArrayList<>(10);
//    directorsList = FileHandler.loadDirectorsFromFile(FILE_PATH);
//    idGenerator = directorsList.isEmpty() ? 1 : directorsList.getLast().getId() + 1;
//  }
//
//  public static synchronized DirectorRepositoryImpl getInstance() {
//    if (instance == null) {
//      instance = new DirectorRepositoryImpl();
//    }
//    return instance;
//  }
//
//  @Override
//  public void create(DirectorDTO directorDTO) {
//    Director director = isPresent(directorDTO);
//    director.setId(idGenerator++);
//    directorsList.add(director);
//    FileHandler.updateFileD(directorsList, FILE_PATH);
//  }
//
//  @Override
//  public DirectorDTO update(DirectorDTO directorDTO, DirectorDTO newDirectorDTO) {
//    Director director = converter.convertDTOToObjt(directorDTO);
//    director.setName(newDirectorDTO.name());
//    director.setNationality(newDirectorDTO.nationality());
//    FileHandler.updateFileD(directorsList, FILE_PATH);
//    return converter.convertObjToDTO(director);
//  }
//
//  @Override
//  public void delete(DirectorDTO directorDTO) {
//    directorsList.remove(converter.convertDTOToObjt(directorDTO));
//    FileHandler.updateFileD(directorsList, FILE_PATH);
//  }
//
//  @Override
//  public List<DirectorDTO> getAll() {
//    return converter.convertDirectorListObjToDTO(directorsList);
//  }
//
//  @Override
//  public DirectorDTO readById(int id) {
//    Optional<Director> director = directorsList.stream()
//            .filter(aux -> aux.getId() == id)
//            .findFirst();
//
//    if (director.isPresent()) {
//      return converter.convertObjToDTO(director.get());
//    } else {
//      throw new IllegalStateException("Paulo");//paulo
//    }
//  }
//
//  @Override
//  public DirectorDTO readByName(String name) {
//    Optional<Director> optionalDirector = directorsList
//            .stream()
//            .filter(aux -> aux.getName().equalsIgnoreCase(name))
//            .findFirst();
//    if (optionalDirector.isPresent()) {
//      return converter.convertObjToDTO(optionalDirector.get());
//    } else {
//      throw new DirectorNotFoundException(name);//Paulo
//    }
//  }
//
//  private Director isPresent(DirectorDTO directorDTO) {
//    Director director = converter.convertDTOToObjt(directorDTO);
//    Optional<Director> optionalDirector = directorsList
//            .stream()
//            .filter(aux -> aux.equals(director))
//            .findFirst();
//
//    if (optionalDirector.isEmpty()) {
//      return director;
//    } else {
//      throw new DirectorAlreadyExist(director);
//    }
//  }
//}
