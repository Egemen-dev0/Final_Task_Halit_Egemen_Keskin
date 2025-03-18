package com.epam.Final_Task_Halit_Egemen_Keskin.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class DashboardPage extends BasePage {
    @FindBy(how = How.XPATH, using = "//div[@class='app_logo']")
    private WebElement appLogo;

    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public boolean isLogoDisplayed() {
        waitForElementToBeVisible(appLogo);
        return appLogo.isDisplayed();
    }
} 