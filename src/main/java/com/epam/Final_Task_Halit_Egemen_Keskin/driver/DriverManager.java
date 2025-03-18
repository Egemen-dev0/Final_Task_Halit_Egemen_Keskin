package com.epam.Final_Task_Halit_Egemen_Keskin.driver;

import com.epam.Final_Task_Halit_Egemen_Keskin.decorator.WebDriverDecorator;
import org.openqa.selenium.WebDriver;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driverThreadLocal = new ThreadLocal<>();

    private DriverManager() {
    }

    public static WebDriver getDriver(String browserType) {
        if (driverThreadLocal.get() == null) {
            DriverFactory driverFactory;
            switch (browserType.toLowerCase()) {
                case "chrome":
                    driverFactory = new ChromeDriverFactory();
                    break;
                case "edge":
                    driverFactory = new EdgeDriverFactory();
                    break;
                case "firefox":
                    driverFactory = new FirefoxDriverFactory();
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser type: " + browserType);
            }
            WebDriver driver = driverFactory.createDriver();

            WebDriver decoratedDriver = new WebDriverDecorator(driver);
            driverThreadLocal.set(decoratedDriver);
        }
        return driverThreadLocal.get();
    }

    public static void quitDriver() {
        if (driverThreadLocal.get() != null) {
            driverThreadLocal.get().quit();
            driverThreadLocal.remove();
        }
    }
} 