package com.neopragma.pointofsale;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.neopragma.pointofsale.adapters.SkuAdapter;

public class SkuLookupIT {

    private Sku sku;
    private SkuAdapter service = new SkuAdapter();

    @Test
    public void it_finds_a_valid_sku() {
        sku = service.find("ABC12346");
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(sku.value(), "ABC12346");
        softAssert.assertEquals(sku.description(), "ACME Portable Holes (3-pack)");
        softAssert.assertAll();
    }

}
