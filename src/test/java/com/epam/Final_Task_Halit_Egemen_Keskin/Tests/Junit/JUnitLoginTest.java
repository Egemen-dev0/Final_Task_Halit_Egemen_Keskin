package com.epam.Final_Task_Halit_Egemen_Keskin.Tests.Junit;

import com.epam.Final_Task_Halit_Egemen_Keskin.page.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class JUnitLoginTest {
    private WebDriver driver;
    
    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }
    
    @Test
    public void testLoginWithEmptyCredentials() {
        // Given I am on the login page
        LoginPage loginPage = new LoginPage(driver).open();
        
        // When I try to login without entering credentials
        loginPage.clickLoginButtonExpectingError();
        
        // Then I should see an error message
        String errorMessage = loginPage.getErrorMessage();
        assertThat("Error message should indicate username is required", 
                errorMessage, equalTo("Epic sadface: Username is required"));
    }
    
    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
} 