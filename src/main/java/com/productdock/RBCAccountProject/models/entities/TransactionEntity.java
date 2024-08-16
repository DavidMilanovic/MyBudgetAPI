package com.productdock.RBCAccountProject.models.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "transaction")
public class TransactionEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_seq")
        @SequenceGenerator(name = "transaction_seq", sequenceName = "transaction_sequence", allocationSize = 1)
        private Integer Id;

        @Basic
        @Column(name = "description", nullable = false, length = 150)
        private String description;

        @Basic
        @Column(name = "amount", nullable = false)
        private double amount;

        @Basic
        @Column(name = "currency", nullable = false, length = 5)
        private String currency;

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "account_id", nullable = false)
        private AccountEntity account;

        // Getters and setters
}
