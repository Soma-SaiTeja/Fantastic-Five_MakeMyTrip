package com.makemytrip.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.makemytrip.utils.JsonWriter;

public class GetAdultCount extends BasePage{
	
	JsonWriter writer = new JsonWriter();
	
	public GetAdultCount(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//span[@data-cy='closeModal']")
	public WebElement closeLoginPopup;
	
	@FindBy(xpath = "//li[@data-cy='menu_Hotels']")
	public WebElement hotels;
	
	@FindBy(xpath = "//div[@data-cy='HotelSearchWidget_319']")
	public WebElement dropDown;
	
	@FindBy(xpath = "//span[@data-testid='adult_count']")
	public WebElement adult;
	
	@FindBy(xpath = "//ul[@class='gstSlct__list']/li")
	public List<WebElement> adultCount;
	
	public void closePop() {
		closeLoginPopup.click();
	}
	
	public void hotelsTab() {
		hotels.click();
	}
	
	public void selectAdultOption() {
	
		dropDown.click();
		adult.click();
		
//		for(WebElement count : adultCount) {
//			String max = count.getText();
//			System.out.println(max);
//		}
	}
	
	public void getAdultCount() {
		String max = adultCount.get(adultCount.size()-1).getText();
		System.out.println(max);
		writer.writeToFile("adultcount.json","the maximum adult count is : ", max);
	}
}
