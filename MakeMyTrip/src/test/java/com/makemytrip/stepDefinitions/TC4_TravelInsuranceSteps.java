package com.makemytrip.stepDefinitions;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.makemytrip.hooks.Hook;
import com.makemytrip.pages.CabsPage;
import com.makemytrip.pages.TravelInsurancePage;
import com.makemytrip.utils.ScreenShot;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TC4_TravelInsuranceSteps {

	private WebDriver driver;
	private TravelInsurancePage tip;
	private static final Logger logger=LogManager.getLogger(TC1_CabSteps.class);
	
	@Given("user opens homepage")
	public void user_opens_homepage() {
			
		    driver = Hook.driver;
		    if (driver == null) {
		        throw new RuntimeException("Driver is not initialized. Check Hooks setup.");
		    }
		    tip = new TravelInsurancePage(driver);
		    ScreenShot.screenShotTC(driver, "04_TravelInsurancePage");
	}

	@When("user handles the popup")
	public void user_handles_the_popup() {
	    // Write code here that turns the phrase above into concrete actions
		tip.handlePopup();
		
	}

	@When("clicks on travel insurance tab")
	public void clicks_on_travel_insurance_tab() {
	    // Write code here that turns the phrase above into concrete actions
	    tip.clickTravel();
	}

	@When("fills the details")
	public void fills_the_details() {
	    // Write code here that turns the phrase above into concrete actions
	    tip.fillDetails();
	}

	@Then("fetches plan details")
	public void fetches_plan_details() {
	    // Write code here that turns the phrase above into concrete actions
	    tip.getPlanDetails();
	}
	
}
