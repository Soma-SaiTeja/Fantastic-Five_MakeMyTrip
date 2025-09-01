package com.makemytrip.pages;

import java.time.Duration;
import java.util.NoSuchElementException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
	
	static WebDriverWait wait;
	WebDriver driver;
	JavascriptExecutor jse;
	FluentWait<WebDriver> fWait;
	
	@FindBy(xpath="//span[@class='commonModal__close']")
	WebElement closePopup;
	
	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		jse=(JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		fWait = new FluentWait<>(driver)
				 .withTimeout(Duration.ofSeconds(20))
				 .pollingEvery(Duration.ofSeconds(2))
				 .ignoring(NoSuchElementException.class);      
	}
	
	public void popup() {
		closePopup.click();
	}
}
