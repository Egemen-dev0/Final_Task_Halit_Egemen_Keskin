package com.epam.Final_Task_Halit_Egemen_Keskin.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

public class FirefoxDriverFactory implements DriverFactory {
    @Override
    public WebDriver createDriver() {
        WebDriverManager.firefoxdriver().browserVersion("latest").setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        return new FirefoxDriver(options);
    }
} 