package com.neopragma.pointofsale

import org.javamoney.moneta.Money
import java.lang.IllegalArgumentException
import javax.money.MonetaryAmount

class SaleCompleteState : SaleState() {

    override fun handle(context: SaleContext, event: SaleEventType) {
        when (event) {
            SaleEventType.CLOSE_TRANSACTION -> {
                prepareReceipt()
                context.transitionTo(SaleIdleState())
            }
            SaleEventType.CANCEL_TRANSACTION -> {
                context.transitionTo(SaleCancelState())
            }
            else -> {
                context.transitionTo(SaleErrorState())
            }
        }
    }

    override fun handle(context: SaleContext, event: SaleEventType, payload: Any) {
        throw IllegalArgumentException("SaleCompleteState.handle() cannot accept a payload argument.")
    }

    fun prepareReceipt() {
        var totalPrice: MonetaryAmount = Money.of(0, Constants.USD)
        context.transaction.lineItems.forEach {
            context.receipt.addReceiptLine(
                ReceiptLine(
                    it.sku.description(),
                    it.quantity,
                    it.price
                )
            )
            totalPrice.add(it.price)
        }
        context.receipt.formatFinalSaleAmount(totalPrice)
    }

}