package com.epam.Final_Task_Halit_Egemen_Keskin.adapter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * This is a simple adapter interface that provides simplified methods
 * for common WebDriver operations.
 */
public interface WebDriverAdapter {
    void navigateTo(String url);
    void click(WebElement element);
    void type(WebElement element, String text);
    void clear(WebElement element);
    String getText(WebElement element);
    String getTitle();
    void quit();
} 