package com.imdb;


import com.imdb.util.view.Menu;

import javax.swing.*;

/**
 * The entry point of the IMDB Library Application. This class initializes
 * the application, displaying a welcome message and showing the main menu.
 */


public class IMDBApplication {

  public static void main(String[] args) {
    Menu menu = new Menu();
    JOptionPane.showMessageDialog(null, "Welcome to the IMDB Library Application");
    menu.displayMainMenu();
  }
}
