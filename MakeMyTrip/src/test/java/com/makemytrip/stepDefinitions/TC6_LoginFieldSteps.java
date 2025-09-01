package com.makemytrip.stepDefinitions;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.makemytrip.hooks.Hook;
import com.makemytrip.pages.LoginField;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
 
public class TC6_LoginFieldSteps {
	
	private WebDriver driver;
	private LoginField login;
	private static final Logger logger=LogManager.getLogger(LoginField.class);
 
	@When("The HomePage popUp appears")
	public void the_home_page_pop_up_appears() throws IOException {
		driver = Hook.driver;
        if (driver == null) {
            throw new RuntimeException("Driver is not initialized. Check Hooks setup.");
        }
        login=new LoginField(driver);
	}
 
	@Then("Navigate to MyBizAccount section to login")
	public void navigate_to_my_biz_account_section_to_login() {
		login.switchOption();
	}
 
	@Then("Click the Forgot Login Id button")
	public void click_the_forgot_login_id_button() {
	    login.forgotId();
	}
 
	@Then("Type incorrect mobie number {string}")
	public void type_incorrect_mobie_number(String string) {
	    login.numberLogin();
	}
 
	@Then("Click continue")
	public void click_continue() {
	    login.submit();
	}
 
	@Then("Retrieve the error message")
	public void retrieve_the_error_message() {
	    login.LoginFail();
	    logger.info("Login failed");
	}
 
}
