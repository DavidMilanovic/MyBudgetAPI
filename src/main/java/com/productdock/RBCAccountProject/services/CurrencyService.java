package com.productdock.RBCAccountProject.services;

import com.productdock.RBCAccountProject.models.ConvertedMoney;
import com.productdock.RBCAccountProject.models.CurrencyDate;

import java.util.Date;
import java.util.Set;

public interface CurrencyService {
    Set<String> getAllCurrencies();
    ConvertedMoney convertCurrency(String fromCurrency, String toCurrency, Double amount);
    CurrencyDate getConversionDate(String defaultValue);
}
