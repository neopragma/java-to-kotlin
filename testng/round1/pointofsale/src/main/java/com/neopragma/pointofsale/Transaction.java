package com.neopragma.pointofsale;

import org.javamoney.moneta.Money;

import javax.money.MonetaryAmount;
import java.util.ArrayList;
import java.util.List;

public class Transaction {

    private MonetaryAmount finalSalesAmount;
    private List<LineItem> lineItems;

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public MonetaryAmount getFinalSalesAmount() {
        return finalSalesAmount;
    }

    Transaction() {
        lineItems = new ArrayList<>(0);
    }

    void addLineItem(LineItem lineItem) {
        lineItems.add(lineItem);
    }
    void removeLineItem(int index) {
        lineItems.remove(index);
    }

    void finalSalesPrice() {
        finalSalesAmount = lineItems.stream()
                .map(LineItem::extendedPrice)
                .reduce(MonetaryAmount::add)
                .orElse(Money.of(0, Constants.USD));
    }
}
