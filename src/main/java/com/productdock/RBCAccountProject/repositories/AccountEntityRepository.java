package com.productdock.RBCAccountProject.repositories;

import com.productdock.RBCAccountProject.models.entities.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountEntityRepository extends JpaRepository<AccountEntity, Integer> {

}
