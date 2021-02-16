package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class CheckoutPage extends MenuPage {
	
	@FindBy(css = "#first-name")
	WebElement first_Name;
	
	@FindBy(css = "#last-name")
	WebElement last_Name;
	
	@FindBy(css = "#postal-code")
	WebElement postalCode;
	
	@FindBy(css = "[value='CONTINUE']")
	WebElement continueBtn;
	
	@FindBy(css = "[data-test='error']")
	WebElement errorMsg;
	
	
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
	}
	
	@Step("Insert user personal details. First name: {firstName}, Last name: {lastName} and zip code {zipCode}")
	public void insertInfo(String firstName, String lastName, String zipCode)
	{
		fillText(first_Name, firstName);
		fillText(last_Name, lastName);
		fillText(postalCode, zipCode);
		click(continueBtn);
	}
	
	@Step("Click continue button")
	public void clickContinue()
	{
		click(continueBtn);
	}
	
	@Step("Get error message")
	public String getErrMsg()
	{
		return getText(errorMsg);
	}
}
