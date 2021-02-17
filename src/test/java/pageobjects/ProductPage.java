package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import io.qameta.allure.Step;

public class ProductPage extends MenuPage {

	@FindBy(css = ".btn_primary.btn_inventory")
	WebElement addToCartBtn;
	
	@FindBy(css = ".inventory_details_back_button")
	WebElement backBtn;
	
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
		sleep(300);
		click(backBtn);
	}
}
