package com.desafio.dock.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_GATEWAY)
public class EntityNotExistsException extends RuntimeException{
    public EntityNotExistsException(String s){
        super(s);
    }
}
