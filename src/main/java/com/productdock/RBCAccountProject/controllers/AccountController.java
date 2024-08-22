package com.productdock.RBCAccountProject.controllers;

import com.productdock.RBCAccountProject.exceptions.NotFoundException;
import com.productdock.RBCAccountProject.models.Account;
import com.productdock.RBCAccountProject.models.AccountRequest;
import com.productdock.RBCAccountProject.models.AvailableMoney;
import com.productdock.RBCAccountProject.services.AccountService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService service;

    public AccountController(AccountService service) {
        this.service = service;
    }

    @GetMapping
    public List<Account> findAll(){return service.findAll();}

    @GetMapping("/{id}")
    public Account findById(@PathVariable Integer id) throws NotFoundException {
        return service.findById(id);
    }

    @GetMapping("/availableMoney/{currency}")
    public AvailableMoney getAvailableMoney(@PathVariable String currency){
        return  service.getAvailableMoney(currency);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Account insertAccount(@RequestBody @Valid AccountRequest account) throws NotFoundException {
        return service.insert(account);
    }

    @PutMapping({"/{id}"})
    public Account updateAccount(@RequestBody @Valid AccountRequest accountRequest, @PathVariable Integer id) throws NotFoundException {
        return service.updateById(id, accountRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(Integer id) throws NotFoundException {
        service.deleteById(id);
    }

    @DeleteMapping
    public void deleteAll(){
        service.deleteAll();
    }
}
