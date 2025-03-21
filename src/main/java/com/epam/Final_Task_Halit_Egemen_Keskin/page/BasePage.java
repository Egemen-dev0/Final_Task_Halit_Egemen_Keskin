package com.epam.Final_Task_Halit_Egemen_Keskin.page;

import com.epam.Final_Task_Halit_Egemen_Keskin.adapter.WebDriverAdapter;
import com.epam.Final_Task_Halit_Egemen_Keskin.adapter.WebDriverAdapterImpl;
import com.epam.Final_Task_Halit_Egemen_Keskin.decorator.WebDriverDecorator;
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
    protected WebDriverAdapter driverAdapter;
    protected WebDriverDecorator driverDecorator;
    protected WebDriverWait wait;
    protected static final Logger logger = LogManager.getLogger();

    public BasePage(WebDriver driver) {

        this.driverDecorator = new WebDriverDecorator(driver);
        this.driverAdapter = new WebDriverAdapterImpl(this.driverDecorator);
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
        driverAdapter.click(element);
    }

    protected void sendKeys(WebElement element, String text) {
        driverAdapter.type(element, text);
    }

    protected void clear(WebElement element) {
        driverAdapter.clear(element);
        
        /* 
         * The standard WebElement.clear() sometimes doesn't completely clear inputs on modern websites.
         * For more robust clearing, we can use JavaScript through our decorator
         */
        try {
            driverDecorator.executeScript("arguments[0].value = ''; arguments[0].dispatchEvent(new Event('input'));", element);
            driverDecorator.executeScript("arguments[0].classList.remove('input_error');", element);
            driverDecorator.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", element);
        } catch (Exception e) {
            logger.warn("Failed to clear element using JavaScript: " + e.getMessage());
        }
    }

    protected String getText(WebElement element) {

        return driverAdapter.getText(element);
    }

    protected String getTitle() {

        return driverAdapter.getTitle();
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