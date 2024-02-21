package com.imdb.model;

public abstract class Person {

    private int id;
    private String name;
    private String nationality;

    public Person(String name, String nationality) {
        this.name = name;
        this.nationality = nationality;
    }

    @Override
    public String toString() {
        return "ID: " + id +
                ", Name: " + name +
                ", Nationality: " + nationality;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

}
