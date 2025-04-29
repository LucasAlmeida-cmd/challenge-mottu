package com.example.challenge_mottu.exceptions;

public class MotoNotFoundException extends RuntimeException{
    public MotoNotFoundException(String chassi) {
        super("Moto não encontrado com chassi: " + chassi);
    }
}
