package com.epam.Final_Task_Halit_Egemen_Keskin.steps;

import com.epam.Final_Task_Halit_Egemen_Keskin.driver.DriverManager;
import com.epam.Final_Task_Halit_Egemen_Keskin.page.DashboardPage;
import com.epam.Final_Task_Halit_Egemen_Keskin.page.LoginPage;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class LoginSteps {
    private WebDriver driver;
    private LoginPage loginPage;
    private DashboardPage dashboardPage;
    private static final Logger logger = LogManager.getLogger();

    @Before
    public void setUp() {
        logger.info("Starting test with Chrome browser");
        driver = DriverManager.getDriver("chrome");
    }

    @After
    public void tearDown() {
        logger.info("Finishing test");
        DriverManager.quitDriver();
    }

    @Given("I am on the login page")
    public void iAmOnTheLoginPage() {
        loginPage = new LoginPage(driver).open();
    }

    @When("I enter {string} in the username field")
    public void iEnterInTheUsernameField(String username) {
        loginPage.enterUsername(username);
    }

    @When("I enter {string} in the password field")
    public void iEnterInThePasswordField(String password) {
        loginPage.enterPassword(password);
    }

    @When("I clear the username field")
    public void iClearTheUsernameField() {
        loginPage.clearUsername();
    }

    @When("I clear the password field")
    public void iClearThePasswordField() {
        loginPage.clearPassword();
    }

    @When("I click the login button")
    public void iClickTheLoginButton() {
        dashboardPage = loginPage.clickLoginButton();
    }

    @When("I click the login button expecting error")
    public void iClickTheLoginButtonExpectingError() {
        logger.info("Clicking login button and expecting an error");
        loginPage = loginPage.clickLoginButtonExpectingError();
    }

    @Then("I should see the error message {string}")
    public void iShouldSeeTheErrorMessage(String expectedErrorMessage) {
        String actualErrorMessage = loginPage.getErrorMessage();
        assertThat("Error message should match expected", 
                actualErrorMessage, equalTo(expectedErrorMessage));
    }

    @Then("I should be redirected to the dashboard")
    public void iShouldBeRedirectedToTheDashboard() {
        assertThat("App logo should be displayed", 
                dashboardPage.isLogoDisplayed(), is(true));
    }

    @Then("I should see the title {string}")
    public void iShouldSeeTheTitle(String expectedTitle) {
        String actualTitle = dashboardPage.getTitle();
        assertThat("Page title should match expected", 
                actualTitle, equalTo(expectedTitle));
    }
} 