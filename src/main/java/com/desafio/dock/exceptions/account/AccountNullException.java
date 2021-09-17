package com.desafio.dock.exceptions.account;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AccountNullException extends RuntimeException {
    public AccountNullException(String s) {
        super(s);
    }
}


