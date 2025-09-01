package com.makemytrip.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import com.makemytrip.utils.ConfigReader;
import com.makemytrip.utils.ExcelUtils;
import com.makemytrip.utils.JsonWriter;

public class LoginField extends BasePage{
	
    String mobileNumber;
	
    JsonWriter writer = new JsonWriter();
	
	List<HashMap<String,String>> data=new ArrayList<>();
	
	public LoginField(WebDriver driver) throws IOException {
		super(driver);
		ExcelUtils excel = new ExcelUtils();
		data = excel.data(ConfigReader.getProperty("test.data.file"),"Login");
		mobileNumber = data.get(0).get("MobileNumber");
    }
 
	
	@FindBy(xpath = "//li[text() = 'MyBiz Account']")
	WebElement switchAcc;
	@FindBy(xpath = "//a[@data-cy = 'MyBizLogin_117']")
	WebElement forgot;
	@FindBy(id = "inputNum")
	WebElement phoneNum;
	@FindBy(xpath = "//button[@data-cy = 'MyBizPhoneLogin#_137']")
	WebElement clickContinue;
	@FindBy(xpath = "//div[@data-cy='errorText']/p")
	WebElement getMsg;
	
	WebDriver driver;
	
	public void switchOption() {
		switchAcc.click();
	}

	public void forgotId() {
		forgot.click();
	}

	public void numberLogin() {
		phoneNum.sendKeys(mobileNumber);
	}

	public void submit() {
		jse.executeScript("arguments[0].click()", clickContinue);
	}
 
	public void LoginFail() {
		String error = getMsg.getText();
		System.out.println(error);
		writer.writeToFile("LoginError.json","Error text: ",error);
		
	}
}
