package com.desafio.dock.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.List;

@Entity
@Table
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idAccount;

    @Column
    private double balance;

    @Column
    @NotNull
    private double withdrawalLimit;

    @Column
    private Boolean flagActive;

    @Column
    @NotNull
    private int typeAccount;

    @Column
    private Date creationDate;

    @OneToOne
    private Person person;

    @ManyToMany
    private List<Transaction> transactions;

    @JsonIgnore
    private double withdrawls;


    public Account() {
        this.transactions = new ArrayList<>();
    }

    public long getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(long idAccount) {
        this.idAccount = idAccount;
    }

    public double getBalance() {
        setBalance(0);
        for (Transaction transaction:transactions) {
            if(transaction.getOperationType().getOperation().equals("DEPOSIT")){
                balance += transaction.getValue();
            }if(transaction.getOperationType().getOperation().equals("WITHDRAW")){
                balance -= transaction.getValue();
            }
        }
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getWithdrawalLimit() {
        return withdrawalLimit;
    }

    public void setWithdrawalLimit(double withdrawalLimit) {
        this.withdrawalLimit = withdrawalLimit;
    }

    public Boolean getFlagActive() {
        return flagActive;
    }

    public void setFlagActive(Boolean flagActive) {
        this.flagActive = flagActive;
    }

    public int getTypeAccount() {
        return typeAccount;
    }

    public void setTypeAccount(int typeAccount) {
        this.typeAccount = typeAccount;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<Transaction> transactions) {
        this.transactions = transactions;
    }

    public void addTransaction(Transaction transaction){
        this.transactions.add(transaction);
    }

    public double getWithdrawls() {
        setWithdrawls(0);
        for (Transaction transaction:transactions) {
           if(transaction.getOperationType().getOperation().equals("WITHDRAW")){
               withdrawls += transaction.getValue();
            }
        }
        return withdrawls;
    }

    public void setWithdrawls(double withdrawls) {
        this.withdrawls = withdrawls;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return idAccount == account.idAccount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAccount);
    }
}
