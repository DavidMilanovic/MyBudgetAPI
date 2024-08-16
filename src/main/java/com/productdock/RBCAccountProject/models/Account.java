package com.productdock.RBCAccountProject.models;

import lombok.Data;

@Data
public class Account {
    private int Id;
    private String name;
    private String currency;
    private double balance;
}
