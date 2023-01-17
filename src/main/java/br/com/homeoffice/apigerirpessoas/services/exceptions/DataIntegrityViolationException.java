package br.com.homeoffice.apigerirpessoas.services.exceptions;

//classe criada para retornar uma exception de DataIntegraty
public class DataIntegrityViolationException extends RuntimeException{
    public DataIntegrityViolationException(String message) {
        super(message);
    }
}
