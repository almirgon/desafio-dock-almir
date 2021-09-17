package com.desafio.dock.exceptions.account;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class AccountInvalidException extends RuntimeException {
    public AccountInvalidException(String s) {
        super(s);
    }
}

