package com.imdb.infra.database;

import com.imdb.infra.repository.ActorRepository;

import java.util.*;

public class ImdbCatalog {

    // Mapa estático para armazenar objetos por tipo de classe
    private static final Map<Class<?>, Set<Object>> OBJECTS = new HashMap<>();
    private static ImdbCatalog instance;

    private ImdbCatalog() {

    }
    public static ImdbCatalog getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ImdbCatalog();
        }
        return instance;
    }

    // Método para adicionar um objeto ao catálogo
    public void create(Object object) {
        // Obtém a coleção de objetos para a classe do objeto
        Set<Object> objects = getObjectCollection(object.getClass());
        // Adiciona o objeto à coleção
        objects.add(object);
    }

    // Método para excluir um objeto do catálogo
    public void delete(Object object) {
        // Obtém a coleção de objetos para a classe do objeto
        Set<Object> objects = getObjectCollection(object.getClass());
        // Remove o objeto da coleção
        objects.remove(object);
    }

    // Método para obter uma lista de objetos por tipo de classe
    public List<Object> getObjectByType(Class<?> clazz) {
        // Obtém a coleção de objetos para a classe especificada
        Set<Object> objects = getObjectCollection(clazz);
        // Retorna uma nova lista contendo todos os objetos da coleção
        return new ArrayList<>(objects);
    }

    // Método privado para obter a coleção de objetos para uma classe
    private Set<Object> getObjectCollection(Class<?> clazz) {
        // Obtém a coleção de objetos para a classe especificada do mapa
        // Se não existir, cria uma nova e a adiciona ao mapa
        return OBJECTS.computeIfAbsent(clazz, k -> new HashSet<>());
    }
}
