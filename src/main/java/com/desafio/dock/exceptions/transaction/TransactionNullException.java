package com.desafio.dock.exceptions.transaction;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class TransactionNullException extends RuntimeException {
    public TransactionNullException(String s){
        super(s);
    }
}
