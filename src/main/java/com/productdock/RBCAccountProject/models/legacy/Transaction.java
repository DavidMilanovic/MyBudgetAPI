package com.productdock.RBCAccountProject.models.legacy;

import jakarta.xml.bind.annotation.XmlElement;

public class Transaction {
    private String description;
    private Amount amount;

    @XmlElement(name = "Description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlElement(name = "Amount")
    public Amount getAmount() {
        return amount;
    }

    public void setAmount(Amount amount) {
        this.amount = amount;
    }
}
