package com.epam.Final_Task_Halit_Egemen_Keskin.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class EdgeDriverFactory implements DriverFactory {
    @Override
    public WebDriver createDriver() {
        WebDriverManager.edgedriver().browserVersion("latest").setup();
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--remote-allow-origins=*");
        return new EdgeDriver(options);
    }
} 