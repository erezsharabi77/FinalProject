package pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class YourCartPage extends MenuPage {

	@FindBy(css = ".btn_action.checkout_button")
	WebElement checkoutBtn;
	
	@FindBy(css = ".subheader")
	WebElement header;
	
	@FindBy(css = ".cart_footer .btn_secondary")
	WebElement continueShoppingBtn;
	
	@FindBy(css = ".btn_secondary.cart_button")
	List<WebElement> list;
	
	public YourCartPage(WebDriver driver) {
		super(driver);
	}

	@Step("Click on checkout button")
	public void checkout()
	{
		click(checkoutBtn);
	}
	
	@Step("Get 'Your Cart' header")
	public String getYourCartPageHeader()
	{
		return getText(header);
	}
	
	@Step("Click on 'Continue Shopping' button")
	public void clickContinueShopping()
	{
		click(continueShoppingBtn);
	}
	
	@Step("Remove all products")
	public void removeAllProduct()
	{
		for (WebElement el : list) {
			click(el);
		}
	}
	@Step("Check if products exist in 'Your Cart' page")
	public boolean areProductsExist()
	{
		return isListExist(list);
	}
	
}
