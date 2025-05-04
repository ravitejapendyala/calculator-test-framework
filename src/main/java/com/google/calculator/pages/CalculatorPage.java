package com.google.calculator.pages;

import com.google.calculator.config.ConfigurationManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class CalculatorPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    // Calculator element locators
    private static final By DISPLAY_FIELD = By.id("cwos");
    private static final String DIGIT_BUTTON_XPATH = "//div[@role='button' and text()='%s']";
    private static final By PLUS_BUTTON = By.xpath("//div[@role='button' and text()='+']");
    private static final By MINUS_BUTTON = By.xpath("//div[@role='button' and text()='−']");
    private static final By MULTIPLY_BUTTON = By.xpath("//div[@role='button' and text()='×']");
    private static final By DIVIDE_BUTTON = By.xpath("//div[@role='button' and text()='÷']");
    private static final By EQUALS_BUTTON = By.xpath("//div[@role='button' and text()='=']");
    private static final By AC_BUTTON = By.xpath("//div[@role='button' and text()='AC']");
    private static final By DECIMAL_BUTTON = By.xpath("//div[@role='button' and text()='.']");

    public CalculatorPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void visitCalculator() {
        driver.get(ConfigurationManager.getInstance().getApplicationUrl());
        driver.findElement(By.name("q")).sendKeys("Google Calculator");
        driver.findElement(By.xpath("(//input[@value='Google Search'])[2]")).click();
        waitForCalculatorToLoad();
    }

    private void waitForCalculatorToLoad() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(DISPLAY_FIELD));
    }

    public void clickDigit(int digit) {
        if (digit < 0 || digit > 9) {
            throw new IllegalArgumentException("Digit must be between 0 and 9");
        }
        WebElement digitButton = driver.findElement(By.xpath(String.format(DIGIT_BUTTON_XPATH, digit)));
        digitButton.click();
    }

    public void enterNumber(String number) {
        // Clear the calculator first
        clearCalculator();

        // Enter each digit
        for (char digit : number.toCharArray()) {
            if (digit == '.') {
                clickDecimalPoint();
            } else {
                clickDigit(Character.getNumericValue(digit));
            }
        }
    }

    public void clickPlus() {
        driver.findElement(PLUS_BUTTON).click();
    }

    public void clickMinus() {
        driver.findElement(MINUS_BUTTON).click();
    }

    public void clickMultiply() {
        driver.findElement(MULTIPLY_BUTTON).click();
    }

    public void clickDivide() {
        driver.findElement(DIVIDE_BUTTON).click();
    }

    public void clickEquals() {
        driver.findElement(EQUALS_BUTTON).click();
    }

    public void clearCalculator() {
        driver.findElement(AC_BUTTON).click();
    }

    public void clickDecimalPoint() {
        driver.findElement(DECIMAL_BUTTON).click();
    }

    public String getDisplayValue() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(DISPLAY_FIELD)).getText();
    }

    public void performAddition(String num1, String num2) {
        enterNumber(num1);
        clickPlus();
        // No need to clear since we're continuing the operation
        for (char digit : num2.toCharArray()) {
            if (digit == '.') {
                clickDecimalPoint();
            } else {
                clickDigit(Character.getNumericValue(digit));
            }
        }
        clickEquals();
    }

    public void performSubtraction(String num1, String num2) {
        enterNumber(num1);
        clickMinus();
        for (char digit : num2.toCharArray()) {
            if (digit == '.') {
                clickDecimalPoint();
            } else {
                clickDigit(Character.getNumericValue(digit));
            }
        }
        clickEquals();
    }

    public void performMultiplication(String num1, String num2) {
        enterNumber(num1);
        clickMultiply();
        for (char digit : num2.toCharArray()) {
            if (digit == '.') {
                clickDecimalPoint();
            } else {
                clickDigit(Character.getNumericValue(digit));
            }
        }
        clickEquals();
    }

    public void performDivision(String num1, String num2) {
        enterNumber(num1);
        clickDivide();
        for (char digit : num2.toCharArray()) {
            if (digit == '.') {
                clickDecimalPoint();
            } else {
                clickDigit(Character.getNumericValue(digit));
            }
        }
        clickEquals();
    }

    public void performChainedOperation(String... inputs) {
        clearCalculator();

        // First number
        for (char digit : inputs[0].toCharArray()) {
            if (digit == '.') {
                clickDecimalPoint();
            } else {
                clickDigit(Character.getNumericValue(digit));
            }
        }

        // Process operations
        for (int i = 1; i < inputs.length; i += 2) {
            String operator = inputs[i];
            switch (operator) {
                case "+":
                    clickPlus();
                    break;
                case "-":
                    clickMinus();
                    break;
                case "*":
                    clickMultiply();
                    break;
                case "/":
                    clickDivide();
                    break;
                case "=":
                    clickEquals();
                    return;
                default:
                    throw new IllegalArgumentException("Unsupported operator: " + operator);
            }

            // Next number if available
            if (i + 1 < inputs.length) {
                for (char digit : inputs[i + 1].toCharArray()) {
                    if (digit == '.') {
                        clickDecimalPoint();
                    } else {
                        clickDigit(Character.getNumericValue(digit));
                    }
                }
            }
        }

        // Calculate final result
        clickEquals();
    }
}