package com.productdock.RBCAccountProject.repositories;

import com.productdock.RBCAccountProject.models.entities.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionEntityRepository extends JpaRepository<TransactionEntity, Integer> {
}
