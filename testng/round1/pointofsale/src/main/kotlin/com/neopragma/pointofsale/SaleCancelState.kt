package com.neopragma.pointofsale

class SaleCancelState : SaleState() {

    override fun handle(context: SaleContext, event: SaleEventType, payload : Any) {
        when (event) {
            SaleEventType.CANCEL_TRANSACTION ->  {
                transaction = Transaction()
                context.transitionTo(SaleIdleState())
            }
            SaleEventType.ABORT_CANCEL ->  {
                context.transitionTo(context.previousState)
            }
            else -> {
                context.transitionTo(SaleErrorState())
            }
        }
    }
}