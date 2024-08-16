package com.productdock.RBCAccountProject.services;

import com.productdock.RBCAccountProject.exceptions.InvalidTransactionException;
import com.productdock.RBCAccountProject.exceptions.NotFoundException;
import com.productdock.RBCAccountProject.models.Transaction;
import com.productdock.RBCAccountProject.models.TransactionRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TransactionService {

    List<Transaction> findAll();
    Transaction findById(Integer id) throws NotFoundException;
    Transaction insert(TransactionRequest request) throws NotFoundException, InvalidTransactionException;
    Transaction update(TransactionRequest request, Integer id) throws NotFoundException;
    void delete(Integer id) throws NotFoundException;
    void deleteAll();
}
