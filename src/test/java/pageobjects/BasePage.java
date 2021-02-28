package pageobjects;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	JavascriptExecutor js; 
	WebDriverWait wait;
	WebDriver driver;

	public BasePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
		js=(JavascriptExecutor)driver;
		wait = new WebDriverWait(driver, 15);
	}
	
	//Fill a given text to a given element
	public void fillText(WebElement el, String text)
	{
		highlightElement(el, "green");
		sleep(300);
		el.clear();
		el.sendKeys(text);
	}
	//Click on a given element
	public void click(WebElement el)
	{
		highlightElement(el, "green");
		sleep(300);
		el.click();
	}
	
	//Get Text from a given element
	public String getText(WebElement el) {
		highlightElement(el, "green");
		sleep(300);
		return el.getText();
	}

	//Wait for a given milliseconds
	public void sleep(long mills) {
		try {
			Thread.sleep(mills);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	//Check if element exist
	public boolean isExist(WebElement el)
	{
		return el.isDisplayed();
	}
	
	//Check if a given list exists
	public boolean isListExist(List<WebElement> list)
	{
		if (list.size() != 0) {
			return true;
		}
		
		else
			return false;
	}
	/*
	 * Call this method with your element and a color like (red,green,orange etc...)
	 * Use this method to highlight elements on the screen
	 */
	private void highlightElement(WebElement element, String color) {
		//keep the old style to change it back
		String originalStyle = element.getAttribute("style");
		String newStyle = "background-color:yellow; border: 1px solid " + color + ";" + originalStyle;

		// Change the style 
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '" + newStyle + "');},0);",
				element);

		// Change the style back after few milliseconds
		js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
				+ originalStyle + "');},400);", element);

	}
}
