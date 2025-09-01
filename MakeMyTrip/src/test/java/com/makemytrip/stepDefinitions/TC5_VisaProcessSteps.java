package com.makemytrip.stepDefinitions;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.makemytrip.hooks.Hook;
import com.makemytrip.pages.VisaProcess;
import com.makemytrip.utils.ScreenShot;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TC5_VisaProcessSteps {
	
	private WebDriver driver;
	private VisaProcess vp;
	private static final Logger logger=LogManager.getLogger(TC1_CabSteps.class);
	@Given("user in the homepage")
	public void  user_in_the_homepage() throws IOException {
		
	    driver = Hook.driver;
	    if (driver == null) {
	        throw new RuntimeException("Driver is not initialized. Check Hooks setup.");
	    }
	    vp = new VisaProcess(driver);
	    ScreenShot.screenShotTC(driver, "05_VisaPage");

	}
	
	@Then("closing popup")
	public void closing_popup() {
		vp.popup();
	}
	
	@When("user navigates to visa tab")
	public void user_navigates_to_visa_tab() {
	    // Write code here that turns the phrase above into concrete actions
	    vp.Visa();
	}

	@And("user double clicks on the destination to enter country {string}")
	public void user_double_clicks_on_the_destination_to_enter_country(String cou) {
	    // Write code here that turns the phrase above into concrete actions
	   vp.visaCountry();
	}

	@And("user also selects MonthYear {string} with date of departure {string} and {string}")
	public void user_also_selects_month_year_with_date_of_departure_and(String travelMonyear, String travelDate, String returnDate) {
	    // Write code here that turns the phrase above into concrete actions
	    vp.VisaDate();
	}

	@Then("user fetched the steps of visa process")
	public void user_fetched_the_steps_of_visa_process() {
	    // Write code here that turns the phrase above into concrete actions
	    vp.VisaSteps();
	}
}
