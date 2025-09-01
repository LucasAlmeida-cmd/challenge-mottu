package com.example.challenge_mottu.exceptions;

public class UsuarioNotFoundException extends RuntimeException{
    public UsuarioNotFoundException(String codigo) {
        super("Usuário não encontrado com Codigo: " + codigo);
    }
}
