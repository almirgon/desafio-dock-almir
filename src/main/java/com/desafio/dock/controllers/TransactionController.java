package com.desafio.dock.controllers;

import com.desafio.dock.models.Transaction;
import com.desafio.dock.services.transaction.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(value = "Controller of transaction")
@RestController
@RequestMapping("/account/{idAccount}/transaction")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @CrossOrigin
    @PostMapping
    @ApiOperation(value = "Method responsible of depositing or withdrawing money of an account")
    public ResponseEntity<Transaction> operation(@PathVariable long idAccount, @RequestParam String type, @RequestParam double value){
        return new ResponseEntity<Transaction>(this.transactionService.operation(type,idAccount,value), HttpStatus.OK);
    }
}
