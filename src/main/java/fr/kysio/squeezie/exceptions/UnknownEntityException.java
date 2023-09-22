package fr.kysio.squeezie.exceptions;

import org.springframework.http.HttpStatus;

public class UnknownEntityException extends ApplicationException{

    public UnknownEntityException(String message) {
        super("NOT_FOUND", message, HttpStatus.NOT_FOUND);
    }

}
