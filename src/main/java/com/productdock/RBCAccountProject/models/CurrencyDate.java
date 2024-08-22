package com.productdock.RBCAccountProject.models;

import lombok.Data;

import java.util.Date;

@Data
public class CurrencyDate {

    private Date date;

    public CurrencyDate(Date date){
        this.date = date;
    }
}
