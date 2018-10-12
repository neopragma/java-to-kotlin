package com.neopragma.pointofsale

abstract class SaleState {

    var transaction : Transaction = Transaction()

    abstract fun handle(context: SaleContext, event: SaleEventType, payload : Any)
}