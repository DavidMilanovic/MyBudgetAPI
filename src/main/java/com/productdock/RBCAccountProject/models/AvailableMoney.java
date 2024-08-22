package com.productdock.RBCAccountProject.models;

import lombok.Data;

@Data
public class AvailableMoney {
    private double availableMoney;

    public AvailableMoney(double availableMoney){
        this.availableMoney = availableMoney;
    }
}
