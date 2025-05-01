package com.example.challenge_mottu.exceptions;

public class PatioNotFoundException extends RuntimeException{
    public PatioNotFoundException(String ident) {
        super("Patio não encontrado com chassi: " + ident);
    }
}
