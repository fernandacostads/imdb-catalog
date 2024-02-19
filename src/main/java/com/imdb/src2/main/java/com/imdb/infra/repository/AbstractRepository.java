package com.imdb.infra.repository;

import com.imdb.infra.database.ImdbCatalog;

import java.util.Collections;
import java.util.List;

public abstract class AbstractRepository {
    // Referência ao catálogo do IMDB
    private final ImdbCatalog imdbCatalog;

    // Construtor que recebe o catálogo do IMDB como parâmetro
    public AbstractRepository(ImdbCatalog imdbCatalog) {
        this.imdbCatalog = imdbCatalog;
    }

    // Método para adicionar um objeto ao catálogo
    public void create(Object object) {
        // Chama o método create() do catálogo do IMDB para adicionar o objeto
        this.imdbCatalog.create(object);
    }

    // Método para obter uma lista de objetos do catálogo
    public List<Object> getList() {
        // Obtém a lista de objetos do catálogo do IMDB da classe associada ao repositório
        List<Object> getObjects = this.imdbCatalog.getObjectByType(modelClass());
        // Retorna uma lista não modificável dos objetos obtidos
        return Collections.unmodifiableList(getObjects);
    }

    // Método para excluir um objeto do catálogo
    public Boolean delete(Object object) {
        // Chama o método delete() do catálogo do IMDB para excluir o objeto
        this.imdbCatalog.delete(object);
        // Retorna verdadeiro indicando que a exclusão foi bem-sucedida
        return true;
    }

    // Método abstrato para obter a classe do modelo associada ao repositório
    protected abstract Class<?> modelClass();
}
