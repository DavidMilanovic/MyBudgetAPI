package com.productdock.RBCAccountProject.services.impl;

import com.productdock.RBCAccountProject.exceptions.NotFoundException;
import com.productdock.RBCAccountProject.models.Account;
import com.productdock.RBCAccountProject.models.AccountRequest;
import com.productdock.RBCAccountProject.models.entities.AccountEntity;
import com.productdock.RBCAccountProject.repositories.AccountEntityRepository;
import com.productdock.RBCAccountProject.services.AccountService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    private final ModelMapper mapper;
    private final AccountEntityRepository repository;
    @PersistenceContext
    private EntityManager entityManager;

    public AccountServiceImpl(ModelMapper mapper, AccountEntityRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    @Override
    public List<Account> findAll() {
        return repository.findAll().stream().map(l->mapper.map(l, Account.class)).toList();
    }

    @Override
    public Account findById(Integer id) throws NotFoundException {
        return mapper.map(repository.findById(id).orElseThrow(NotFoundException::new), Account.class);
    }

    @Override
    public Account insert(AccountRequest accountRequest) throws NotFoundException {
         AccountEntity entity  = mapper.map(accountRequest, AccountEntity.class);
         entity.setId(null);
         entity= repository.saveAndFlush(entity);
         entityManager.refresh(entity);
         return findById(entity.getId());
    }

    @Override
    public Account updateById(Integer id, AccountRequest accountRequest) throws NotFoundException {
        AccountEntity entity  = mapper.map(accountRequest, AccountEntity.class);
        entity.setId(id);
        entity= repository.saveAndFlush(entity);
        entityManager.refresh(entity);
        return findById(entity.getId());
    }

    @Override
    public void deleteById(Integer id) throws NotFoundException {
        findById(id);
        repository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        repository.deleteAll();
    }
}
