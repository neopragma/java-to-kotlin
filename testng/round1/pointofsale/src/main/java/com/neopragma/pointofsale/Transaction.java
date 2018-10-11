package com.neopragma.pointofsale;

import org.javamoney.moneta.Money;

import javax.money.MonetaryAmount;
import java.util.ArrayList;
import java.util.List;

class Transaction {

    private List<LineItem> lineItems;

    Transaction() {
        lineItems = new ArrayList<>(0);
    }

    void addLineItem(LineItem lineItem) {
        lineItems.add(lineItem);
    }

    MonetaryAmount finalSalesPrice() {
        return lineItems.stream()
                .map(LineItem::extendedPrice)
                .reduce(MonetaryAmount::add)
                .orElse(Money.of(0, Constants.USD));
    }
}
