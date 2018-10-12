package com.neopragma.pointofsale;

import javax.money.MonetaryAmount;

public class Sku {
    private String value;
    private String description;
    private MonetaryAmount price;
    private final String pattern = "^[A-C]{3}\\d{5}$";

    public Sku(String value, MonetaryAmount price, String description) {
        if (value.matches(pattern)) {
            this.value = value;
            this.price = price;
            this.description = description;
        } else {
            throw new IllegalArgumentException("SKU must have the form XXX99999 where XXX can contain A, B, or C");
        }
    }

    public String value() {
        return value;
    }

    String description() {
        return description;
    }

    MonetaryAmount price() {
        return price; }

    boolean isTaxable() {
        return value.startsWith("A");
    }
}
