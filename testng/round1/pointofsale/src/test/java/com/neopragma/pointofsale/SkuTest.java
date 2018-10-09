package com.neopragma.pointofsale;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

public class SkuTest {

    private Sku sku;

    @Test
    public void it_accepts_a_properly_formed_sku() {
        new Sku("ABC12345", "desc");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void it_rejects_an_improperly_formed_sku() {
        new Sku("567XRT", "desc");
    }

    @Test
    public void it_returns_the_value_and_description_of_the_sku() {
        sku = new Sku("BCB98013", "Description");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(sku.value(), "BCB98013");
        softAssert.assertEquals(sku.description(), "Description");
        softAssert.assertAll();
    }

    @Test
    public void it_recognizes_a_taxable_sku() {
        sku = new Sku("ABC12345", "desc");
        Assert.assertTrue(sku.isTaxable());
    }

    @Test
    public void it_recognizes_a_nontaxable_sku() {
        sku = new Sku("BAA12345", "desc");
        Assert.assertFalse(sku.isTaxable());
    }
}
