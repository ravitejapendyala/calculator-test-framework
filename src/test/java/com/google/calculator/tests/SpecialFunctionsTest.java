package com.google.calculator.tests;

import com.google.calculator.base.BaseTest;
import com.google.calculator.utils.TestUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SpecialFunctionsTest extends BaseTest {

    @Test(description = "Test clear functionality")
    public void testClearFunctionality() {
        calculatorPage.enterNumber("123");
        calculatorPage.clearCalculator();
        String displayValue = calculatorPage.getDisplayValue();
        Assert.assertEquals(displayValue, "0", "Clear operation failed");
    }

    @Test(description = "Test negative number operations")
    public void testNegativeNumberOperations() {
        calculatorPage.enterNumber("10");
        calculatorPage.clickMinus();
        calculatorPage.enterNumber("15");
        calculatorPage.clickEquals();
        double result = TestUtils.parseDisplayValue(calculatorPage.getDisplayValue());
        Assert.assertEquals(result, -5.0, "Negative number operation failed");
    }

    @Test(description = "Test multiple decimal points prevention")
    public void testMultipleDecimalPoints() {
        // Attempt to enter multiple decimal points
        calculatorPage.enterNumber("5");
        calculatorPage.clickDecimalPoint();
        calculatorPage.clickDecimalPoint(); // This should be ignored
        calculatorPage.enterNumber("25");

        String displayValue = calculatorPage.getDisplayValue();
        Assert.assertEquals(displayValue, "5.25", "Multiple decimal points were not prevented");
    }

    @Test(description = "Test consecutive operations")
    public void testConsecutiveOperations() {
        calculatorPage.enterNumber("5");
        calculatorPage.clickPlus();
        calculatorPage.clickMultiply(); // This should replace the plus operation
        calculatorPage.enterNumber("3");
        calculatorPage.clickEquals();

        double result = TestUtils.parseDisplayValue(calculatorPage.getDisplayValue());
        Assert.assertEquals(result, 15.0, "Consecutive operations handling failed");
    }
}
