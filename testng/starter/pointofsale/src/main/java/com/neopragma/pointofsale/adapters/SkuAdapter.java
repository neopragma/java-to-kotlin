package com.neopragma.pointofsale.adapters;

import com.neopragma.pointofsale.Sku;

import java.util.HashMap;
import java.util.Map;

/**
 * Fake adapter for a Sku lookup service
 */
public class SkuAdapter {

    private Map<String, Sku> skuData;
    private static final Sku DEFAULT_SKU = new Sku("AAA00000", "Undefined SKU");

    public SkuAdapter() {
        skuData = new HashMap<>();
        skuData.put("BBC12345", new Sku("BBC12345", "ACME Bird Seed"));
        skuData.put("ABC12346", new Sku("ABC12346", "ACME Portable Holes (3-pack)"));
        skuData.put("ABC12347", new Sku("ABC12347", "ACME Dynamite (case)"));
    }

    public Sku find(String code) {
        return skuData.get(code) == null ? DEFAULT_SKU : skuData.get(code);
    }

}
