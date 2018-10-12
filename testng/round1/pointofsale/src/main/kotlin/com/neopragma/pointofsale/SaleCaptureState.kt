package com.neopragma.pointofsale

import java.lang.IllegalArgumentException

class SaleCaptureState : SaleState() {

    override fun handle(context: SaleContext, event: SaleEventType, payload : Any) {
        when (event) {
            SaleEventType.ADD_LINE_ITEM -> {
                addLineItem(context.transaction, payload)
            }
            SaleEventType.REMOVE_LINE_ITEM -> {
                removeLineItem(context.transaction, payload)
            }
            else -> {
                context.transitionTo(SaleErrorState())
            }
        }
    }

    override fun handle(context: SaleContext, event: SaleEventType) {
        when (event) {
            SaleEventType.COMPLETE_TRANSACTION -> {
                context.transaction.finalSalesPrice()
                context.transitionTo(SaleCompleteState())
            }
            SaleEventType.CANCEL_TRANSACTION -> {
                context.transitionTo(SaleCancelState())
            }
            else -> {
                context.transitionTo(SaleErrorState())
            }
        }
    }

    private fun addLineItem(transaction: Transaction, payload: Any) {
        transaction.addLineItem(payload as? LineItem)
    }

    private fun removeLineItem(transaction: Transaction, payload: Any) {
        var lineItem: LineItem? = payload as? LineItem
        var targetSkuValue: String? = lineItem?.sku?.value()
        var newList: List<LineItem> = transaction.lineItems.filterIndexed { index, lineItem ->
            targetSkuValue != lineItem.sku.value()
        }
        transaction.lineItems = newList
    }

}