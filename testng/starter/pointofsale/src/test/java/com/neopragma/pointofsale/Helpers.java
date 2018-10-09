package com.neopragma.pointofsale;

import org.javamoney.moneta.Money;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class Helpers {

    public static Sku taxableSku = mock(Sku.class);
    public static Sku nonTaxableSku = mock(Sku.class);

    public static LineItem nonTaxableLineItem() {
        when(nonTaxableSku.value()).thenReturn("BBC12345");
        when(nonTaxableSku.isTaxable()).thenReturn(false);
        return new LineItem(
                nonTaxableSku, Money.of(5.00, Constants.USD), 2);
    }

    public static LineItem taxableLineItem() {
        when(taxableSku.value()).thenReturn("ABC12345");
        when(taxableSku.isTaxable()).thenReturn(true);
        return new LineItem(
                taxableSku, Money.of(6.50, Constants.USD), 2);
    }

}
