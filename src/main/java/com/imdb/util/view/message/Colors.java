package com.imdb.util.view.message;

public enum Colors {
    RESET("\u001B[0m"),
    RED("\u001B[31m"),
    GREEN("\u001B[32m"),
    //BLUE("\u001B[34m"),
    YELLOW("\u001B[33m");

    private final String codeANSI;

    Colors(String codeANSI) {
        this.codeANSI = codeANSI;
    }

    @Override
    public String toString() {
        return codeANSI;
    }
}
