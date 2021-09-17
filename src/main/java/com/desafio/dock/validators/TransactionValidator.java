package com.desafio.dock.validators;

import com.desafio.dock.exceptions.transaction.TransactionInvalidException;
import com.desafio.dock.exceptions.transaction.TransactionNullException;
import com.desafio.dock.models.Transaction;
import org.springframework.stereotype.Component;

@Component
public class TransactionValidator {
    public void validTransaction(Transaction transaction){
        if(transaction == null) throw new TransactionNullException("Transaction cannot be null");
        if(transaction.getOperationType() == null) throw new TransactionInvalidException("Operation not allowed");
        if(transaction.getValue() <= 0) throw new TransactionInvalidException("Value cannot be less or equal than zero");
    }
}
