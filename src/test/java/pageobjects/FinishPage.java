package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class FinishPage extends MenuPage {
	
	@FindBy(css = ".complete-header")
	WebElement thankYouMsg;
	
	public FinishPage(WebDriver driver) {
		super(driver);
	}

	@Step("Get Thank You page message")
	public String getThankYouMsg()
	{
		return getText(thankYouMsg);
	}
}
