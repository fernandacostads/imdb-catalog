package com.imdb.infra.repository;


import com.imdb.core.cases.actor.Actor;
import com.imdb.infra.database.ImdbCatalog;

import java.util.Objects;


public class ActorRepository extends AbstractRepository {
    private static final ImdbCatalog imdbCatalog = ImdbCatalog.getInstance();
    private static ActorRepository instance;

    private ActorRepository() {
        super(imdbCatalog);
    }

    public static ActorRepository getInstance() {
        if (Objects.isNull(instance)) {
            instance = new ActorRepository();
        }
        return instance;
    }

    @Override
    protected Class<?> modelClass() {
        return Actor.class;
    }


    // Estas funções são da classe antiga, mas servem de exemplo para nós vermos como usar as funções das classes abstratas
    // Ignorar os objetos Album

//    public List consultarPorNome(String nome) {
//        List albuns = getList();
//        List albumComONomeBuscado = new ArrayList();
//        for (Object objeto : albuns) {
//            Album album = (Album) objeto;
//            if (compararNomeAlbum(album, nome)) {
//                albumComONomeBuscado.add(album);
//            }
//        }
//        return albumComONomeBuscado;
//    }
//
//    public List consultarPorAutor(Artista autor) {
//        List albuns = getList();
//        List albunsDoAutor = new ArrayList();
//        for (Object objeto : albuns) {
//            Album album = (Album) objeto;
//            if (compararAutor(album, autor)) {
//                albunsDoAutor.add(album);
//            }
//        }
//        return albunsDoAutor;
//    }
//
//    public List consultarPorAutorOuNome(Artista autor, String nomeDoAlbum) {
//        List albuns = getList();
//        List albunsFiltrado = new ArrayList();
//        for (Object objeto : albuns) {
//            Album album = (Album) objeto;
//            if (compararNomeAlbum(album, nomeDoAlbum)
//                    || compararAutor(album, autor)) {
//                albunsFiltrado.add(album);
//            }
//        }
//        return albunsFiltrado;
//    }
//
//    private Boolean compararAutor(Album album, Artista autor) {
//        return autor != null
//                && autor.getNome() != null
//                && album.getAutor() != null
//                && album.getAutor().getNome() != null
//                && autor.getNome().equalsIgnoreCase(album.getAutor().getNome());
//    }
//
//    private Boolean compararNomeAlbum(Album album, String nomeParaComparacao) {
//        return album.getNome() != null
//                && nomeParaComparacao != null
//                && album.getNome().contains(nomeParaComparacao);
//    }

}
