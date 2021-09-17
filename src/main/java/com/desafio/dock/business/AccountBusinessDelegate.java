package com.desafio.dock.business;

import com.desafio.dock.dto.AccountDTO;
import com.desafio.dock.services.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountBusinessDelegate {

    @Autowired
    private AccountService accountService;

    public AccountDTO createAccount(AccountDTO accountDTO, long idPerson){
        return new AccountDTO(this.accountService.createAccount(accountDTO.getAccount(), idPerson));
    }
    public AccountDTO consultBalance(long idAccount){
        return new AccountDTO(this.accountService.consultBalance(idAccount));
    }
    public AccountDTO lockAccount(long idAccount){
        return new AccountDTO(this.accountService.lockAccount(idAccount));
    }

    public AccountDTO findById(long idAccount){
        return new AccountDTO(this.accountService.findById(idAccount));
    }
}
