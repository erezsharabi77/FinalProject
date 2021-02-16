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

	public void fillText(WebElement el, String text)
	{
		highlightElement(el, "green");
		sleep(300);
		el.clear();
		el.sendKeys(text);
	}

	public void click(WebElement el)
	{
		highlightElement(el, "green");
		sleep(300);
		el.click();
	}

	public String getText(WebElement el) {
		highlightElement(el, "green");
		sleep(300);
		return el.getText();
	}

	public void sleep(long mills) {
		try {
			Thread.sleep(mills);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isExist(WebElement el)
	{
		return el.isDisplayed();
	}
	
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
