package com.neopragma.pointofsale

class SaleCaptureState : SaleState() {

    override fun handle(context: SaleContext, event: SaleEventType, payload : Any) {
        when (event) {
            SaleEventType.ADD_LINE_ITEM -> {
                addLineItem(payload)
            }
            SaleEventType.COMPLETE_TRANSACTION -> {
                context.transitionTo(SaleCompleteState())
            }
            SaleEventType.REMOVE_LINE_ITEM -> {
                removeLineItem(payload)
            }
            SaleEventType.CANCEL_TRANSACTION -> {
                context.transitionTo(SaleCancelState())
            }
            else -> {
                context.transitionTo(SaleErrorState())
            }
        }
    }

    private fun addLineItem(payload: Any) {

    }

    private fun removeLineItem(payload: Any) {

    }
}