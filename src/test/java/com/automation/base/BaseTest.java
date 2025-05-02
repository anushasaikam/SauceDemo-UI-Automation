package com.automation.base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.automation.factory.WebDriverFactory;

/**
 * Base test class that initializes and quits WebDriver via WebDriverFactory.
 */
public class BaseTest {

    protected WebDriver driver;

    @BeforeMethod
    public void setup() {
        // Initialize WebDriver instance
        driver = new ChromeDriver(); // Example: Initialize WebDriver
        driver.get("https://www.saucedemo.com");
    }

    @AfterMethod
    public void tearDown() {
        // Quit and clean up the WebDriver instance
        WebDriverFactory.quitDriver();
    }
}
