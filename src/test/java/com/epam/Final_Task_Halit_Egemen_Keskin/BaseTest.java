package com.epam.Final_Task_Halit_Egemen_Keskin;

import com.epam.Final_Task_Halit_Egemen_Keskin.driver.DriverManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Optional;

public class BaseTest {
    protected WebDriver driver;
    protected static final Logger logger = LogManager.getLogger();

    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
        logger.info("Starting test with browser: " + browser);
        driver = DriverManager.getDriver(browser);
    }

    @AfterMethod
    public void tearDown() {
        logger.info("Finishing test");
        DriverManager.quitDriver();
    }
}