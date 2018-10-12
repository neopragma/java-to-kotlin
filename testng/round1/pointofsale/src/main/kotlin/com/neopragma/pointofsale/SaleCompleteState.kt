package com.neopragma.pointofsale

import java.lang.IllegalArgumentException

class SaleCompleteState : SaleState() {

    override fun handle(context: SaleContext, event: SaleEventType) {

        println("SaleCompleteState.handle(), event is ${event.name}")

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

    }

}