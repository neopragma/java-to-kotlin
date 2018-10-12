package com.neopragma.pointofsale

import java.lang.IllegalArgumentException

class SaleCancelState : SaleState() {

    override fun handle(context: SaleContext, event: SaleEventType) {
        when (event) {
            SaleEventType.CANCEL_TRANSACTION ->  {
                context.transaction = Transaction()
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

    override fun handle(context: SaleContext, event: SaleEventType, payload: Any) {
        throw IllegalArgumentException("SaleCancelState.handle() cannot accept a payload argument.")
    }

}