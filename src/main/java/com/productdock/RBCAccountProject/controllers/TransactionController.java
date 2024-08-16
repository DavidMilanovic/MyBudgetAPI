package com.productdock.RBCAccountProject.controllers;

import com.productdock.RBCAccountProject.exceptions.InvalidTransactionException;
import com.productdock.RBCAccountProject.exceptions.NotFoundException;
import com.productdock.RBCAccountProject.models.Transaction;
import com.productdock.RBCAccountProject.models.TransactionRequest;
import com.productdock.RBCAccountProject.services.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;
    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<Transaction> findAll(){
       return transactionService.findAll();
    }

    @GetMapping("/{id}")
    public Transaction findById(@PathVariable Integer id) throws NotFoundException {
        return transactionService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Transaction insertTransaction(@RequestBody TransactionRequest request) throws NotFoundException, InvalidTransactionException {
        return transactionService.insert(request);
    }

    @PutMapping("/{id}")
    public Transaction updateTransaction(@RequestBody TransactionRequest request, @PathVariable Integer id) throws NotFoundException{
        return transactionService.update(request,id);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) throws NotFoundException {
        transactionService.delete(id);
    }

    @DeleteMapping
    public void deleteAll(){
        transactionService.deleteAll();
    }
}
