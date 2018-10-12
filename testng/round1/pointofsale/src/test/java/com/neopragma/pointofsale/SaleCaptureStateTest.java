package com.neopragma.pointofsale;

import org.javamoney.moneta.Money;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class SaleCaptureStateTest {

    private SaleCaptureState state;
    private SaleContext context;

    @BeforeMethod
    public void initializeStateObject() {
        state = new SaleCaptureState();
        context = new SaleContext(state);
    }

    @Test
    public void it_adds_a_LineItem() {
        state.handle(context, SaleEventType.ADD_LINE_ITEM, Helpers.taxableLineItem());
        assertEquals(context.getTransaction().getLineItems().size(), 1);
    }

    @Test
    public void it_removes_a_LineItem() {
        context.getTransaction().addLineItem(Helpers.taxableLineItem());
        state.handle(context, SaleEventType.REMOVE_LINE_ITEM, Helpers.taxableLineItem());
        assertEquals(context.getTransaction().getLineItems().size(), 0);
    }

    @Test
    public void on_complete_it_calculates_final_sales_price_for_two_line_items() {
        state.handle(context, SaleEventType.ADD_LINE_ITEM, Helpers.taxableLineItem());
        state.handle(context, SaleEventType.ADD_LINE_ITEM, Helpers.nonTaxableLineItem());
        state.handle(context, SaleEventType.COMPLETE_TRANSACTION);
        assertEquals(context.getTransaction().getFinalSalesAmount(), Money.of(24.105, Constants.USD));
    }

}
