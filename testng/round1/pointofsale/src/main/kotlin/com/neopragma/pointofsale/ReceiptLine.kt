package com.neopragma.pointofsale

import org.javamoney.moneta.format.CurrencyStyle
import java.util.*
import javax.money.MonetaryAmount
import javax.money.format.AmountFormatQueryBuilder
import javax.money.format.MonetaryAmountFormat
import javax.money.format.MonetaryFormats

class ReceiptLine(description: String, quantity: Int, unitPrice: MonetaryAmount) {

    var description: String = Constants.EMPTY_STRING
    var quantity: String = Constants.EMPTY_STRING
    var unitPrice: String = Constants.EMPTY_STRING
    var taxAmount: String = Constants.EMPTY_STRING
    var extendedPrice: String = Constants.EMPTY_STRING
    var extendedPriceOriginal: MonetaryAmount = unitPrice

    val format: MonetaryAmountFormat = MonetaryFormats.getAmountFormat(
            AmountFormatQueryBuilder.of(Locale.US)
                    .set(CurrencyStyle.SYMBOL)
                    .build()
    )

    init {
        this.description = description
        setQuantity(quantity)
        setUnitPrice(unitPrice)
        setExtendedPrice(quantity, unitPrice)
    }


    fun setQuantity(value: Int) {
        this.quantity = Integer.toString(value)
    }

    fun setUnitPrice(value: MonetaryAmount) {
        this.unitPrice = format.format(value);
    }

    fun setExtendedPrice(quantity: Int, unitPrice: MonetaryAmount) {
        this.extendedPriceOriginal = unitPrice.multiply(quantity)
        this.extendedPrice = format.format(extendedPriceOriginal)
    }



}