package com.epam.Final_Task_Halit_Egemen_Keskin.Tests.TestNG;

import com.epam.Final_Task_Halit_Egemen_Keskin.data.TestDataProvider;
import com.epam.Final_Task_Halit_Egemen_Keskin.page.LoginPage;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LoginWithEmptyCredentialsTest extends BaseTest {

    @Test(dataProvider = "browserTypes", dataProviderClass = TestDataProvider.class)
    public void testLoginWithEmptyCredentials(String browser) {
        logger.info("Running test: testLoginWithEmptyCredentials with browser: " + browser);
        
        // Given I am on the login page with fresh state
        LoginPage loginPage = new LoginPage(driver).open();
        
        // When I enter and clear username and password
        loginPage.enterUsername("test_user")
                .enterPassword("test_password")
                .clearUsername()
                .clearPassword()
                .refreshPage()
                .clickLoginButtonExpectingError();
        
        // Then I should see an error message
        String errorMessage = loginPage.getErrorMessage();
        assertThat("Error message should indicate username is required", 
                errorMessage, equalTo("Epic sadface: Username is required"));
    }
} 