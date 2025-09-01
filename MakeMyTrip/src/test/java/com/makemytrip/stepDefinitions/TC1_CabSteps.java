package com.makemytrip.stepDefinitions;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.makemytrip.hooks.Hook;
import com.makemytrip.pages.CabsPage;
import com.makemytrip.utils.ScreenShot;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TC1_CabSteps {
	
	private WebDriver driver;
	private CabsPage cp;
	private static final Logger logger=LogManager.getLogger(TC1_CabSteps.class);
	@Given("user is on homepage")
	public void  user_is_on_homepage() throws IOException {
		
	    driver = Hook.driver;
	    if (driver == null) {
	        throw new RuntimeException("Driver is not initialized. Check Hooks setup.");
	    }
	    cp = new CabsPage(driver);
	    ScreenShot.screenShotTC(driver, "01_HomePage");

	} 
	
	@When ("popup appears user proceeds to close the popup")
	public void close_popup(){
		cp.closePopup();
	}
	
	@Then ("user navigates to cabs menu")
	public void user_navigates_to_cabs_menu() {
		cp.cabsMenu();
	}
	
	
	@And ("user fills the details of from {string} and to {string}")
	public void userFillsFromAndTo(String start, String destination) {
		cp.fromAndTo();
	}
	
	@Then ("user proceeds to select travel date {string}")
	public void userSelectsDate(String TravelDate){
		cp.DateSelection();
	}
	
	@And ("user selects the required time {string}")
	public void selectTime(String Time) {
		cp.Time();
	}
	
	@Then ("user clicks on apply")
	public void apply() {
		cp.applyDetails();
	}
	
	@Given ("user now clicks on search to fetch results")
	public void search() {
		cp.searchForResults();
	}
	
	@When ("user closes the secondPopup") 
	public void closeSecondPopUp() {
		cp.closePop();
	}
	
	@And ("user selects cab type as suv")
	public void selectCarType() {
		cp.selectSUV();
	}
	
	@Then  ("wait for the results to load")
	public void waitForResults() {
		
	}
	
	@Then("user fetches the lowest price cab available")
	public void fetchLowPrice() {
		cp.GetLowestPrice();
	}
	
}
