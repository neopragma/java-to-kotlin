package com.neopragma.pointofsale

abstract class SaleState {

    abstract fun handle(context: SaleContext, event: SaleEventType, payload : Any)

    abstract fun handle(context: SaleContext, event: SaleEventType)
}