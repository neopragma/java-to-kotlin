package com.neopragma.pointofsale

class SaleIdleState : SaleState() {

    override fun handle(context: SaleContext, event: SaleEventType, payload : Any) {
        when (event) {
            SaleEventType.START_TRANSACTION ->  {
                transaction = Transaction()
                context.transitionTo(SaleCaptureState())
            }
        }
    }

}