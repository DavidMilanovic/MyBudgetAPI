package com.productdock.RBCAccountProject.models;

import lombok.Data;

@Data
public class TransactionRequest {

    private String description;
    private double amount;
    private String currency;
    private Integer accountId;

}
