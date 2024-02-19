package com.imdb.src2.main.java.com.imdb.controller;

import com.imdb.appServices.MovieService;
import com.imdb.model.Actor;
import com.imdb.model.Director;
import com.imdb.model.Movie;

import java.util.ArrayList;
import java.util.Arrays;

public class TesteController {

  private final MovieService movieService;

  public TesteController() {
    movieService = new MovieService();
  }

  Movie movie1 = new Movie(
    "Rambo I",
    1990,
    1250.10,
    "$",
    "Um veterano da Guerra do Vietnã usa todo seu treinamento e agressividade exercitada nos campos de batalha quando é preso e torturado por policiais.",
    new ArrayList<>(
      Arrays.asList(new Actor("joão", "BR"), new Actor("beto", "Pl"))
    ),
    new ArrayList<>(
      Arrays.asList(new Director("joão", "BR"), new Director("beto", "Pl"))
    )
  );
  Movie movie2 = new Movie(
    "Rambo II",
    1991,
    1350.50,
    "$",
    "Rambo está preso em uma penitenciária federal quando recebe uma proposta: será perdoado e reintegrado ao Exército se participar de uma missão no Vietnã, onde terá que enfrentar todo tipo de inimigos, inclusive oficiais americanos corruptos.",
    new ArrayList<>(
      Arrays.asList(new Actor("joão", "BR"), new Actor("beto", "Pl"))
    ),
    new ArrayList<>(
      Arrays.asList(new Director("joão", "BR"), new Director("beto", "Pl"))
    )
  );
  Movie movie3 = new Movie(
    "Rambo III",
    1992,
    1450.75,
    "$",
    "O ex-soldado John Rambo recusa um pedido do coronel Trautman, seu antigo líder de pelotão, para uma nova missão, pensando apenas em continuar com o seu novo estilo de vida, baseado na crença budista. No entanto, quando o coronel Trautman é raptado pelos russos na fronteira do Afeganistão e o governo…",
    new ArrayList<>(
      Arrays.asList(new Actor("joão", "BR"), new Actor("beto", "Pl"))
    ),
    new ArrayList<>(
      Arrays.asList(new Director("joão", "BR"), new Director("beto", "Pl"))
    )
  );
  Movie movie4 = new Movie(
    "Rambo IV",
    1993,
    1550.95,
    "$",
    "John Rambo vive na Tailândia e leva uma vida simples e solitária nas montanhas, pescando e capturando cobras venenosas para vender. Um grupo de missionários precisa passar pelas minas terrestres escondidas pelo caminho que leva ao campo de refugiados, onde pretendem entregar suprimentos médicos e co…",
    new ArrayList<>(
      Arrays.asList(new Actor("joão", "BR"), new Actor("beto", "Pl"))
    ),
    new ArrayList<>(
      Arrays.asList(new Director("joão", "BR"), new Director("beto", "Pl"))
    )
  );
  Movie movie5 = new Movie(
    "Spider-Man",
    1994,
    1650.25,
    "$",
    "filme espetacular",
    new ArrayList<>(
      Arrays.asList(new Actor("joão", "BR"), new Actor("beto", "Pl"))
    ),
    new ArrayList<>(
      Arrays.asList(new Director("joão", "BR"), new Director("beto", "Pl"))
    )
  );
  Movie movie6 = new Movie(
    "The Shawshank Redemption",
    1994,
    1750.35,
    "$",
    "Andy Dufresne é condenado a duas prisões perpétuas consecutivas pelas mortes de sua esposa e de seu amante. Porém, só Andy sabe que ele não cometeu os crimes. No presídio, durante dezenove anos, ele faz amizade com Red, sofre as brutalidades da vida na cadeia, se adapta, ajuda os carcereiros, etc.",
    new ArrayList<>(
      Arrays.asList(
        new Actor("joão", "BR"),
        new Actor("beto", "Pl"),
        new Actor("joão2", "BR"),
        new Actor("beto2", "Pl")
      )
    ),
    new ArrayList<>(
      Arrays.asList(
        new Director("joão", "BR"),
        new Director("beto", "Pl"),
        new Director("joão2", "BR"),
        new Director("beto2", "Pl")
      )
    )
  );
  Movie movie7 = new Movie(
    "Inception",
    1995,
    1850.45,
    "$",
    "filme alucinante",
    new ArrayList<>(
      Arrays.asList(new Actor("joão", "BR"), new Actor("beto", "Pl"))
    ),
    new ArrayList<>(
      Arrays.asList(new Director("joão", "BR"), new Director("beto", "Pl"))
    )
  );
  Movie movie8 = new Movie(
    "The Godfather",
    1996,
    1950.55,
    "$",
    "filme clássico",
    new ArrayList<>(
      Arrays.asList(new Actor("joão", "BR"), new Actor("beto", "Pl"))
    ),
    new ArrayList<>(
      Arrays.asList(new Director("joão", "BR"), new Director("beto", "Pl"))
    )
  );
  Movie movie9 = new Movie(
    "Pulp Fiction",
    1997,
    2050.65,
    "$",
    "filme cult",
    new ArrayList<>(
      Arrays.asList(new Actor("joão", "BR"), new Actor("beto", "Pl"))
    ),
    new ArrayList<>(
      Arrays.asList(new Director("joão", "BR"), new Director("beto", "Pl"))
    )
  );
  Movie movie10 = new Movie(
    "The Dark Knight",
    1998,
    2015,
    "$",
    "filme épico",
    new ArrayList<>(
      Arrays.asList(new Actor("joão", "BR"), new Actor("beto", "Pl"))
    ),
    new ArrayList<>(
      Arrays.asList(new Director("joão", "BR"), new Director("beto", "Pl"))
    )
  );

  public void teste() {
    movieService.addMovie(movie1);
    movieService.addMovie(movie2);
    movieService.addMovie(movie3);
    movieService.addMovie(movie4);
    movieService.addMovie(movie5);
    movieService.addMovie(movie6);
    movieService.addMovie(movie7);
    movieService.addMovie(movie8);
    movieService.addMovie(movie9);
    movieService.addMovie(movie10);
  }
}
