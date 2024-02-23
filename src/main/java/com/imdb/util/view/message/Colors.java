package com.imdb.util.view.message;

/**
 * Enumeration of ANSI color codes for formatting console output. Allows
 * for text color customization in console-based applications to improve
 * readability and user experience.
 */

public enum Colors {
  RESET("\u001B[0m"),
  RED("\u001B[31m"),
  GREEN("\u001B[32m"),
  //BLUE("\u001B[34m"),
  YELLOW("\u001B[33m");

  private final String codeANSI;

  /**
   * Constructs a color instance with an ANSI color code.
   *
   * @param codeANSI The ANSI code for the color.
   */

  Colors(String codeANSI) {
    this.codeANSI = codeANSI;
  }

  /**
   * Returns the ANSI color code.
   *
   * @return The ANSI code string for the color.
   */

  @Override
  public String toString() {
    return codeANSI;
  }
}
