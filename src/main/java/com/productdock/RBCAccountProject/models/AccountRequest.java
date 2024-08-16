package com.productdock.RBCAccountProject.models;

import lombok.Data;

@Data
public class AccountRequest {
    private String name;
    private String currency;
    private double balance;
}
