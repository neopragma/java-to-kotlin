package com.neopragma.pointofsale;

import javax.money.MonetaryAmount;
import java.util.ArrayList;
import java.util.List;

public class Transaction {

    private List<LineItem> lineItems;

    Transaction() {
        lineItems = new ArrayList<LineItem>(0);
    }

    void addLineItem(LineItem lineItem) {
        lineItems.add(lineItem);
    }

    MonetaryAmount finalSalesPrice() {
        return lineItems.stream()
                .map(LineItem::extendedPrice)
                .reduce(MonetaryAmount::add)
                .get();
    }
}
