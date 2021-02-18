package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class AboutPage extends BasePage {

	@FindBy(css = ".title.is-1")
	WebElement header;
	
	public AboutPage(WebDriver driver) {
		super(driver);
	}
	//Get header of About page
	@Step("Get About header")
	public String getHeader()
	{
		return getText(header);
	}
}
