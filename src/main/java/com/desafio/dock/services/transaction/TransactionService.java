package com.desafio.dock.services.transaction;

import com.desafio.dock.models.Transaction;

public interface TransactionService {

    Transaction operation(String typeOperation, long idAccount, double value);
}
