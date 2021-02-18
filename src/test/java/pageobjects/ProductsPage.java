package pageobjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import io.qameta.allure.Step;

public class ProductsPage extends MenuPage {

	@FindBy(css = ".inventory_item_name")
	List<WebElement> list;

	@FindBy(css = ".inventory_item_price")
	List<WebElement> priceList;

	@FindBy(css = ".product_label")
	WebElement productsLabel;

	@FindBy(css = ".btn_primary.btn_inventory")
	List<WebElement> addToCartlist;


	public ProductsPage(WebDriver driver) {
		super(driver);
	}

	@Step("Choose/Click on product: {name}")
	public void chooseProduct(String name) {
		//		List<WebElement> list = driver.findElements(By.cssSelector(".inventory_item_name"));
		for (WebElement elem : list) {
			if (elem.getText().equalsIgnoreCase(name)) {
				click(elem);
				break;
			}
		}
	}
	@Step("Add all products to cart")
	public void addAllProducts() {
		for (WebElement elem : addToCartlist) {
			click(elem);
			Assert.assertEquals(getText(elem), "REMOVE");
		}
	}

	@Step("Get number of products")
	public int getNoOfProducts() {
		//		List<WebElement> list = driver.findElements(By.cssSelector(".inventory_item_name"));
		sleep(1000);
		return list.size();
	}

	@Step("Check if the user is on the Products page")
	public boolean isProductsPage()
	{
		if(getText(productsLabel).equalsIgnoreCase("Products"))
			return true;
		else
			return false;

	}

	@Step("Sort all products by: {visibleText}")
	public void sortProducts(String visibleText)
	{
		Select drpSort = new Select (driver.findElement(By.cssSelector(".product_sort_container")));
		drpSort.selectByVisibleText(visibleText);
	}

	@Step("Validate all products are sorted by price from low to high")
	public boolean AreProductsSortedByPriceLowToHigh()
	{
		String str = priceList.get(0).getText();
		str = str.substring(1, str.length());
		double lowPrice = Double.parseDouble(str);
		//		int lowPrice =Integer.parseInt(str);
		for (WebElement el : priceList) {
			str = el.getText().substring(1, el.getText().length());
			if (lowPrice <= Double.parseDouble(str)) {
				System.out.println(lowPrice + " is lower or equal to " + el.getText());
			}
			else
				return false;
			lowPrice = Double.parseDouble(el.getText().substring(1, el.getText().length()));
		}
		return true;
	}
}
