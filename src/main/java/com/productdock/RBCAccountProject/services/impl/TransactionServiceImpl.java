package com.productdock.RBCAccountProject.services.impl;

import com.productdock.RBCAccountProject.exceptions.InvalidTransactionException;
import com.productdock.RBCAccountProject.exceptions.NotFoundException;
import com.productdock.RBCAccountProject.models.Account;
import com.productdock.RBCAccountProject.models.AccountRequest;
import com.productdock.RBCAccountProject.models.Transaction;
import com.productdock.RBCAccountProject.models.TransactionRequest;
import com.productdock.RBCAccountProject.models.entities.TransactionEntity;
import com.productdock.RBCAccountProject.repositories.TransactionEntityRepository;
import com.productdock.RBCAccountProject.services.AccountService;
import com.productdock.RBCAccountProject.services.CurrencyService;
import com.productdock.RBCAccountProject.services.TransactionService;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {

    private final TransactionEntityRepository repository;
    private final ModelMapper mapper;
    private final AccountService accountService;
    private final EntityManager entityManager;
    private final CurrencyService currencyService;

    public TransactionServiceImpl(TransactionEntityRepository repository, ModelMapper mapper, AccountService accountService, EntityManager entityManager, CurrencyService currencyService) {
        this.repository = repository;
        this.mapper = mapper;
        this.accountService = accountService;
        this.entityManager = entityManager;
        this.currencyService = currencyService;
    }

    @Override
    public List<Transaction> findAll() {
        return repository.findAll().stream().map(l->mapper.map(l, Transaction.class)).toList();
    }

    @Override
    public Transaction findById(Integer id) throws NotFoundException {
        return mapper.map(repository.findById(id).orElseThrow(NotFoundException::new), Transaction.class);
    }

    @Override
    public Transaction insert(TransactionRequest request) throws NotFoundException, InvalidTransactionException {
        Account account = accountService.findById(request.getAccountId());
        processTransaction(account, request);

        TransactionEntity entity  = mapper.map(request, TransactionEntity.class);
        entity.setId(null);
        entity= repository.saveAndFlush(entity);
        entityManager.refresh(entity);
        return findById(entity.getId());
    }

    private void processTransaction(Account account, TransactionRequest request) throws InvalidTransactionException, NotFoundException {
        Double requestedAmount = request.getAmount();
        String requestedCurrency = request.getCurrency().toLowerCase();

        if (account.getCurrency().toLowerCase().equals(requestedCurrency)) {
            if (account.getBalance() >= requestedAmount) {
                account.setBalance(account.getBalance() - requestedAmount);
            } else {
                throw new InvalidTransactionException();
            }
        } else {
            Double exchangedAmount =  currencyService.convertCurrency(requestedCurrency, account.getCurrency(), requestedAmount);
            if (account.getBalance() >= exchangedAmount) {
                account.setBalance(account.getBalance() - exchangedAmount);
            } else {
                throw new InvalidTransactionException();
            }
        }

        accountService.updateById(account.getId(), mapper.map(account, AccountRequest.class));
    }


    @Override
    public Transaction update(TransactionRequest request, Integer id) throws NotFoundException {
        TransactionEntity entity  = mapper.map(request, TransactionEntity.class);
        entity.setId(id);
        entity= repository.saveAndFlush(entity);
        entityManager.refresh(entity);
        return findById(entity.getId());
    }

    @Override
    public void delete(Integer id) throws NotFoundException {
        findById(id);
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
