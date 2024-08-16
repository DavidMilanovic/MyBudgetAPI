package com.productdock.RBCAccountProject.services;

import com.productdock.RBCAccountProject.exceptions.NotFoundException;
import com.productdock.RBCAccountProject.models.Account;
import com.productdock.RBCAccountProject.models.AccountRequest;

import java.util.List;

public interface AccountService {

    List<Account> findAll();
    Account findById(Integer id) throws NotFoundException;
    Account insert(AccountRequest accountRequest) throws NotFoundException;
    Account updateById(Integer id, AccountRequest accountRequest) throws NotFoundException;
    void deleteById(Integer id) throws NotFoundException;
    void deleteAll();

}
