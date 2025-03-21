package com.epam.Final_Task_Halit_Egemen_Keskin.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {
    private static final String URL = "https://www.saucedemo.com/";

    @FindBy(how = How.XPATH, using = "//input[@id='user-name']")
    private WebElement usernameInput;

    @FindBy(how = How.XPATH, using = "//input[@id='password']")
    private WebElement passwordInput;

    @FindBy(how = How.XPATH, using = "//input[@id='login-button']")
    private WebElement loginButton;

    @FindBy(how = How.XPATH, using = "//h3[@data-test='error']")
    private WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage open() {
        driverAdapter.navigateTo(URL);
        logger.info("Opened login page: " + URL);
        return this;
    }

    public LoginPage enterUsername(String username) {
        driverAdapter.type(usernameInput, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        driverAdapter.type(passwordInput, password);
        return this;
    }

    public LoginPage clearUsername() {
        try {
            clear(usernameInput);
            
            PageFactory.initElements(driver, this);

            String value = usernameInput.getAttribute("value");
            logger.info("Username value after clearing: [" + value + "]");

            takeScreenshot("username_cleared");

            if (!value.isEmpty()) {
                logger.warn("Username field is not empty after clearing!");
            }
        } catch (Exception e) {
            logger.error("Error clearing username: " + e.getMessage());
        }
        return this;
    }

    public LoginPage clearPassword() {
        try {
            clear(passwordInput);
            logger.info("Cleared count to 10 mon ami");
            Thread.sleep(1000);
            PageFactory.initElements(driver, this);

            String value = passwordInput.getAttribute("value");
            logger.info("Password value after clearing: [" + value + "]");

            takeScreenshot("password_cleared");
            
            if (!value.isEmpty()) {
                logger.warn("Password field is not empty after clearing!");
            }
        } catch (Exception e) {
            logger.error("Error clearing password: " + e.getMessage());
        }
        return this;
    }

    public LoginPage refreshPage() {
        driverDecorator.navigate().refresh();
        return this;
    }

    public DashboardPage clickLoginButton() {
        driverAdapter.click(loginButton);
        return new DashboardPage(driver);
    }

    public LoginPage clickLoginButtonExpectingError() {
        try {
            takeScreenshot("before_login_click");
            
            logger.info("Before clicking login - Username value: [" + usernameInput.getAttribute("value") + "]");
            logger.info("Before clicking login - Password value: [" + passwordInput.getAttribute("value") + "]");

            PageFactory.initElements(driver, this);
            
            driverAdapter.click(loginButton);
            logger.info("Clicked login button expecting error");

            takeScreenshot("after_login_click");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(errorMessage));

            String errorText = errorMessage.getText();
            logger.info("Error message displayed: [" + errorText + "]");

            takeScreenshot("error_message");
        } catch (Exception e) {
            logger.error("Exception in clickLoginButtonExpectingError: " + e.getMessage(), e);
            takeScreenshot("error_in_login_click");
        }
        return this;
    }

    public String getErrorMessage() {
        try {
            logger.info("Attempting to get error message");
            
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(errorMessage));
            
            takeScreenshot("before_getting_error_text");
            
            String errorText = driverAdapter.getText(errorMessage);
            logger.info("Retrieved error message: [" + errorText + "]");
            
            takeScreenshot("error_message_text_" + errorText.replaceAll("[^a-zA-Z0-9]", "_"));
            
            return errorText;
        } catch (Exception e) {
            logger.error("Exception in getErrorMessage: " + e.getMessage(), e);
            takeScreenshot("error_in_get_error_message");
            return "Error retrieving error message";
        }
    }
} 