package com.makemytrip.runners;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.makemytrip.utils.AllureReportOpener;

//import com.makemytrip.utils.AllureReportOpener;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

/**
 * TestRunner class to configure and execute Cucumber tests using TestNG.
 */
@CucumberOptions(
    features = {"src/test/resources/features/"},  // Path to feature files
    glue = {"com.makemytrip.stepDefinitions", "com.makemytrip.hooks"},  // Step definitions and hooks
    plugin = {
        "io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm",  // Allure report plugin
        "pretty",  // Console output formatting
        "html:target/cucumber-report.html"  // HTML report generation
    }
)
public class TestRunner extends AbstractTestNGCucumberTests {

    @BeforeSuite
    public void beforeSuite() {
        // Clean previous Allure results before test execution
        AllureReportOpener.cleanAllureResults();
    }
    @AfterSuite                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
    public void afterSuite() {
        // Open Allure report after test execution
        AllureReportOpener.openAllureReport();
    }
}