package com.neopragma.pointofsale

import java.lang.IllegalArgumentException

class SaleErrorState : SaleState() {

    override fun handle(context: SaleContext, event: SaleEventType) {
    }

    override fun handle(context: SaleContext, event: SaleEventType, payload: Any) {
    }

}