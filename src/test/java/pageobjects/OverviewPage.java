package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class OverviewPage extends MenuPage{

	@FindBy(css = ".subheader")
	WebElement header;
	
	@FindBy(css = ".cart_button")
	WebElement finishBtn;
	
	
	public OverviewPage(WebDriver driver) {
		super(driver);
	}

	@Step("Get overview page header")
	public String getOverviewPageHeader()
	{
		return getText(header);
	}
	
	@Step("Click on finish button")
	public void clickFinishBtn()
	{
		click(finishBtn);
	}
}
