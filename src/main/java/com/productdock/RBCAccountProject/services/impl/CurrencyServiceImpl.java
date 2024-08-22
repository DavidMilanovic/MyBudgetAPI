package com.productdock.RBCAccountProject.services.impl;

import com.productdock.RBCAccountProject.models.ConvertedMoney;
import com.productdock.RBCAccountProject.models.CurrencyDate;
import com.productdock.RBCAccountProject.services.CurrencyService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${exchange.api.currencies.url}")
    private String currenciesUrl;

    @Value("${exchange.api.conversion.url}")
    private String conversionUrlTemplate;


    @Override
    public Set<String> getAllCurrencies() {
        Map<String, String> currencies = restTemplate.getForObject(currenciesUrl, Map.class);
        if (currencies != null) {
            return currencies.keySet();
        }
        return new HashSet<>();
    }

    @Override
    public ConvertedMoney convertCurrency(String fromCurrency, String toCurrency, Double amount) {
        String fromCurrencyLower = fromCurrency.toLowerCase();
        String toCurrencyLower = toCurrency.toLowerCase();

        String conversionUrl = conversionUrlTemplate.replace("{currency}", fromCurrencyLower);

        Map<String, Object> conversionData = restTemplate.getForObject(conversionUrl, Map.class);

        if (conversionData != null && conversionData.containsKey(fromCurrencyLower)) {
            Map<String, Double> conversionRates = (Map<String, Double>) conversionData.get(fromCurrencyLower);

            if (conversionRates.containsKey(toCurrencyLower)) {
                Double conversionRate = conversionRates.get(toCurrencyLower);
                return new ConvertedMoney(amount * conversionRate);
            }
        }

        throw new IllegalArgumentException("Currency conversion failed. Invalid currencies or data not available.");
    }

    @Override
    public CurrencyDate getConversionDate(String defaultValue) {
        String conversionUrl = conversionUrlTemplate.replace("{currency}", defaultValue.toLowerCase());

        Map<String, Object> conversionData = restTemplate.getForObject(conversionUrl, Map.class);

        if (conversionData != null && conversionData.containsKey("date")) {
            String dateStr = (String) conversionData.get("date");

            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                return new CurrencyDate(dateFormat.parse(dateStr));
            } catch (ParseException e) {
                throw new IllegalArgumentException("Failed to parse conversion date", e);
            }
        }

        throw new IllegalArgumentException("Conversion date not available.");
    }


}
