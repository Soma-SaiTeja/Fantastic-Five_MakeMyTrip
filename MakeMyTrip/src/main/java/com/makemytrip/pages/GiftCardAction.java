package com.makemytrip.pages;

import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.makemytrip.utils.ConfigReader;
import com.makemytrip.utils.ExcelUtils;
import com.makemytrip.utils.JsonWriter;

public class GiftCardAction extends BasePage{
	
	String card;
	String senderName;
	String phnNo;
	String emailAddress;
	
	JsonWriter writer = new JsonWriter();
	
	List<HashMap<String,String>> data = new ArrayList<>();
	public GiftCardAction(WebDriver driver) throws IOException {
		super(driver);
		ExcelUtils excel = new ExcelUtils();
		data = excel.data(ConfigReader.getProperty("test.data.file"),"GiftCard");
		card = data.get(0).get("Gift Card Name");
		senderName = data.get(0).get("Name");
		phnNo = data.get(0).get("Number");
		emailAddress = data.get(0).get("Email");
	}
	
	@FindBy(xpath = "//span[@data-cy='closeModal']")
	public WebElement closeLoginPopup;
	
	@FindBy(xpath = "//li[@data-cy = 'tertiaryRowItem_Gift Cards']")
	public WebElement giftCard;
	
	@FindBy(xpath = "//h3[@class = 'lato-black black-text']")
	public List<WebElement> cards;
	
	@FindBy(xpath = "//input[@name = 'senderName']")
	public WebElement name;
	
	@FindBy(xpath = "//input[@name = 'senderMobileNo']")
	public WebElement mobileNum;
	
	@FindBy(xpath = "//input[@name = 'senderEmailId']")
	public WebElement email;
	
	@FindBy(xpath = "//button[@data-cy = 'BookingDetails_440']")
	public WebElement buy;
	
	@FindBy(xpath = "//p[@class = 'red-text font11 append-top5']")
	public WebElement errormsg;
	
	public void closePop() {
		closeLoginPopup.click();
		}
	
	public void giftAction() throws InterruptedException {
	
//		Actions a = new Actions(driver);
//		a.moveToElement(giftCard).perform();
		jse.executeScript("arguments[0].click()",giftCard);
		String originalWindow = driver.getWindowHandle();

		// Wait for the new window/tab to open
		new WebDriverWait(driver, Duration.ofSeconds(10))
		    .until(driver -> driver.getWindowHandles().size() > 1);

		// Switch to the new window
		for (String windowHandle : driver.getWindowHandles()) {
		    if (!windowHandle.equals(originalWindow)) {
		        driver.switchTo().window(windowHandle);
		        break;
		    }
		}
		}
		
		public void selectCard() {
		for(WebElement c : cards) {
			String trueCard = c.getText().toLowerCase();
			if(trueCard.contains(card.toLowerCase())) {
				jse.executeScript("arguments[0].scrollIntoView(false)",c);
				//fWait.until(driver -> { return jse.executeScript("arguments[0].click()",c); });
				jse.executeScript("arguments[0].click()",c);
			}
		}}
		
		public void fillDetails() {
		//name.click();
		name.sendKeys(senderName);
		
		//mobileNum.click();
		mobileNum.sendKeys(phnNo);
		
		//email.click();
		email.sendKeys(emailAddress);
		buy.click();
		
		}
		
		public void getErrorMsg() {
		//return errormsg.getText();
		System.out.println("the error message being displayed: "+errormsg.getText());
		writer.writeToFile("FetchErrorMsg.json","ErrorMsg : ", errormsg.getText());
		}
	}
