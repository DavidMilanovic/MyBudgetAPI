package com.productdock.RBCAccountProject.models;

import lombok.Data;

@Data
public class ConvertedMoney {
    private double convertedMoney;

    public ConvertedMoney(double convertedMoney){
        this.convertedMoney = convertedMoney;
    }
}
