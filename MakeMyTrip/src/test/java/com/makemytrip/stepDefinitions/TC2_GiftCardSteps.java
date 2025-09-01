package com.makemytrip.stepDefinitions;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.makemytrip.hooks.Hook;
import com.makemytrip.pages.GiftCardAction;
import com.makemytrip.utils.ScreenShot;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class TC2_GiftCardSteps {
	private WebDriver driver;
	private GiftCardAction gc;
	private static final Logger logger=LogManager.getLogger(TC1_CabSteps.class);
	@Given("user in homepage")
	public void  user_in_homepage() throws IOException {
		
	    driver = Hook.driver;
	    if (driver == null) {
	        throw new RuntimeException("Driver is not initialized. Check Hooks setup.");
	    }
	    gc = new GiftCardAction(driver);
	    ScreenShot.screenShotTC(driver, "02_GiftPage");

	}
	
	@When("user proceeds to close the popup")
	public void closeThePopup() {
		gc.closePop();
	}
	
	@Then("user navigates to gift cards tab")
	public void user_navigates_to_gift_cards_tab() throws InterruptedException {
	    // Write code here that turns the phrase above into concrete actions
	    gc.giftAction();
	}

	@Then("user selects gift card {string} among cards")
	public void user_selects_gift_card_among_cards(String card) {
	    // Write code here that turns the phrase above into concrete actions
	    gc.selectCard();
	}

	@Then("user fills the details {string} {string} {string}")
	public void user_fills_the_details(String senderName, String phnNo, String emailAddress) {
	    // Write code here that turns the phrase above into concrete actions
	    gc.fillDetails();
	}

	@Then("user extracts the error message")
	public void user_extracts_the_error_message() {
	    // Write code here that turns the phrase above into concrete actions
	    gc.getErrorMsg();
	}
}
