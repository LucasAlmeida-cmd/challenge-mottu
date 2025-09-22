package com.example.challenge_mottu.exceptions;

public class MotoqueiroEmailException extends RuntimeException{
    public MotoqueiroEmailException(String email) {
        super(email);
    }
}
