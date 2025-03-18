package com.epam.Final_Task_Halit_Egemen_Keskin.data;

import org.testng.annotations.DataProvider;

public class TestDataProvider {
    @DataProvider(name = "browserTypes", parallel = true)
    public static Object[][] browserTypes() {
        return new Object[][]{
                {"chrome"},
                {"edge"},
                {"firefox"}
        };
    }

    @DataProvider(name = "validCredentials")
    public static Object[][] validCredentials() {
        return new Object[][]{
                {"standard_user", "secret_sauce"},
                {"problem_user", "secret_sauce"},
                {"performance_glitch_user", "secret_sauce"},
                {"error_user", "secret_sauce"},
                {"visual_user", "secret_sauce"}
        };
    }

    @DataProvider(name = "invalidCredentials")
    public static Object[][] invalidCredentials() {
        return new Object[][]{
                {"test_user", "test_password"}
        };
    }
} 