package com.automation.factory;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.automation.config.ConfigReader;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * Factory for WebDriver instances. Supports thread-safe drivers for parallel tests.
 */
public class WebDriverFactory {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    /**
     * Initialize WebDriver for the current thread.
     */
    public static void initDriver() {
        String browser = ConfigReader.get("browser", "chrome");
        boolean headless = ConfigReader.getBoolean("headless", false);

        WebDriver driver;
        switch (browser.toLowerCase()) {
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions ffOptions = new FirefoxOptions();
                if (headless) ffOptions.addArguments("--headless");
                driver = new FirefoxDriver(ffOptions);
                break;

            case "edge":
                WebDriverManager.edgedriver().setup();
                EdgeOptions edgeOptions = new EdgeOptions();
                if (headless) edgeOptions.addArguments("--headless");
                driver = new EdgeDriver(edgeOptions);
                break;

            case "chrome":
            default:
                WebDriverManager.chromedriver().setup();
                ChromeOptions chromeOptions = new ChromeOptions();
                if (headless) chromeOptions.addArguments("--headless");
                driver = new ChromeDriver(chromeOptions);
                break;
        }

        // Apply timeouts
        driver.manage().timeouts().implicitlyWait(
            Duration.ofSeconds(ConfigReader.getInt("implicitWait", 10)));
        driver.manage().timeouts().pageLoadTimeout(
            Duration.ofSeconds(ConfigReader.getInt("pageLoadTimeout", 30)));

        driver.manage().window().maximize();

        driverThreadLocal.set(driver);
    }

    /**
     * Get the WebDriver for the current thread.
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        return driverThreadLocal.get();
    }

    /**
     * Quit and remove the WebDriver for the current thread.
     */
    public static void quitDriver() {
        WebDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }
}

