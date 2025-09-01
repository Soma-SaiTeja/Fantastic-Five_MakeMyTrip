package com.makemytrip.pages;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.makemytrip.utils.JsonWriter;

public class TravelInsurancePage extends BasePage{
	
	JsonWriter writer = new JsonWriter();
	
	public TravelInsurancePage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//span[@data-cy='closeModal']")
	public WebElement closeLoginPopup;

	@FindBy(xpath = "//li[@data-cy='menu_TravelInsurance']")
	public WebElement travel;

	@FindBy(xpath = "//img[@src = 'https://tripmoneycmsimgak.mmtcdn.com/img/USA_45e303dba3.png']")
	public WebElement country;

	@FindBy(xpath = "//span[text() = 'Student']")
	public WebElement student;

	@FindBy(xpath = "//span[text() = 'OKAY,GOT IT']")
	public WebElement closePopup;

	@FindBy(xpath = "//span[text() = 'VIEW PLANS']")
	public WebElement viewPlan;

//	@FindBy(xpath = "//span[text() = '1 mo.']")
//	public WebElement duration;

	@FindBy(xpath = "//div[@data-test-id='InsurancePlansComp-InsuranceTypeSection']")
	public List<WebElement> planDetails;

	@FindBy(xpath = "//div[@data-test-id='InsurancePlansComp-InsurancePlanTypeHd']//span[@data-test-id='FormattedText']")
	public WebElement planName; // "Acko General Insurance Limited"
	
	@FindBy(css = "div[data-test-id='InsurancePlansComp-InsurancePlanSection']")
	public List<WebElement> cards;

	public void handlePopup() {
		closeLoginPopup.click();
	}
	
	public void clickTravel() {
		travel.click();
	}
	
	public void fillDetails() {
		wait.until(ExpectedConditions.elementToBeClickable(country)).click();
		student.click();

		viewPlan.click();
		wait.until(ExpectedConditions.elementToBeClickable(closePopup)).click();
		// duration.click();
	}
	
	public void getPlanDetails() {
		String name = planName.getText().trim();
		System.out.println("Insurance Provider:" + " " + name);
		//Collections.sort(planDetails);

		for (WebElement info : planDetails) {
			String planPrice = info.getText().trim();
			System.out.println("Price Details:" + " " + planPrice);
			writer.writeToFile("PlanDetails.json","the name and prices of plan are : ", name +" "+planPrice);
		}
	}
}
