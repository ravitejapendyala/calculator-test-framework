package com.google.calculator.base;

import com.google.calculator.driver.DriverManager;
import com.google.calculator.pages.CalculatorPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

public class BaseTest {
    protected WebDriver driver;
    protected CalculatorPage calculatorPage;

    @BeforeMethod
    @Parameters({"browser", "headless"})
    public void setUp(@Optional String browser, @Optional String headless) {
        // Override configuration with test parameters if provided
        if (browser != null && !browser.isEmpty()) {
            System.setProperty("browser", browser);
        }

        if (headless != null && !headless.isEmpty()) {
            System.setProperty("headless", headless);
        }

        driver = DriverManager.getDriver();
        calculatorPage = new CalculatorPage(driver);
        calculatorPage.visitCalculator();
    }

    @AfterMethod
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
