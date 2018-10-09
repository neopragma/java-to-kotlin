package com.neopragma.pointofsale;

import javax.money.MonetaryAmount;

public class LineItem {

    private Sku sku;
    private MonetaryAmount price;
    private Integer quantity;

    public LineItem(Sku sku, MonetaryAmount price, Integer quantity) {
        this.sku = sku;
        this.price = price;
        this.quantity = quantity;
    }

    public MonetaryAmount extendedPrice() {
        MonetaryAmount extendedPrice = price.multiply(quantity);
        if (sku.isTaxable()) {
            extendedPrice = extendedPrice.multiply(1 + Constants.SALES_TAX_RATE);
        }
        return extendedPrice;
    }

    public Sku sku() {
        return sku;
    }

    public MonetaryAmount price() {
        return price;
    }
}
