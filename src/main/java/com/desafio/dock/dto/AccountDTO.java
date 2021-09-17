package com.desafio.dock.dto;

import com.desafio.dock.models.Account;
import com.desafio.dock.models.Person;
import com.desafio.dock.models.Transaction;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.List;

public class AccountDTO {

    private Account account;

    public AccountDTO(){
        this.account = new Account();
    }

    public AccountDTO(Account account){
        this.account = account;
    }

    @JsonIgnore
    public Account getAccount(){
        return account;
    }

    public long getIdAccount(){
        return account.getIdAccount();
    }

    public double getBalance(){
        return account.getBalance();
    }

    public void setBalance(double balance){
        account.setBalance(balance);
    }

    public double getWithdrawalLimit(){
        return account.getWithdrawalLimit();
    }

    public void setWithdrawalLimit(double withdrawalLimit){
        account.setWithdrawalLimit(withdrawalLimit);
    }

    public Boolean getFlagActive(){
        return account.getFlagActive();
    }

    public void setFlagActive(Boolean flagActive){
        account.setFlagActive(flagActive);
    }

    public int getTypeAccount() {
        return account.getTypeAccount();
    }

    public void setTypeAccount(int typeAccount) {
        account.setTypeAccount(typeAccount);
    }

    public Date getCreationDate() {
        return account.getCreationDate();
    }

    public void setCreationDate(Date creationDate) {
        account.setCreationDate(creationDate);
    }

    public PersonDTO getPerson(){
        return new PersonDTO(account.getPerson());
    }

    public void setPerson(Person person){
        account.setPerson(person);
    }

    public List<Transaction> getTransactions(){
        return account.getTransactions();
    }

    public void setTransactions(List<Transaction> transactions){
        account.setTransactions(transactions);
    }








}
