package com.google.calculator.tests;

import com.google.calculator.base.BaseTest;
import com.google.calculator.utils.TestUtils;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ArithmeticOperationsTest extends BaseTest {

    @Test(description = "Test addition operation")
    public void testAddition() {
        calculatorPage.performAddition("23", "45");
        double result = TestUtils.parseDisplayValue(calculatorPage.getDisplayValue());
        Assert.assertEquals(result, 68.0, "Addition operation failed");
    }

    @Test(description = "Test subtraction operation")
    public void testSubtraction() {
        calculatorPage.performSubtraction("75", "25");
        double result = TestUtils.parseDisplayValue(calculatorPage.getDisplayValue());
        Assert.assertEquals(result, 50.0, "Subtraction operation failed");
    }

    @Test(description = "Test multiplication operation")
    public void testMultiplication() {
        calculatorPage.performMultiplication("12", "5");
        double result = TestUtils.parseDisplayValue(calculatorPage.getDisplayValue());
        Assert.assertEquals(result, 60.0, "Multiplication operation failed");
    }

    @Test(description = "Test division operation")
    public void testDivision() {
        calculatorPage.performDivision("100", "4");
        double result = TestUtils.parseDisplayValue(calculatorPage.getDisplayValue());
        Assert.assertEquals(result, 25.0, "Division operation failed");
    }

    @Test(description = "Test division by zero")
    public void testDivisionByZero() {
        calculatorPage.performDivision("5", "0");
        String result = calculatorPage.getDisplayValue();
        Assert.assertTrue(result.contains("Infinity") || result.contains("Error"),
                "Division by zero should result in Infinity or Error");
    }

    @Test(description = "Test decimal number addition")
    public void testDecimalAddition() {
        calculatorPage.performAddition("3.5", "2.7");
        double result = TestUtils.parseDisplayValue(calculatorPage.getDisplayValue());
        Assert.assertEquals(result, 6.2, 0.0001, "Decimal addition operation failed");
    }

    @Test(description = "Test chained operations")
    public void testChainedOperations() {
        calculatorPage.performChainedOperation("5", "+", "3", "*", "2", "-", "4", "=");
        double result = TestUtils.parseDisplayValue(calculatorPage.getDisplayValue());
        Assert.assertEquals(result, 7.0, "Chained operation failed");
    }
}
