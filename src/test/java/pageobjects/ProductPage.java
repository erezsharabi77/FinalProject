package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import io.qameta.allure.Step;

public class ProductPage extends MenuPage {

	@FindBy(css = ".btn_primary.btn_inventory")
	WebElement addToCartBtn;
	
//	@FindBy(css = ".inventory_details > button")
	@FindBy(css = ".inventory_details_back_button")
//	@FindBy(xpath = "//*[@id='inventory_item_container']/div/button")
	WebElement backBtn;
	
	@FindBy(css = ".inventory_details_name")
	WebElement productName;
	
	public ProductPage(WebDriver driver) {
		super(driver);
	}

	@Step("Add product to cart from product page")
	public void addToCart()
	{
		click(addToCartBtn);
	}
	
	@Step("Click back from the Product page")
	public void back()
	{
		wait.until(ExpectedConditions.elementToBeClickable(backBtn));
		click(backBtn);
	}
	
	@Step("Get product name")
	public String getProductName()
	{
//		wait.until(ExpectedConditions.invisibilityOf(productName));
		sleep(300);
		return getText(productName);
	}
}
