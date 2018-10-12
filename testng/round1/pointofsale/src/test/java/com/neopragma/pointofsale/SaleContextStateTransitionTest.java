package com.neopragma.pointofsale;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class SaleContextStateTransitionTest {

    private SaleContext context;

    @BeforeMethod
    public void initializeSaleContext() {
        context = new SaleContext(new SaleIdleState());
    }

    @Test
    public void it_begins_in_idle_state() {
        assertTrue(context.getState() instanceof SaleIdleState,
                expectedStateMessage(context, "SaleIdleState"));
    }

    @Test
    public void start_event_in_idle_state_transitions_to_capture_state() {
        context.process(SaleEventType.START_TRANSACTION);
        assertTrue(context.getState() instanceof SaleCaptureState,
                expectedStateMessage(context, "SaleCaptureState"));
    }

    @Test
    public void start_event_in_capture_state_transitions_to_error_state() {
        context.setState(new SaleCaptureState());
        context.process(SaleEventType.START_TRANSACTION);
        assertTrue(context.getState() instanceof SaleErrorState,
                expectedStateMessage(context, "SaleErrorState"));
    }

    @Test
    public void add_line_item_event_in_capture_state_does_not_transition() {
        context.process(SaleEventType.START_TRANSACTION);
        context.process(SaleEventType.ADD_LINE_ITEM, new Object());
        assertTrue(context.getState() instanceof SaleCaptureState,
                expectedStateMessage(context, "SaleCaptureState"));
    }

    @Test
    public void remove_line_item_event_in_capture_state_does_not_transition() {
        context.process(SaleEventType.START_TRANSACTION);
        context.process(SaleEventType.REMOVE_LINE_ITEM, new Object());
        assertTrue(context.getState() instanceof SaleCaptureState,
                expectedStateMessage(context, "SaleCaptureState"));
    }

    @Test
    public void cancel_event_in_capture_state_transitions_to_cancel_state() {
        context.process(SaleEventType.START_TRANSACTION);
        context.process(SaleEventType.CANCEL_TRANSACTION);
        assertTrue(context.getState() instanceof SaleCancelState,
                expectedStateMessage(context, "SaleCancelState"));
    }

    @Test
    public void complete_event_in_capture_state_transitions_to_complete_state() {
        context.process(SaleEventType.START_TRANSACTION);
        context.process(SaleEventType.COMPLETE_TRANSACTION);
        assertTrue(context.getState() instanceof SaleCompleteState,
                expectedStateMessage(context, "SaleCompleteState"));
    }

    @Test
    public void complete_event_in_complete_state_does_not_transition() {
        context.process(SaleEventType.START_TRANSACTION);
        context.process(SaleEventType.COMPLETE_TRANSACTION);
        assertTrue(context.getState() instanceof SaleCompleteState,
                expectedStateMessage(context, "SaleCompleteState"));
    }

    @Test
    public void close_event_in_complete_state_transitions_to_idle_state() {
        context.process(SaleEventType.START_TRANSACTION);
        context.process(SaleEventType.COMPLETE_TRANSACTION);
        context.process(SaleEventType.CLOSE_TRANSACTION);
        assertTrue(context.getState() instanceof SaleIdleState,
                expectedStateMessage(context, "SaleIdleState"));
    }

    @Test
    public void cancel_event_in_complete_state_transitions_to_cancel_state() {
        context.process(SaleEventType.START_TRANSACTION);
        context.process(SaleEventType.COMPLETE_TRANSACTION);
        context.process(SaleEventType.CANCEL_TRANSACTION);
        assertTrue(context.getState() instanceof SaleCancelState,
                expectedStateMessage(context, "SaleCancelState"));
    }

    @Test
    public void cancel_event_in_cancel_state_transitions_to_idle_state() {
        context.process(SaleEventType.START_TRANSACTION);
        context.process(SaleEventType.COMPLETE_TRANSACTION);
        context.process(SaleEventType.CANCEL_TRANSACTION);
        context.process(SaleEventType.CANCEL_TRANSACTION);
        assertTrue(context.getState() instanceof SaleIdleState,
                expectedStateMessage(context, "SaleIdleState"));
    }

    @Test
    public void abort_cancel_event_in_cancel_state_transitions_to_previous_state() {
        context.process(SaleEventType.START_TRANSACTION);
        context.process(SaleEventType.COMPLETE_TRANSACTION);
        context.process(SaleEventType.CANCEL_TRANSACTION);
        context.process(SaleEventType.ABORT_CANCEL);
        assertTrue(context.getState() instanceof SaleCompleteState,
                expectedStateMessage(context, "SaleCompleteState"));
    }

    private String expectedStateMessage(SaleContext context, String expected) {
        return "Expected " + expected + ", got " + context.getState().getClass().getSimpleName();
    }

}
