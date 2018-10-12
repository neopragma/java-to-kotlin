package com.neopragma.pointofsale;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.javamoney.moneta.Money;
import javax.money.MonetaryAmount;

public class TransactionTest {

    @Test
    public void it_calculates_total_price_with_1_line_item_non_taxable() {
        Transaction transaction = new Transaction();
        transaction.addLineItem(Helpers.nonTaxableLineItem());
        MonetaryAmount totalPrice = Money.of(10, "USD");
        transaction.finalSalesPrice();
        Assert.assertEquals(transaction.getFinalSalesAmount(), totalPrice);
    }

    @Test
    public void it_calculates_total_price_with_2_line_items_taxable() {
        Transaction transaction = new Transaction();
        transaction.addLineItem(Helpers.taxableLineItem());
        transaction.addLineItem(Helpers.taxableLineItem());
        MonetaryAmount totalPrice = Money.of(28.21, Constants.USD);
        transaction.finalSalesPrice();
        Assert.assertEquals(transaction.getFinalSalesAmount(), totalPrice);
    }

    @Test
    public void it_calculates_total_price_with_a_mixture_of_taxable_and_non_taxable_items() {
        Transaction transaction = new Transaction();
        transaction.addLineItem(Helpers.nonTaxableLineItem());
        transaction.addLineItem(Helpers.taxableLineItem());
        MonetaryAmount totalPrice = Money.of(24.105, Constants.USD);
        transaction.finalSalesPrice();
        Assert.assertEquals(transaction.getFinalSalesAmount(), totalPrice);
    }

}