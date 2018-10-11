package com.neopragma.pointofsale.adapters

import com.neopragma.pointofsale.Sku

import java.util.HashMap
import java.util.stream.Collectors

/**
 * Fake adapter for a Sku lookup service
 */
class SkuAdapter {

    private val skuData: MutableMap<String, Sku>

    init {
        skuData = HashMap()
        skuData["999BBC12345"] = Sku("BBC12345", "ACME Bird Seed")
        skuData["999ABC12346"] = Sku("ABC12346", "ACME Portable Holes (3-pack)")
        skuData["999ABC12347"] = Sku("ABC12347", "ACME Dynamite (case)")
    }

    fun findByUPC(upc: String): Sku {
        return skuData.getOrDefault(upc, DEFAULT_SKU)
    }

    @Deprecated(
        "This method will be removed",
        replaceWith = ReplaceWith(
                "findByUPC(upc)"))
    fun find(code: String): Sku {
        return findBySKU(code)
    }

    fun findBySKU(code: String): Sku {
        val sku = skuData.entries.stream()
                .filter { x -> code == x.value.value() }
                .collect(Collectors.toList())
        return sku[0].value
    }

    companion object {
        private val DEFAULT_SKU = Sku("AAA00000", "Undefined SKU")
    }

}
