package com.makemytrip.hooks;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Reporter;

import com.makemytrip.utils.ConfigReader;
import com.makemytrip.utils.DriverSetup;
import com.makemytrip.utils.ScreenShot;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.qameta.allure.Allure;

public class Hook {
	public static WebDriver driver;

    // Driver setup utility
    static DriverSetup setup;

    // Browser name to be used for test execution
    public static String browser;

    // Logger for logging test execution details
    public Logger logger;

    /**
     * This method runs before each scenario.
     * initializes the WebDriver based on the browser parameter from TestNG XML.
     * Also sets up logging.
     */
    @Before
    public void setUp(Scenario scenario) throws MalformedURLException {
        logger = LogManager.getLogger(this.getClass());

         //Fetch browser name from TestNG XML configuration
        browser = Reporter.getCurrentTestResult()
                          .getTestContext()
                          .getCurrentXmlTest()
                          .getParameter("browser");

        setup = new DriverSetup();

        try {
            // Instantiate WebDriver using the specified browser
            driver = setup.driverInstantiate(ConfigReader.getProperty("browser"));
            logger.info("Driver is initialized successfully.");
        } catch (Exception e) {
            System.out.println("WebDriver initialization failed: " + e.getMessage());
            throw new RuntimeException("WebDriver setup failed", e);
        }
    }

    /**
     * This method runs after each scenario.
     * If the scenario fails, it captures a screenshot and attaches it to the Allure report.
     * Finally, it tears down the WebDriver instance.
     * @throws IOException 
     */
    
    @AfterStep
    public void ScreenShot() throws IOException {
   
            // Capture screenshot on failure
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

            // Attach screenshot to Allure report
            Allure.addAttachment("Screenshot on Failure", new ByteArrayInputStream(screenshot));
            File screenshot1 = ScreenShot.screenShotTC(driver, "ScreenShot");
        	FileUtils.copyFile(screenshot1, new File(System.getProperty("user.dir") +ConfigReader.getProperty("screenshot.path")));
            
    }
    
    @After
    public void tearDown(Scenario scenario) throws IOException {
       
    	if (scenario.isFailed()) {
            // Capture screenshot on failure
            byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
 
            // Attach screenshot to Allure report
            Allure.addAttachment("Screenshot", new ByteArrayInputStream(screenshot));
            
            File screenshot1=ScreenShot.screenShotTC(driver, "ScreenShot");
                        
            FileUtils.copyFile(screenshot1, new File(System.getProperty("user.dir") +ConfigReader.getProperty("screenshot.path")));
        }
    	
        // Close the WebDriver if it's not null
        if (driver != null) {
            setup.driverTearDown();
            logger.info("Driver is closed successfully.");
        }
        
    }
}
