package tests;

import static org.testng.Assert.fail;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import io.qameta.allure.Description;
import pageobjects.FinishPage;
import pageobjects.LoginPage;
import pageobjects.ProductPage;
import pageobjects.ProductsPage;
import pageobjects.YourCartPage;
import utils.Utils;

public class RemoveProductsTest extends BaseTest {


	@Test(description = "tc01_Login")
	@Description("Login with an existing user and password")
	public void tc01_login() throws IOException
	{
		LoginPage lp = new LoginPage(driver);
		lp.login(Utils.readProperty("user"), Utils.readProperty("password"));
		ProductsPage pp = new ProductsPage(driver);
		boolean actual = pp.isProductsPage();
		Assert.assertTrue(actual);
	}


	@Test(description = "tc02_Count Products")
	@Description("Count Products on the products pages")
	public void tc02_countProducts() throws IOException
	{
		ProductsPage pp = new ProductsPage(driver);
		int actual = pp.getNoOfProducts();
		int expected = 6;
		Assert.assertEquals(actual, expected);
	}

	@Test(description = "tc03_Add Product")
	@Description("Add first product to cart")
	public void tc03_addProduct1() throws IOException
	{
		String productName = Utils.readProperty("product1");
		ProductsPage psp = new ProductsPage(driver);
		psp.chooseProduct(productName);

		ProductPage pp = new ProductPage(driver);

		pp.addToCart();
		String i = pp.getcartBadgeNumber();
		int cartCount=Integer.parseInt(i);
		Assert.assertEquals(cartCount, 1);
		pp.back();

	}

	@Test(description = "tc04_Add Product")
	@Description("Add second product to cart")
	public void tc04_addProduct2() throws IOException
	{
		String productName = Utils.readProperty("product2");
		ProductsPage psp = new ProductsPage(driver);
		psp.chooseProduct(productName);

		ProductPage pp = new ProductPage(driver);

		pp.addToCart();
		String i = pp.getcartBadgeNumber();
		int cartCount=Integer.parseInt(i);
		Assert.assertEquals(cartCount, 2);
		pp.back();

	}

	@Test(description = "tc05_Remove all products")
	@Description("Remove all products from your cart page")
	public void tc05_removeAllProduct() throws IOException
	{
		ProductsPage psp = new ProductsPage(driver);
		psp.openCart();

		YourCartPage ycp = new YourCartPage(driver);
		ycp.removeAllProduct();
		Assert.assertTrue(!ycp.areProductsExist());
	}


	@Test(description = "tc06_Logout")
	@Description("Logout from the application")
	public void tc06_logout() throws IOException
	{
		FinishPage fp = new FinishPage(driver);
		fp.clickMenu();
		fp.logout();
		LoginPage lp = new LoginPage(driver);
		boolean actual = lp.isItLoginPage();
		Assert.assertTrue(actual);
	}
}
