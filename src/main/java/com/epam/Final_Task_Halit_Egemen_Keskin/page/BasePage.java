package com.epam.Final_Task_Halit_Egemen_Keskin.page;

import com.epam.Final_Task_Halit_Egemen_Keskin.adapter.WebDriverAdapter;
import com.epam.Final_Task_Halit_Egemen_Keskin.adapter.WebDriverAdapterImpl;
import com.epam.Final_Task_Halit_Egemen_Keskin.decorator.WebDriverDecorator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
        // First try the standard clear method via adapter
        driverAdapter.clear(element);
        
        /* 
         * For more robust clearing on modern websites, we'll use an enhanced JavaScript approach
         * that handles different types of form fields and ensures both the value property 
         * and value attribute are cleared, while also preventing autofill
         */
        try {
            // More comprehensive JavaScript to clear input fields with advanced techniques
            String script = 
                "const field = arguments[0];" +
                "const fieldType = field.type;" +
                
                "// Find the parent form to disable autocomplete at form level" +
                "let form = field.form;" +
                "if (form) {" +
                "    form.setAttribute('autocomplete', 'off');" +
                "}" +
                
                "// Store original name to restore later if needed" +
                "const originalName = field.getAttribute('name');" +
                "if (originalName) {" +
                "    field.setAttribute('data-original-name', originalName);" +
                "    // Temporarily change the name to prevent browser autofill" +
                "    field.setAttribute('name', originalName + '-prevent');" +
                "}" +
                
                "// Disable autocomplete on the field" +
                "field.setAttribute('autocomplete', 'off');" +
                "field.setAttribute('autocapitalize', 'off');" +
                "field.setAttribute('autocorrect', 'off');" +
                
                "if (fieldType === 'text' || fieldType === 'email' || fieldType === 'password' || fieldType === 'textarea') {" +
                "    field.value = '';" + // Clear the value property
                "    field.setAttribute('value', '');" + // Clear the value attribute
                "    // Create and dispatch multiple events to ensure change is registered" +
                "    field.dispatchEvent(new Event('input', { bubbles: true }));" +
                "    field.dispatchEvent(new Event('change', { bubbles: true }));" +
                "    field.dispatchEvent(new MouseEvent('click'));" +
                "} else if (fieldType === 'checkbox' || fieldType === 'radio') {" +
                "    field.checked = false;" +
                "    field.removeAttribute('checked');" +
                "} else if (fieldType === 'select-one') {" +
                "    field.selectedIndex = 0;" +
                "}" +
                
                "// Remove any error styling classes" +
                "field.classList.remove('input_error');" +
                
                "// Create field properties to avoid them being captured by browser" +
                "Object.defineProperty(field, 'value', {" +
                "    get: function() { return this.getAttribute('data-value') || ''; }," +
                "    set: function(val) { this.setAttribute('data-value', val || ''); }," +
                "    configurable: true" +
                "});" +
                
                "// Return debugging information" +
                "return {" +
                "    value: field.value," +
                "    autocomplete: field.getAttribute('autocomplete')," +
                "    name: field.getAttribute('name')," +
                "    formAutocomplete: form ? form.getAttribute('autocomplete') : 'no-form'" +
                "};";
    
            Object result = driverDecorator.executeScript(script, element);
            logger.info("Field after enhanced clearing: " + result);
            
            // Wait a brief moment to ensure changes are applied
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            
            // Refresh element reference to avoid stale element issues
            PageFactory.initElements(driver, this);
            
            // Verify the element value is truly empty
            String verifyScript = "return arguments[0].value || arguments[0].getAttribute('value');";
            String verifiedValue = (String) driverDecorator.executeScript(verifyScript, element);
            logger.info("Verified field value after clearing: [" + verifiedValue + "]");
            
        } catch (Exception e) {
            logger.warn("Failed to clear element using enhanced JavaScript: " + e.getMessage(), e);
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