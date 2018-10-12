package com.neopragma.pointofsale

class SaleCompleteState : SaleState() {

    override fun handle(context: SaleContext, event: SaleEventType, payload : Any) {
        when (event) {
            SaleEventType.CLOSE_TRANSACTION -> {
                finalCalculations(context, payload)
                context.transitionTo(SaleIdleState())
            }
            SaleEventType.COMPLETE_TRANSACTION -> {
                finalCalculations(context, payload)
            }
            SaleEventType.CANCEL_TRANSACTION -> {
                context.transitionTo(SaleCancelState())
            }
            else -> {
                context.transitionTo(SaleErrorState())
            }
        }
    }

    fun finalCalculations(context: SaleContext, payload: Any) {

    }

}