package com.onthego.cadastro.exceptions;

public class EmailAlreadyExists extends RuntimeException{

    public EmailAlreadyExists() {
        super("Email Already Exists");
    }

    public EmailAlreadyExists(String message) {
        super(message);
    }
}
