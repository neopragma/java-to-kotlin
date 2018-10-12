package com.neopragma.pointofsale

class SaleContext(state: SaleState) {

    var state : SaleState = SaleIdleState()
    var previousState : SaleState = SaleIdleState()

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

}