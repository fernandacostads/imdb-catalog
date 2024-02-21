package com.imdb.model;

public class Actor {
    private int id;
    private String name;
    private String nationality;

    public Actor(int id, String name, String nationality) {
        this.id = id;
        this.name = name;
        this.nationality = nationality;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getNationality() { return nationality; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setNationality(String nationality) { this.nationality = nationality; }
}

    @Override
    public String toString() {
        return "Actor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                '}';
    }
}
