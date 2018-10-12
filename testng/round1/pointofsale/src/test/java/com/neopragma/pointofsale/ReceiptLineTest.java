package com.neopragma.pointofsale;

import org.javamoney.moneta.Money;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static org.testng.Assert.assertEquals;

public class ReceiptLineTest {

    @Test
    public void initializeReceiptLine() {
        SoftAssert softAssert = new SoftAssert();
        ReceiptLine line = new ReceiptLine(
                "This is a description",
                 15,
                Money.of(1956.866, Constants.USD));
        softAssert.assertEquals(line.getDescription(), "This is a description");
        softAssert.assertEquals(line.getQuantity(), "15");
        softAssert.assertEquals(line.getUnitPrice(), "$1,956.87");
        softAssert.assertAll();
    }
}
