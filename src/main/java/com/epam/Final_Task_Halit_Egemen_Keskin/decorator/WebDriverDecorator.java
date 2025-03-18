package com.epam.Final_Task_Halit_Egemen_Keskin.decorator;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.Set;

public class WebDriverDecorator implements WebDriver, JavascriptExecutor {
    private final WebDriver driver;
    private static final Logger logger = LogManager.getLogger();

    public WebDriverDecorator(WebDriver driver) {
        this.driver = driver;
    }

    @Override
    public void get(String url) {
        logger.info("Navigating to URL: " + url);
        driver.get(url);
    }

    @Override
    public String getCurrentUrl() {
        String currentUrl = driver.getCurrentUrl();
        logger.info("Getting current URL: " + currentUrl);
        return currentUrl;
    }

    @Override
    public String getTitle() {
        String title = driver.getTitle();
        logger.info("Getting page title: " + title);
        return title;
    }

    @Override
    public List<WebElement> findElements(By by) {
        logger.info("Finding elements by: " + by);
        return driver.findElements(by);
    }

    @Override
    public WebElement findElement(By by) {
        logger.info("Finding element by: " + by);
        return driver.findElement(by);
    }

    @Override
    public String getPageSource() {
        logger.info("Getting page source");
        return driver.getPageSource();
    }

    @Override
    public void close() {
        logger.info("Closing browser");
        driver.close();
    }

    @Override
    public void quit() {
        logger.info("Quitting browser");
        driver.quit();
    }

    @Override
    public Set<String> getWindowHandles() {
        logger.info("Getting window handles");
        return driver.getWindowHandles();
    }

    @Override
    public String getWindowHandle() {
        String windowHandle = driver.getWindowHandle();
        logger.info("Getting window handle: " + windowHandle);
        return windowHandle;
    }

    @Override
    public TargetLocator switchTo() {
        logger.info("Switching to another frame/window");
        return driver.switchTo();
    }

    @Override
    public Navigation navigate() {
        logger.info("Getting navigation object");
        return driver.navigate();
    }

    @Override
    public Options manage() {
        logger.info("Getting options object");
        return driver.manage();
    }

    @Override
    public Object executeScript(String script, Object... args) {
        logger.info("Executing JavaScript: " + script);
        return ((JavascriptExecutor) driver).executeScript(script, args);
    }

    @Override
    public Object executeAsyncScript(String script, Object... args) {
        logger.info("Executing async JavaScript: " + script);
        return ((JavascriptExecutor) driver).executeAsyncScript(script, args);
    }
} 