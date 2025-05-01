package com.example.challenge_mottu.exceptions;

public class VagaException extends RuntimeException{
    public VagaException(String ident) {
        super(ident);
    }
}
