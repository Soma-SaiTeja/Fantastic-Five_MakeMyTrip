package com.makemytrip.stepDefinitions;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.makemytrip.hooks.Hook;
import com.makemytrip.pages.CabsPage;
import com.makemytrip.pages.GetAdultCount;
import com.makemytrip.utils.ScreenShot;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TC3_AdultCountSteps {
	
	private WebDriver driver;
	private GetAdultCount gac;
	private static final Logger logger=LogManager.getLogger(TC1_CabSteps.class);
	@Given("user is on the homepage")
	public void  user_is_on_the_homepage() throws IOException {
		
	    driver = Hook.driver;
	    if (driver == null) {
	        throw new RuntimeException("Driver is not initialized. Check Hooks setup.");
	    }
	    gac = new GetAdultCount(driver);
	    ScreenShot.screenShotTC(driver, "03_AdultCountPage");

	}
	
	
	
	
	@And("the login popup is closed")
	public void the_login_popup_is_closed() {
	    // Write code here that turns the phrase above into concrete actions
	    gac.closePop();
	}

	@When("the user navigates to the Hotels tab")
	public void the_user_navigates_to_the_hotels_tab() {
	    // Write code here that turns the phrase above into concrete actions
	    gac.hotelsTab();
	}

	@And("the user opens the guests dropdown and selects the Adults option")
	public void the_user_opens_the_guests_dropdown_and_selects_the_adults_option() {
	    // Write code here that turns the phrase above into concrete actions
	    gac.selectAdultOption();
	}

	@Then("the maximum adults value should be {string}")
	public void the_maximum_adults_value_should_be(String string) {
	    // Write code here that turns the phrase above into concrete actions
	    gac.getAdultCount();
	}
	
}
