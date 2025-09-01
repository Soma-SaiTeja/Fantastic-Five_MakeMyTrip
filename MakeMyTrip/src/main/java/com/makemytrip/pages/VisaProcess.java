package com.makemytrip.pages;
 
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import com.makemytrip.utils.ExcelUtils;
import com.makemytrip.utils.ConfigReader;
import com.makemytrip.utils.JsonWriter;
 
public class VisaProcess extends BasePage {
	
	JsonWriter writer=new JsonWriter();
	
	String CountryName;
	String Month;
	String TravelDate;
	String ReturnDate;
 
	List<HashMap<String,String>> data=new ArrayList<>();
 
	public VisaProcess(WebDriver driver) throws IOException {
		super(driver);
		
		ExcelUtils excel=new ExcelUtils();
		data=excel.data(ConfigReader.getProperty("test.data.file"),"Visa");
		
		CountryName=data.get(0).get("Destination");
		Month=data.get(0).get("MonthYear");
		TravelDate=data.get(0).get("Travel Date");
		ReturnDate=data.get(0).get("Return Date");
		
	}
	
	@FindBy(xpath="//li[@class = 'menu_Visa']")
	WebElement clickVisa;
	
	@FindBy(xpath="//input[@placeholder = 'Where are you going?']")
	WebElement doubleClick;
	
	@FindBy(xpath="//input[@placeholder = 'Search Country']")
	WebElement country;
	
	@FindBy(className="searchedCity")
	List<WebElement> searchList;
	
	@FindBy(xpath="//label[@for='monthSelect']")
	WebElement dateSelection;
	
	@FindBy(xpath="//div[@class='DayPicker-Caption']")
	List<WebElement> month;
	
	@FindBy(xpath="//div[@role='gridcell']")
	List<WebElement> dates;
	
	@FindBy(xpath="//span[@aria-label='Next Month']")
	WebElement next;
	
	@FindBy(id="search_button")
	WebElement search;
	
	@FindBy(xpath="//div[@class='whiteCard']")
	WebElement VisaProcess;
 
	public void driverSetup() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://www.makemytrip.com/");
	}
	
	public void Visa() {
		
		clickVisa.click();
	}
	
	public void visaCountry() {
		
		Actions action = new Actions(driver);
		
		action.doubleClick(doubleClick).perform();
		
		country.sendKeys(CountryName);
		
		for(WebElement ele : searchList) {
			String text = ele.getText();
			if(text.contains(CountryName)) {
				ele.click();
				}
			}
		}
	
	public void VisaDate(){
		
		    
		dateSelection.click();
		    
		boolean dayVal=false;
		
		while(!dayVal){
			List<WebElement> months=month;
		    List<WebElement> Dates=dates;
		    
		    for(WebElement mon:months){
		        String displayedMonthYear=mon.getText().toLowerCase();
		        if(displayedMonthYear.equalsIgnoreCase(Month.toLowerCase())){
		        	for(WebElement res:Dates){
		        		if(res.getDomAttribute("aria-label").equalsIgnoreCase(TravelDate)){
		        			jse.executeScript("arguments[0].click()",res);
		        		}
		        		if (res.getDomAttribute("aria-label").equalsIgnoreCase(ReturnDate)) {
							jse.executeScript("arguments[0].click()", res);
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
		search.click();
	}
	
	public void VisaSteps(){
		jse.executeScript("arguments[0].scrollIntoView(false)",VisaProcess);
		System.out.println(VisaProcess.getText());
		writer.writeToFile("VisaProcess.json","Steps to succesfully get your visa: ",VisaProcess.getText());
	}
}