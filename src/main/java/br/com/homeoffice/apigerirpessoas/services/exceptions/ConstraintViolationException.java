package br.com.homeoffice.apigerirpessoas.services.exceptions;

public class ConstraintViolationException extends RuntimeException{
    public ConstraintViolationException(String message) {
        super(message);
    }
}
