package com.desafio.dock.services.account;

import com.desafio.dock.exceptions.EntityNotExistsException;
import com.desafio.dock.models.Account;
import com.desafio.dock.models.Person;
import com.desafio.dock.models.Transaction;
import com.desafio.dock.repositories.AccountRepository;
import com.desafio.dock.services.person.PersonService;
import com.desafio.dock.validators.AccountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class AccountBean implements AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private PersonService personService;

    @Autowired
    private AccountValidator accountValidator;

    @Override
    public Account createAccount(Account account, long idPerson) {
        accountValidator.ValidAccount(account);
        Person person = this.personService.getPersonByID(idPerson);
        if(person == null){
            throw new EntityNotExistsException("Person not exists");
        }
        Date data = new Date();
        account.setCreationDate(data);
        account.setBalance(0);
        account.setFlagActive(true);
        account.setPerson(person);
        return this.accountRepository.save(account);
    }

    @Override
    public Account consultBalance(long idAccount) {
        Account account = this.accountRepository.getAccountByIdAccount(idAccount);
        if(account == null){
            throw new EntityNotExistsException("Account does not exist");
        }
        return account;
    }


    @Override
    public Account lockAccount(long idAccount) {
        Account account = this.accountRepository.getAccountByIdAccount(idAccount);
        if(account == null){
            throw new EntityNotExistsException("Account does not exist");
        }
        account.setFlagActive(false);
        return this.accountRepository.save(account);
    }

    @Override
    public Account findById(long idAccount) {
        Account account = this.accountRepository.getAccountByIdAccount(idAccount);
        if(account == null){
            throw new EntityNotExistsException("Account does not exist");
        }
        return account;
    }

    @Override
    public Account updateAccount(Account account) {
        if(account == null){
            throw new EntityNotExistsException("Account does not exist");
        }
        return this.accountRepository.save(account);
    }


}
