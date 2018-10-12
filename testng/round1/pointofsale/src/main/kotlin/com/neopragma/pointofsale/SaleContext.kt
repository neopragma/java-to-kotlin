package com.neopragma.pointofsale

open class SaleContext(state: SaleState) {

    var transaction : Transaction = Transaction()
    var state : SaleState = SaleIdleState()
    var previousState : SaleState = SaleIdleState()
    var receipt : Receipt = Receipt()

    fun transitionTo(state : SaleState) {
        when (this.state) {
            is SaleCaptureState, is SaleCompleteState -> {
                previousState = this.state
            }
        }
        this.state = state
    }

    fun process(event : SaleEventType, payload : Any) {
        state.handle(this, event, payload)
    }

    fun process(event : SaleEventType) {
        state.handle(this, event)
    }

}