package com.desafio.dock.controllers;

import com.desafio.dock.business.AccountBusinessDelegate;
import com.desafio.dock.dto.AccountDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(value = "Controller of Account")
@RestController
@RequestMapping(value = "/account")
public class AccountController {

    @Autowired
    private AccountBusinessDelegate accountBusinessDelegate;


    @CrossOrigin
    @ApiOperation(value = "Method responsible for creating a bank account")
    @PostMapping("/{idPerson}")
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO, @PathVariable long idPerson){
        return new ResponseEntity<AccountDTO>(this.accountBusinessDelegate.createAccount(accountDTO,idPerson),HttpStatus.CREATED);
    };

    @CrossOrigin
    @ApiOperation(value = "Method responsible for consulting the balance of an account")
    @GetMapping("/consultBalance/{idAccount}")
    public ResponseEntity<Object> consultBalance(@PathVariable long idAccount){
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("message", "Account balance: " + idAccount);
        response.put("balance",  this.accountBusinessDelegate.consultBalance(idAccount).getBalance());
        return new ResponseEntity<Object>(response,HttpStatus.OK);
    };

    @CrossOrigin
    @ApiOperation(value = "Method responsible for blocking an account")
    @PostMapping("/lock/{idAccount}")
    public ResponseEntity<Object> lockAccount(@PathVariable long idAccount){
        Map<String, Object> response = new HashMap<String, Object>();
        response.put("message", "Account blocked successfully");
        response.put("account", this.accountBusinessDelegate.lockAccount(idAccount));
        return new ResponseEntity<Object>(response,HttpStatus.OK);
    };

    @CrossOrigin
    @ApiOperation(value = "Method responsible for consulting the transaction statement of an account")
    @GetMapping("/extract/{idAccount}")
    public ResponseEntity<Object> getExtract(@PathVariable long idAccount){
        Map<String, Object> response = new HashMap<String, Object>();
        AccountDTO account = this.accountBusinessDelegate.findById(idAccount);
        response.put("message", "Transaction statement: Account " + idAccount);
        response.put("balance", account.getBalance());
        response.put("flagActive", account.getFlagActive());
        response.put("transactions", account.getTransactions());
        return new ResponseEntity<Object>(response,HttpStatus.OK);
    }

}
