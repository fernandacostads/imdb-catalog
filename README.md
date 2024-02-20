# Nome do Projeto: imdb-catalog

## Objetivo

Desenvolver um Sistema de Gerenciamento de Banco de Dados (DBMS) para um catálogo de filmes, semelhante ao IMDb, utilizando a Programação Orientada a Objetos (POO).

## Integrantes

1. ... (a preencher)
2. Daniel Martins de Andrade
3. Paulo Henrique S. Felipe
4. ... (a preencher)
5. ... (a preencher)

## Classes

### Classe Movie

```java
public class Movie {
    private String title;
    private Date releaseDate;
    private double budget;
    private String description;
    private List<Person> listOfPersons;
    private List<Evaluation> listOfEvaluations; // Adicionado
    private String genre; // Adicionado
    private int duration; // Adicionado
    private String language; // Adicionado

    // getters e setters para cada atributo

}
```

### Classe Person

```java
public class Person {
    private String name;
    private Date birthDate;
    private String nationality;
    private String bio; // Adicionado

    // getters e setters para cada atributo
}
```

### Classe Director (herda de Person)

```java
public class Director extends Person {
    private List<Movie> listOfMoviesDirected; // Adicionado

    // getters e setters para cada atributo
}
```

### Classe Actor (herda de Person)

```java
public class Actor extends Person {
    private List<Movie> listOfMoviesActed; // Adicionado

    // getters e setters para cada atributo
}
```

### Classe Evaluation <!-- Adicionado -->

```java
public class Evaluation {
    protected String evaluator; // pessoa que fez a avaliação

    // getters e setters para cada atributo
}
```

### Classe Rating (herda de Evaluation) <!-- Adicionado -->

```java
public class Rating extends Evaluation {
    private int rating;

    // getters e setters para cada atributo
}
```

### Classe Comment (herda de Evaluation) <!-- Adicionado -->

```java
public class Comment extends Evaluation {
    private String comment;

    // getters e setters para cada atributo
}
```

## Funções da Aplicação

```java
// Método registerMovie(Movie movie): adiciona um novo filme ao banco de dados
public void registerMovie(Movie movie) {
    // implementação
}

// Método registerActor(Actor actor): adiciona um novo ator ao banco de dados
public void registerActor(Actor actor) {
    // implementação
}

// Método registerDirector(Director director): adiciona um novo diretor ao banco de dados
public void registerDirector(Director director) {
    // implementação
}

// Método associateMovie(Movie movie, Actor actor, Director director): associa um filme a seus atores e diretores
public void associateMovie(Movie movie, Actor actor, Director director) {
    // implementação
}

// Método addEvaluation(Movie movie, Evaluation evaluation): adiciona uma avaliação ou comentário a um filme
public void addEvaluation(Movie movie, Evaluation evaluation) {
    // implementação
}

// Método searchMovie(String name): retorna filmes que correspondem ao nome fornecido, ignorando maiúsculas e minúsculas
public List<Movie> searchMovie(String name) {
    // implementação
}

// Método searchMovieByGenre(String genre): retorna filmes que correspondem ao gênero fornecido
public List<Movie> searchMovieByGenre(String genre) {
    // implementação
}

// Método calculateAverageRating(Movie movie): calcula a classificação média de um filme
public double calculateAverageRating(Movie movie) {
    // implementação
}

// Método listMoviesByGenre(String genre): lista todos os filmes de um determinado gênero
public List<Movie> listMoviesByGenre(String genre) {
    // implementação
}
```

## Requisitos de Construção

O sistema deve ser construído utilizando os conceitos de Programação Orientada a Objetos (POO), incluindo encapsulamento, herança, polimorfismo e classes abstratas. Adicione comentários ao seu código para explicar o que cada parte do código faz. Isso tornará seu código mais legível para outros desenvolvedores.

## Assutos para pesquisa e aprimorar o projeto:

1. _Padrão de Projeto:_ Padrões de projeto como Singleton, Factory, e DAO (Data Access Object) para tornar o código mais modular e fácil de manter.
2. _Persistência de Dados:_ Utilização de um banco de dados SQL ou NoSQL. Além disso, o uso de ORM (Object-Relational Mapping) como Hibernate.
3. _Autenticação e Autorização:_ Implementação de autenticação e autorização para proteger os dados. JWT (JSON Web Tokens) é uma opção popular para isso.
4. _Testes:_ Escrever testes unitários e de integração para garantir que o código esteja funcionando como esperado.
5. _Documentação:_ Documentar o código e usar ferramentas como Swagger para a documentação da API.
6. _Logs e Monitoramento:_ Implementar logs no aplicativo para ajudar a depurar problemas. Ferramentas de monitoramento como o Prometheus podem ser úteis.
7. _Tratamento de Erros:_ Ter um bom sistema de tratamento de erros e exceções para garantir que o aplicativo possa lidar com problemas inesperados.
8. _API RESTful:_ Considerar fazer uma aplicação uma API RESTful.
