package com.neopragma.pointofsale;

import org.javamoney.moneta.Money;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import javax.money.Monetary;

import static com.neopragma.pointofsale.SaleKt.*;

public class SaleIT {

    @Test
    public void it_orchestrates_a_sale_happy_path() {
        SoftAssert softAssert = new SoftAssert();

        newSale();
        softAssert.assertEquals(getContext().getState().getClass().getSimpleName(),
                "SaleCaptureState", "Sale transaction should begin in capture state.");

        ringUpUPC("999BBC12345");
        softAssert.assertEquals(getContext().getTransaction().getLineItems().size(), 1);

        ringUpUPC("999ABC12346", 3);
        softAssert.assertEquals(getContext().getTransaction().getLineItems().size(), 2);

        ringUpUPC("999ABC12347", Money.of(10, Constants.USD));
        softAssert.assertEquals(getContext().getTransaction().getLineItems().size(), 3);

        completeSale();
        softAssert.assertEquals(
                getContext().getState().getClass().getSimpleName(),
                "SaleCompleteState",
                "Sale transaction should be in complete state.");
        softAssert.assertEquals(
                getContext().getTransaction().getFinalSalesAmount().with(Monetary.getDefaultRounding()),
                Money.of(73.12, Constants.USD),
                "Expect calculated total sale amount to be present in the Transaction object at this point.");

        confirmSale();
        softAssert.assertEquals(
                getContext().getState().getClass().getSimpleName(),
                "SaleIdleState",
                "System should be ready for the next sale.");
        softAssert.assertEquals(
                getContext().getReceipt().getReceiptLines().size(), 3);




        softAssert.assertAll();

    }

}
