package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import pageobjects.CheckoutPage;
import pageobjects.FinishPage;
import pageobjects.LoginPage;
import pageobjects.OverviewPage;
import pageobjects.ProductPage;
import pageobjects.ProductsPage;
import pageobjects.YourCartPage;
import utils.Utils;

public class BuyProductsTest extends BaseTest {


	@Test(description = "tc01_Login")
	@Description("Login with existing user")
	public void tc01_login() throws IOException
	{
		System.out.println("Buy 2 products Test begins");
		System.out.println("*******START LOGIN****************");
		LoginPage lp = new LoginPage(driver);
		lp.login(Utils.readProperty("user"), Utils.readProperty("password"));
		ProductsPage pp = new ProductsPage(driver);
		boolean actual = pp.isProductsPage();
		Assert.assertTrue(actual);
		System.out.println("*******END LOGIN****************");

	}


	@Test(description = "tc02_Count Products")
	@Description("Count products from the Products page and verify it shows 6 products")
	public void tc02_countProducts() throws IOException
	{
		System.out.println("*******START COUNT PRODUCTS****************");
		ProductsPage pp = new ProductsPage(driver);
		int actual = pp.getNoOfProducts();
		int expected = 6;
		Assert.assertEquals(actual, expected);
		System.out.println("*******END COUNT PRODUCTS****************");
	}

	@Test(description = "tc03_Add product to cart")
	@Description("Add first product to the cart")
	public void tc03_addProduct1() throws IOException
	{
		System.out.println("*******START ADD FIRST PRODUCT****************");
		String productName = Utils.readProperty("product1");
		ProductsPage psp = new ProductsPage(driver);
		psp.chooseProduct(productName);

		ProductPage pp = new ProductPage(driver);

		pp.addToCart();
		String i = pp.getcartBadgeNumber();
		int cartCount=Integer.parseInt(i);
		Assert.assertEquals(cartCount, 1);
		pp.back();
		System.out.println("*******END ADD FIRST PRODUCT****************");
	}

	@Test(description = "tc04_Add product to cart")
	@Description("Add second product to the cart")
	public void tc04_addProduct2() throws IOException
	{
		System.out.println("*******START SECOND FIRST PRODUCT****************");
		String productName = Utils.readProperty("product2");
		ProductsPage psp = new ProductsPage(driver);
		psp.chooseProduct(productName);

		ProductPage pp = new ProductPage(driver);

		pp.addToCart();
		String i = pp.getcartBadgeNumber();
		int cartCount=Integer.parseInt(i);
		Assert.assertEquals(cartCount, 2);

		pp.back();
		System.out.println("*******END SECOND FIRST PRODUCT****************");

	}

	@Test(description = "tc05_Checkout")
	@Description("Fill first name, last name, zip code and move to checkout")
	public void tc05_checkout() throws IOException
	{
		System.out.println("*******START CHECKOUT****************");
		ProductsPage psp = new ProductsPage(driver);
		psp.openCart();

		YourCartPage ycp = new YourCartPage(driver);
		String header = ycp.getYourCartPageHeader();
		Assert.assertEquals(header, "Your Cart");

		ycp.checkout();

		CheckoutPage cp = new CheckoutPage(driver);
		cp.insertInfo(Utils.readProperty("firstname"), Utils.readProperty("lastname"), Utils.readProperty("zipcode"));
		OverviewPage ov = new OverviewPage(driver);
		header = ov.getOverviewPageHeader();
		Assert.assertEquals(header, "Checkout: Overview");
		System.out.println("*******END CHECKOUT****************");
	}

	@Test(description = "tc06_Finish Order")
	@Description("Click on finish order to get to the thank you page")
	public void tc06_finishOrder() throws IOException
	{
		System.out.println("*******START FINISH ORDER****************");
		OverviewPage ovp = new OverviewPage(driver);
		ovp.clickFinishBtn();
		FinishPage fp = new FinishPage(driver);
		String actual = fp.getThankYouMsg();
		String expected = "THANK YOU FOR YOUR ORDER";

		Assert.assertEquals(actual, expected);
		System.out.println("*******END FINISH ORDER****************");
	}

	@Test(description = "tc07_Logout")
	@Description("Logout from the application")
	public void tc07_logout() throws IOException
	{
		System.out.println("*******START LOGOUT****************");
		FinishPage fp = new FinishPage(driver);
		fp.clickMenu();
		fp.logout();
		LoginPage lp = new LoginPage(driver);
		boolean actual = lp.isItLoginPage();

		Assert.assertTrue(actual);
		System.out.println("*******END LOGOUT****************");
		
	}
}
