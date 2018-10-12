package com.neopragma.pointofsale

enum class SaleEventType {
    START_TRANSACTION,
    CANCEL_TRANSACTION,
    ABORT_CANCEL,
    ADD_LINE_ITEM,
    REMOVE_LINE_ITEM,
    COMPLETE_TRANSACTION,
    CLOSE_TRANSACTION
}