package com.imdb.model;

import java.io.Serializable;

public class Director extends Person implements Serializable {
  public Director(String name, String nationality) {
    super(name, nationality);
  }
}
