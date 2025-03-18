package com.epam.Final_Task_Halit_Egemen_Keskin;

import com.epam.Final_Task_Halit_Egemen_Keskin.data.TestDataProvider;
import com.epam.Final_Task_Halit_Egemen_Keskin.page.DashboardPage;
import com.epam.Final_Task_Halit_Egemen_Keskin.page.LoginPage;
import org.testng.annotations.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class LoginWithValidCredentialsTest extends BaseTest {

    @Test(dataProvider = "validCredentials", dataProviderClass = TestDataProvider.class)
    public void testLoginWithValidCredentials(String username, String password) {
        logger.info("Running test: testLoginWithValidCredentials with username: " + username);
        
        // Given I am on the login page
        LoginPage loginPage = new LoginPage(driver).open();
        
        // When I enter valid credentials and click login
        DashboardPage dashboardPage = loginPage
                .enterUsername(username)
                .enterPassword(password)
                .clickLoginButton();
        
        // Then I should be redirected to the dashboard
        String title = dashboardPage.getTitle();
        assertThat("Page title should be 'Swag Labs'", title, equalTo("Swag Labs"));
        assertThat("App logo should be displayed", dashboardPage.isLogoDisplayed(), is(true));
    }
} 