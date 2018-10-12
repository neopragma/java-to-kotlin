package com.neopragma.pointofsale;

import javax.money.MonetaryAmount;

class LineItem {

    Sku sku;
    MonetaryAmount price;
    Integer quantity;

    LineItem(Sku sku, MonetaryAmount price, Integer quantity) {
        this.sku = sku;
        this.price = price;
        this.quantity = quantity;
    }

    MonetaryAmount extendedPrice() {
        MonetaryAmount extendedPrice = price.multiply(quantity);
        if (sku.isTaxable()) {
            extendedPrice = extendedPrice.multiply(1 + Constants.SALES_TAX_RATE);
        }
        return extendedPrice;
    }

    public Sku getSku() {
        return sku;
    }
}
