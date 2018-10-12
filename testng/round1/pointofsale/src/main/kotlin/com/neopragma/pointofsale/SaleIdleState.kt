package com.neopragma.pointofsale

class SaleIdleState : SaleState() {

    override fun handle(context: SaleContext, event: SaleEventType) {
        when (event) {
            SaleEventType.START_TRANSACTION ->  {
                context.transaction = Transaction()
                context.transitionTo(SaleCaptureState())
            }
        }
    }

    override fun handle(context: SaleContext, event: SaleEventType, payload: Any) {

    }

}