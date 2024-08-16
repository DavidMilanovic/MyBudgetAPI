package com.productdock.RBCAccountProject.services;

import java.util.Date;
import java.util.Set;

public interface CurrencyService {
    Set<String> getAllCurrencies();
    Double convertCurrency(String fromCurrency, String toCurrency, Double amount);
    Date getConversionDate(String defaultValue);
}
