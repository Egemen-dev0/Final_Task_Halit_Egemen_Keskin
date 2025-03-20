package com.epam.Final_Task_Halit_Egemen_Keskin.Tests.Junit;

import com.epam.Final_Task_Halit_Egemen_Keskin.page.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class JUnitLoginWithEmptyPasswordTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testLoginWithEmptyPassword() {
        // Given I am on the login page
        LoginPage loginPage = new LoginPage(driver).open();

        // When I enter username and click login without entering password
        loginPage.enterUsername("standard_user")
                .clickLoginButtonExpectingError();

        // Then I should see an error message
        String errorMessage = loginPage.getErrorMessage();
        assertThat("Error message should indicate password is required", 
                errorMessage, equalTo("Epic sadface: Password is required"));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
} 