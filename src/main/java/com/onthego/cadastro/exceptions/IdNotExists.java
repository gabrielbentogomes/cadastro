package com.onthego.cadastro.exceptions;

public class IdNotExists extends RuntimeException{

    public IdNotExists() {

        super("Id Doesnt Exists");
    }

    public IdNotExists(String message) {
        super(message);
    }
}
