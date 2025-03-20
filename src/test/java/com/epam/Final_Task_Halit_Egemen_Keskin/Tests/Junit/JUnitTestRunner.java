package com.epam.Final_Task_Halit_Egemen_Keskin.Tests.Junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
    JUnitLoginTest.class,
    JUnitLoginWithEmptyPasswordTest.class,
    JUnitLoginWithValidCredentialsTest.class
})
public class JUnitTestRunner {
    // This class is just a placeholder for the JUnit test runner
} 