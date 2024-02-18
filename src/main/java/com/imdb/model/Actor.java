package com.imdb.model;

import java.io.Serializable;

public class Actor extends Person implements Serializable {
  public Actor(String name, String nationality) {
    super(name, nationality);
  }
}
