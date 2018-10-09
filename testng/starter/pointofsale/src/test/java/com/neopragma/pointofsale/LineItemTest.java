package com.neopragma.pointofsale;

import org.javamoney.moneta.Money;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LineItemTest {

    @Test
    public void it_computes_the_extended_price_non_taxable() {
        Assert.assertEquals(Helpers.nonTaxableLineItem().extendedPrice(),
                Money.of(10, Constants.USD));
    }

    @Test
    public void it_computes_the_extended_price_with_tax() {
        Assert.assertEquals(Helpers.taxableLineItem().extendedPrice(),
                Money.of(14.105, Constants.USD));
    }

}
