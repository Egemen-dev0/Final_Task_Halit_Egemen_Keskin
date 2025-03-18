package com.epam.Final_Task_Halit_Egemen_Keskin;

import com.epam.Final_Task_Halit_Egemen_Keskin.data.TestDataProvider;
import com.epam.Final_Task_Halit_Egemen_Keskin.page.LoginPage;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class LoginWithEmptyPasswordTest extends BaseTest {

    @Test(dataProvider = "browserTypes", dataProviderClass = TestDataProvider.class)
    public void testLoginWithEmptyPassword(String browser) {
        logger.info("Running test: testLoginWithEmptyPassword with browser: " + browser);
        
        // Given I am on the login page
        LoginPage loginPage = new LoginPage(driver).open();
        
        /* ORIGINAL APPROACH THAT DIDN'T WORK - Kept for reference
        // When I enter username and password, then clear password
        loginPage.enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clearPassword()
                .clickLoginButtonExpectingError();
        */
        
        // Enter only username, leave password empty
        loginPage.enterUsername("standard_user")
                .clickLoginButtonExpectingError();
        
        // Then I should see an error message
        String errorMessage = loginPage.getErrorMessage();
        assertThat("Error message should indicate password is required", 
                errorMessage, equalTo("Epic sadface: Password is required"));
    }
} 