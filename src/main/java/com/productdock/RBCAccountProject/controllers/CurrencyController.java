package com.productdock.RBCAccountProject.controllers;

import com.productdock.RBCAccountProject.models.ConvertedMoney;
import com.productdock.RBCAccountProject.models.CurrencyDate;
import com.productdock.RBCAccountProject.services.CurrencyService;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/currencies")
public class CurrencyController {

    private final CurrencyService service;

    public CurrencyController(CurrencyService service) {
        this.service = service;
    }

    @GetMapping
    public Set<String> getAllCurrencies(){
        return service.getAllCurrencies();
    }
    

    @GetMapping("/{defaultCurrency}")
    public CurrencyDate getCurrentExchangeDate(@PathVariable String defaultCurrency){
        return service.getConversionDate(defaultCurrency);
    }

    @GetMapping("/convert/{from}-{to}-{amount}")
    public ConvertedMoney convertCurrency(@PathVariable String from, @PathVariable String to, @PathVariable Double amount){
        return service.convertCurrency(from, to, amount);
    }
}
