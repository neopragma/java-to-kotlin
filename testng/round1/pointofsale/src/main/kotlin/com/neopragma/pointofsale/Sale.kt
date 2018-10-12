package com.neopragma.pointofsale

import com.neopragma.pointofsale.adapters.SkuAdapter
import org.javamoney.moneta.Money
import javax.money.MonetaryAmount

var context: SaleContext = SaleContext(SaleIdleState())

/** Respond to New Sale keypad entry */
fun newSale() {
    context.process(SaleEventType.START_TRANSACTION)
}

/** Respond to UPC entry with quantity and price entered by cashier */
fun ringUpUPC(upc: String, price: MonetaryAmount, quantity: Int) {
    var sku : Sku = lookUpSku(upc)
    processLineItem(sku, price, quantity);
}

/** Respond to UPC entry, default quantity 1, price entered by cashier */
fun ringUpUPC(upc: String, price: MonetaryAmount) {
    var sku : Sku = lookUpSku(upc)
    processLineItem(sku, price, 1)
}

/** Respond to UPC entry, default price from Sku, quantity entered by cashier */
fun ringUpUPC(upc: String, quantity: Int) {
    var sku : Sku = lookUpSku(upc)
    processLineItem(sku, sku.price(), quantity)
}

/** Respond to UPC entry, default price from Sku, default quantity 1 */
fun ringUpUPC(upc: String) {
    var sku : Sku = lookUpSku(upc)
    processLineItem(sku, sku.price(), 1)
}

/** Respond to Complete Sale keypad entry */
fun completeSale() {
    context.process(SaleEventType.COMPLETE_TRANSACTION);
}

/** Respond to Confirm Sale keypad entry */
fun confirmSale() {
    context.process(SaleEventType.CLOSE_TRANSACTION);
}

private fun lookUpSku(upc: String) : Sku {
    var skuService: SkuAdapter = SkuAdapter()
    return skuService.findByUPC(upc)
}

private fun processLineItem(sku: Sku, price: MonetaryAmount, quantity: Int) {
    context.process(SaleEventType.ADD_LINE_ITEM, LineItem(sku, price, quantity))

}