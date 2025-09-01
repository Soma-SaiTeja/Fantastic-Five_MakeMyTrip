package com.makemytrip.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverSetup {
	
	private static WebDriver driver;
	
	@SuppressWarnings("deprecation")
	public WebDriver driverInstantiate(String browser) throws MalformedURLException {
		
		String Env=ConfigReader.getProperty("environment").toLowerCase();
		String os=ConfigReader.getProperty("os").toLowerCase();
		
		if (Env.equals("remote")) {
            // Set desired capabilities for remote execution
            DesiredCapabilities capabilities = new DesiredCapabilities();

            switch (os) {
                case "windows":
                    capabilities.setPlatform(Platform.WINDOWS);
                    break;
                case "mac":
                    capabilities.setPlatform(Platform.MAC);
                    break;
                case "linux":
                    capabilities.setPlatform(Platform.LINUX);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported OS: " + os);
            }

            switch (browser) {
                case "chrome":
                    capabilities.setBrowserName("chrome");
                    break;
                case "edge":
                    capabilities.setBrowserName("MicrosoftEdge");
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported browser: " + browser);
            }

            // Initialize RemoteWebDriver with Selenium Grid URL
            driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);

        } else if (Env.equals("local")) {
            // Initialize local WebDriver based on browser
            if (browser.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver();
            } else if (browser.equalsIgnoreCase("edge")) {
                driver = new EdgeDriver();
            } else {
                throw new IllegalArgumentException("Unsupported browser: " + browser);
            }
        }
		
		driver.get(ConfigReader.getProperty("app.url"));
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        
        return driver;	
	}
	public void driverTearDown() {

		if (driver != null) {
            driver.quit();
        }
    }

}
