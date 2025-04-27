package com.example.challenge_mottu.exceptions;

public class UsuarioNotFoundException extends RuntimeException{
    public UsuarioNotFoundException(String cpf) {
        super("U1suário não encontrado com CPF: " + cpf);
    }
}
