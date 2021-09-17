package com.desafio.dock.services.account;

import com.desafio.dock.models.Account;
import com.desafio.dock.models.Transaction;

import java.math.BigDecimal;
import java.util.List;

public interface AccountService {

    Account createAccount(Account account, long idPerson);
    Account consultBalance(long idAccount);
    Account lockAccount(long idAccount);
    Account findById(long idAccount);
    Account updateAccount(Account account);



}
