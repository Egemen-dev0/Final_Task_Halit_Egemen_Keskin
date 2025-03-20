package com.epam.Final_Task_Halit_Egemen_Keskin.Tests.Junit;

import com.epam.Final_Task_Halit_Egemen_Keskin.page.DashboardPage;
import com.epam.Final_Task_Halit_Egemen_Keskin.page.LoginPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class JUnitLoginWithValidCredentialsTest {
    private WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testLoginWithValidCredentials() {
        // Given I am on the login page
        LoginPage loginPage = new LoginPage(driver).open();

        // When I enter valid credentials and click login
        DashboardPage dashboardPage = loginPage
                .enterUsername("standard_user")
                .enterPassword("secret_sauce")
                .clickLoginButton();

        // Then I should be redirected to the dashboard
        String title = dashboardPage.getTitle();
        assertThat("Page title should be 'Swag Labs'", title, equalTo("Swag Labs"));
        assertThat("App logo should be displayed", dashboardPage.isLogoDisplayed(), is(true));
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
} 