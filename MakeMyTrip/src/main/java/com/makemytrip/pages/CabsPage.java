package com.makemytrip.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.makemytrip.utils.ExcelUtils;
import com.makemytrip.utils.JsonWriter;
import com.makemytrip.utils.ConfigReader;

public class CabsPage extends BasePage{
	
	String from;
	String to;
	String destination;
	String travelMonYear;
	String travelDate;
	String hour;
	String minute;
	String meridian;
	String CarType;
	
	JsonWriter writer = new JsonWriter();
	
	
	List<HashMap<String,String>> data = new ArrayList<>();
	public CabsPage(WebDriver driver) throws IOException{
		super(driver);
		ExcelUtils excel = new ExcelUtils();
		data = excel.data(ConfigReader.getProperty("test.data.file"),"Cabs");
		from = data.get(0).get("From");
		to = data.get(0).get("To");
		destination = data.get(0).get("Destination");
		travelMonYear = data.get(0).get("travelMonYear");
		travelDate = data.get(0).get("TravelDate");
		hour = data.get(0).get("Hour");
		minute = data.get(0).get("Minute");
		meridian =data.get(0).get("Meridian");
		CarType = data.get(0).get("CarType");
	}
	
	@FindBy(xpath = "//span[@data-cy='closeModal']")
	public WebElement closeLoginPopup;
	
	@FindBy(xpath = "//li[@data-cy='menu_Cabs']")
	public WebElement cabs;
	
	@FindBy(xpath = "//div[@data-cy = 'OutstationOneWayWidget_57']")
	public WebElement source;
	
	@FindBy(xpath = "//input[@placeholder='From']")
	public WebElement fromLocation;
	
	@FindBy(xpath = "//*[text() = 'Delhi']")
	public WebElement selectFromLoc;
	
	@FindBy(xpath = "//input[@placeholder = 'To']")
	public WebElement toLocation;
	
	@FindBy(xpath = "//span[@class = 'sr_city blackText']")
	public List<WebElement> selectToLoc;
	
	@FindBy(xpath="//label[@for='departure']")
	WebElement date;
	
	@FindBy(xpath="//div[@class='DayPicker-Caption']")
	List<WebElement> month;
	
	@FindBy(xpath="//span[@aria-label='Next Month']")
	WebElement next;
	
	@FindBy(xpath="//div[@role='gridcell']")
	List<WebElement> dates;
	
	@FindBy(xpath = "//div[@data-cy='OutstationOneWayWidget_62']")
	public WebElement time;
	
	@FindBy(xpath = "//li[@class = 'hrSlotItemParent']")
	public List<WebElement> selectHr;
	
	@FindBy(xpath = "//li[@class = 'minSlotItemParent']")
	List<WebElement> selectMin;
	
	@FindBy(xpath = "//span[@class = 'applyBtnText']")
	public WebElement apply;
	
	@FindBy(xpath = "//a[@data-cy='OutstationOneWayWidget_64']")
	public WebElement search;
	
	@FindBy(xpath = "//img[@alt='Close']")
	public WebElement closeSecondPopup;
	
	@FindBy(xpath = "//span[text()='SUV']")
	public WebElement carType;
	
	@FindBy(xpath = "//span[@class='cabDetailsCard_price__SHN6W']")
	public List<WebElement> prices;
	
	public void closePopup() {
		wait.until(ExpectedConditions.elementToBeClickable(closeLoginPopup)).click();
	}
	
	public void cabsMenu() {
		cabs.click();
	}
	
	public void fromAndTo() {
		source.click();
		fromLocation.click();
		fromLocation.sendKeys(from);
		wait.until(ExpectedConditions.elementToBeClickable(selectFromLoc)).click();
		
		toLocation.sendKeys(to);
		fWait.until(driver -> { return selectToLoc.get(0).getText().toLowerCase().contains(to.toLowerCase()); });
		
		for(WebElement to : selectToLoc) {
			String val = to.getText();
			if(val.equalsIgnoreCase(destination)) {
				to.click();
				break;
			}
		}
		}
		
	public void DateSelection() {
		date.click();
        
		boolean dayVal = false;
	    while(!dayVal){
	    	
	        List<WebElement> months=month;
	        List<WebElement> Dates=dates;
 
	        for(WebElement mon:months){
	        	
	            String displayedMonthYear=mon.getText().toLowerCase();
	            if(displayedMonthYear.equalsIgnoreCase(travelMonYear.toLowerCase())){
	                for(WebElement res:Dates){
	                    if(res.getDomAttribute("aria-label").equalsIgnoreCase(travelDate)){
	                        jse.executeScript("arguments[0].click()",res);
	                        dayVal=true;
	                        break;
	                    }
	                }
	            }
	        }
	        if(!dayVal){
	            jse.executeScript("arguments[0].click()",next);
	        }
	    }
	}
		
		public void Time() {
		time.click();
		
		for(WebElement hr : selectHr) {
			String hour = hr.getText();
			if(hour.equals("06 Hr")) {
				hr.click();
				break;
			}
		}
		
		for(WebElement min : selectMin) {
			String minutes = min.getText();
			if(minutes.equals("30 min")) {
				min.click();
				break;
			}
		}
		}
		
		public void applyDetails() {
			apply.click();
		}
		
		public void searchForResults() {
			search.click();
		}
		
		public void closePop() {
			
		try{
			if(closeSecondPopup.isDisplayed()) {
				closeSecondPopup.click();
			}
		}
		catch(Exception e) {}
		}
		
		public void selectSUV() { 

				carType.click();
		}
		
		public void GetLowestPrice() {
		int[] arr = new int[prices.size()];
		int i=0;
		for(WebElement price : prices) {
			String lowPrices = price.getText();
			int lowPrice = Integer.parseInt(lowPrices.replaceAll("[^0-9]", ""));
			arr[i]=lowPrice;
			i++;
		}

		Arrays.sort(arr);
		System.out.println(arr[0]);
		writer.writeToFile("LowCarPrice.json","lowest car price: ", String.valueOf(arr[0]));
	}

}