package com.imdb;


import com.imdb.util.view.Menu;
import javax.swing.JOptionPane;


public class IMDBApplication {

    public static void main(String[] args) {
        Menu menu = new Menu();
        JOptionPane.showMessageDialog(null, "Welcome to the IMDB Library Application");
        menu.displayMainMenu();
    }
}
