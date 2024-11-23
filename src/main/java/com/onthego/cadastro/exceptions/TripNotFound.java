package com.onthego.cadastro.exceptions;

public class TripNotFound extends RuntimeException{

    public TripNotFound() {
        super("Any trip found");
    }

    public TripNotFound(String message) {
        super(message);
    }
}
