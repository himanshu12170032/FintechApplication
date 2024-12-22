package com.assignment.project.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {
                "com.assignment.project.stepdefinitions",
                "com.assignment.project.Config"
        },
        plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class AccountTestRunner {

}
