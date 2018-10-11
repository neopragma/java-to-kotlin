package com.neopragma.pointofsale;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import com.neopragma.pointofsale.adapters.SkuAdapter;

public class SkuLookupIT {

    private Sku sku;
    private SkuAdapter service = new SkuAdapter();

    @Test
    public void find_finds_a_valid_sku() {
        sku = service.find("ABC12346");
        assertSkuFound("ABC12346", "ACME Portable Holes (3-pack)");
    }

    @Test
    public void findBySKU_finds_a_valid_sku() {
        sku = service.findBySKU("ABC12346");
        assertSkuFound("ABC12346", "ACME Portable Holes (3-pack)");
    }

    @Test
    public void findByUPC_finds_a_valid_sku() {
        sku = service.findByUPC("999ABC12346");
        assertSkuFound("ABC12346", "ACME Portable Holes (3-pack)");
    }

    private void assertSkuFound(String expectedSku, String expectedDescription) {
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(sku.value(), expectedSku);
        softAssert.assertEquals(sku.description(), expectedDescription);
        softAssert.assertAll();
    }

}
