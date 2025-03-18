package com.epam.Final_Task_Halit_Egemen_Keskin.page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.OutputType;
import java.io.File;
import java.nio.file.Files;

import java.time.Duration;

public abstract class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;
    protected static final Logger logger = LogManager.getLogger();

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    protected void waitForElementToBeVisible(WebElement element) {
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected void waitForElementToBeClickable(WebElement element) {
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    protected void click(WebElement element) {
        waitForElementToBeClickable(element);
        element.click();
        logger.info("Clicked on element: " + element);
    }

    protected void sendKeys(WebElement element, String text) {
        waitForElementToBeVisible(element);
        element.clear();
        element.sendKeys(text);
        logger.info("Entered text '" + text + "' into element: " + element);
    }

    protected void clear(WebElement element) {
        waitForElementToBeVisible(element);
        element.clear();
        element.sendKeys("");
        
        /* 
         * The standard WebElement.clear() sometimes doesn't completely clear inputs on modern websites.
         * This is because some sites have JavaScript that manages input state or form data
         * beyond the standard DOM input value.
         *
         * Issues encountered with clearing fields on SauceDemo site:
         * 1. Fields appear to be cleared but then get repopulated with previous values before submission
         * 2. JavaScript events might be restoring field values from browser autofill
         * 3. WebElements can become stale after clearing them with JavaScript
         *
         * The approach below using JavascriptExecutor sometimes works better than the standard clear(),
         * but even this had issues in our current setup.
         */
        try {
            if (driver instanceof JavascriptExecutor) {
                ((JavascriptExecutor) driver).executeScript("arguments[0].value = '';", element);
            } else {
                logger.warn("Driver does not support JavaScript execution");
            }
        } catch (Exception e) {
            logger.warn("Failed to clear element using JavaScript: " + e.getMessage());
        }
        logger.info("Cleared element: " + element);
    }

    protected String getText(WebElement element) {
        waitForElementToBeVisible(element);
        String text = element.getText();
        logger.info("Got text '" + text + "' from element: " + element);
        return text;
    }

    protected String getTitle() {
        String title = driver.getTitle();
        logger.info("Got page title: " + title);
        return title;
    }

    protected void takeScreenshot(String name) {
        try {
            if (driver instanceof TakesScreenshot) {
                File screenshotsDir = new File("screenshots");
                if (!screenshotsDir.exists()) {
                    boolean dirCreated = screenshotsDir.mkdirs();
                    if (!dirCreated) {
                        logger.warn("Failed to create screenshots directory at: " + screenshotsDir.getAbsolutePath());
                    } else {
                        logger.info("Created screenshots directory at: " + screenshotsDir.getAbsolutePath());
                    }
                }

                String timestamp = String.valueOf(System.currentTimeMillis());
                File destFile = new File(screenshotsDir.getAbsolutePath(), name + "_" + timestamp + ".png");

                File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

                Files.copy(scrFile.toPath(), destFile.toPath());
                logger.info("Screenshot saved: " + destFile.getAbsolutePath());
            } else {
                logger.warn("Driver does not support taking screenshots");
            }
        } catch (Exception e) {
            logger.error("Failed to take screenshot: " + e.getMessage(), e);
        }
    }
} 