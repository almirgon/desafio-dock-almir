package com.desafio.dock.validators;

import com.desafio.dock.exceptions.account.AccountInvalidException;
import com.desafio.dock.exceptions.account.AccountNullException;
import com.desafio.dock.models.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountValidator {
    public void ValidAccount(Account account){
        if(account == null) throw new AccountNullException("Account cannot be null");
        if(account.getTypeAccount() <= 0) throw new AccountInvalidException("Type Account cannot be less or equal than zero");
        if(account.getWithdrawalLimit() <= 0) throw new AccountInvalidException("Withdrawal limit cannot be less or equal than zero");
    }
}
