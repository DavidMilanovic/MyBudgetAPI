package com.productdock.RBCAccountProject.models;
import lombok.Data;


@Data
public class Transaction {

    private int ID;
    private String description;
    private double amount;
    private String currency;
    private String accountName;
}
