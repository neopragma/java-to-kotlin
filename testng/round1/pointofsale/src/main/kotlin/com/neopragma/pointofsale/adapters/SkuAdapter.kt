package com.neopragma.pointofsale.adapters

import com.neopragma.pointofsale.Constants
import com.neopragma.pointofsale.Sku
import org.javamoney.moneta.Money

import java.util.HashMap
import java.util.stream.Collectors

/**
 * Fake adapter for a Sku lookup service
 */
class SkuAdapter {

    private val skuData: MutableMap<String, Sku>

    init {
        skuData = HashMap()
        skuData["999BBC12345"] = Sku("BBC12345", Money.of(11, Constants.USD), "ACME Bird Seed")
        skuData["999ABC12346"] = Sku("ABC12346", Money.of(15.75, Constants.USD), "ACME Portable Holes (3-pack)")
        skuData["999ABC12347"] = Sku("ABC12347", Money.of(19.50, Constants.USD),"ACME Dynamite (case)")
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
        private val DEFAULT_SKU = Sku("AAA00000", Money.of(1, Constants.USD), "Undefined SKU")
    }

}
