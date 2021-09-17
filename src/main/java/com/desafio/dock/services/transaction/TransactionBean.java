package com.desafio.dock.services.transaction;

import com.desafio.dock.exceptions.transaction.TransactionInvalidException;
import com.desafio.dock.models.Account;
import com.desafio.dock.models.OperationType;
import com.desafio.dock.models.Transaction;
import com.desafio.dock.repositories.TransactionRepository;
import com.desafio.dock.services.account.AccountService;
import com.desafio.dock.validators.TransactionValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TransactionBean implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionValidator transactionValidator;

    @Override
    public Transaction operation(String typeOperation, long idAccount, double value) {
        Transaction transaction = new Transaction();
        Date date = new Date();
        Account account = this.accountService.findById(idAccount);
        if(account.getFlagActive() == false){
            throw new TransactionInvalidException("The account is locked for operations");
        }
        if(typeOperation.toUpperCase().equals(OperationType.DEPOSIT.getOperation())){
            transaction.setOperationType(OperationType.DEPOSIT);
        }
        if(typeOperation.toUpperCase().equals(OperationType.WITHDRAW.getOperation())){
            if(value <= account.getBalance() && account.getWithdrawls() <= account.getWithdrawalLimit()){
                transaction.setOperationType(OperationType.WITHDRAW);
            }else if(account.getWithdrawls() > account.getWithdrawalLimit()){
                throw new TransactionInvalidException("Daily withdrawal limits reached! Try again in 24 hours");
            }else{
                throw new TransactionInvalidException("Non-sufficient funds");
            }

        }
        transaction.setDateTransaction(date);
        transaction.setValue(value);
        this.transactionValidator.validTransaction(transaction);
        this.transactionRepository.save(transaction);
        account.addTransaction(transaction);
        this.accountService.updateAccount(account);
        return transaction;
    }
}
