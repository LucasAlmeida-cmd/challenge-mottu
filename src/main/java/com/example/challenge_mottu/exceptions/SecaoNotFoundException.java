package com.example.challenge_mottu.exceptions;

public class SecaoNotFoundException extends RuntimeException{
    public SecaoNotFoundException(String ind) {
        super("Usuário não encontrado com CPF: " + ind);
    }
}
