package pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.qameta.allure.Step;

public class MenuPage extends BasePage{

	@FindBy(css = ".svg-inline--fa.fa-shopping-cart.fa-w-18.fa-3x")
	WebElement cartBtn;
	
	@FindBy(css = ".bm-burger-button button")
	WebElement menuIcon;
	
	@FindBy(css = "#inventory_sidebar_link")
	WebElement allItems;
	
	@FindBy(css = "#about_sidebar_link")
	WebElement about;
	
	@FindBy(css = "#logout_sidebar_link")
	WebElement logout;
	
	@FindBy(css = "#reset_sidebar_link")
	WebElement resetAppState;
	
	@FindBy(css = ".bm-cross-button button")
	WebElement closeBtn;
	
	@FindBy(css = "#loading")
	WebElement loading;
	
	@FindBy(css = ".shopping_cart_badge")
	WebElement cartBadge;
	

	
	public MenuPage(WebDriver driver) {
		super(driver);
	}

	public void waitingForLoading() {
		wait.until(ExpectedConditions.visibilityOf(loading));
		wait.until(ExpectedConditions.invisibilityOf(loading));
	}
	
	@Step("Open cart to see all products")
	public void openCart() {
		click(cartBtn);
	}
	
	@Step("Click on the menu bar in the left hand side of the page")
	public void clickMenu()
	{
		click(menuIcon);
	}
	
	@Step("Click on logout from the menu bar")
	public void logout()
	{
		click(logout);
	}
	
	@Step("Click on All Items from the menu bar")
	public void allItems()
	{
		click(allItems);
	}
	
	@Step("Click on About from the menu bar")
	public void about()
	{
		click(about);
	}
	
	@Step("Click on Reset App State from the menu bar")
	public void resetAppState()
	{
		click(resetAppState);
	}
	
	@Step("Close menu bar")
	public void closeMenu()
	{
		click(closeBtn);
	}
	
	@Step("Get the number of items as appear on the cart icon")
	public String getcartBadgeNumber()
	{
		return getText(cartBadge);
	}
}
