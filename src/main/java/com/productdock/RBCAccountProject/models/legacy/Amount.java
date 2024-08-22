package com.productdock.RBCAccountProject.models.legacy;

import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlValue;

public class Amount {
    private String currency;
    private double value;

    @XmlAttribute(name = "currency")
    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @XmlValue
    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
