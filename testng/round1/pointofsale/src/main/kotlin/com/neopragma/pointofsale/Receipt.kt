package com.neopragma.pointofsale

import org.javamoney.moneta.format.CurrencyStyle
import java.util.*
import javax.money.MonetaryAmount
import javax.money.format.AmountFormatQueryBuilder
import javax.money.format.MonetaryAmountFormat
import javax.money.format.MonetaryFormats

class Receipt {

    var receiptLines: MutableList<ReceiptLine> = ArrayList()

    var finalSaleAmount: String = Constants.EMPTY_STRING

    val format: MonetaryAmountFormat = MonetaryFormats.getAmountFormat(
            AmountFormatQueryBuilder.of(Locale.US)
                    .set(CurrencyStyle.SYMBOL)
                    .build()
    )

    fun addReceiptLine(line: ReceiptLine) {
        receiptLines.add(line)
    }

    fun formatFinalSaleAmount(value: MonetaryAmount) {
        this.finalSaleAmount = format.format(value)
    }

}