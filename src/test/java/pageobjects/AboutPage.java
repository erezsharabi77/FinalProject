package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AboutPage extends BasePage {

	@FindBy(css = ".title.is-1")
	WebElement header;
	
	public AboutPage(WebDriver driver) {
		super(driver);
	}

	public String getHeader()
	{
		return getText(header);
	}
}
