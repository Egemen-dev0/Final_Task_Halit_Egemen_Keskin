package com.epam.Final_Task_Halit_Egemen_Keskin;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.epam.Final_Task_Halit_Egemen_Keskin.steps", "com.epam.Final_Task_Halit_Egemen_Keskin.config"},
        plugin = {"pretty", "html:target/cucumber-reports"}
)
public class CucumberRunner {
} 