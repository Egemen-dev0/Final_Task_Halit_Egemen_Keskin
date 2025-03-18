package com.epam.Final_Task_Halit_Egemen_Keskin.adapter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Implementation of the WebDriverAdapter interface that adapts the WebDriver
 * to provide simplified methods for common operations.
 */
public class WebDriverAdapterImpl implements WebDriverAdapter {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private static final Logger logger = LogManager.getLogger();

    public WebDriverAdapterImpl(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Override
    public void navigateTo(String url) {
        logger.info("Navigating to URL: " + url);
        driver.get(url);
    }

    @Override
    public void click(WebElement element) {
        logger.info("Clicking on element: " + element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    @Override
    public void type(WebElement element, String text) {
        logger.info("Typing text '" + text + "' into element: " + element);
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
        element.sendKeys(text);
    }

    @Override
    public void clear(WebElement element) {
        logger.info("Clearing element: " + element);
        wait.until(ExpectedConditions.visibilityOf(element));
        element.clear();
    }

    @Override
    public String getText(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
        String text = element.getText();
        logger.info("Getting text from element: " + text);
        return text;
    }

    @Override
    public String getTitle() {
        String title = driver.getTitle();
        logger.info("Getting page title: " + title);
        return title;
    }

    @Override
    public void quit() {
        logger.info("Quitting WebDriver");
        driver.quit();
    }
} 