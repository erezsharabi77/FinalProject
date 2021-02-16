package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import pageobjects.CheckoutPage;
import pageobjects.FinishPage;
import pageobjects.LoginPage;
import pageobjects.OverviewPage;
import pageobjects.ProductsPage;
import pageobjects.YourCartPage;
import utilities.ListenerClass;
import utils.Utils;

@Listeners({ListenerClass.class})
public class BuyAllProductsTest extends BaseTest {


	@Test(description = "tc01_Login")
	@Description("Login with existing user")
	public void tc01_login() throws IOException
	{
		LoginPage lp = new LoginPage(driver);
		lp.login(Utils.readProperty("user"), Utils.readProperty("password"));
		ProductsPage pp = new ProductsPage(driver);
		boolean actual = pp.isProductsPage();
//		actual=false;
		Assert.assertTrue(actual);

	}


	@Test(description = "tc02_Count Products")
	@Description("Count products from the Products page and verify it shows 6 products")
	public void tc02_countProducts() throws IOException
	{
		ProductsPage pp = new ProductsPage(driver);
		int actual = pp.getNoOfProducts();
		int expected = 6;
		Assert.assertEquals(actual, expected);
	}

	@Test(description = "tc03_Add all products")
	@Description("Add all products to cart")
	public void tc03_addAllProduct() throws IOException
	{
//		test = report.startTest("Add all Products","Add all products to the cart");
		ProductsPage psp = new ProductsPage(driver);
		psp.addAllProducts();

		String i = psp.getcartBadgeNumber();
		int cartCount=Integer.parseInt(i);
		Assert.assertEquals(cartCount, 6);
	}

	@Test(description = "tc04_Checkout")
	@Description("Fill first name, last name, zip code and move to checkout")
	public void tc04_checkout() throws IOException
	{
//		test = report.startTest("Checkout","The purpose of this TC is to fill personal details and move to checkout overview");
		ProductsPage psp = new ProductsPage(driver);
		psp.openCart();

		YourCartPage ycp = new YourCartPage(driver);
		String header = ycp.getYourCartPageHeader();
		Assert.assertEquals(header, "Your Cart");


		//Click on checkout button from Your Cart page
		ycp.checkout();

		CheckoutPage cp = new CheckoutPage(driver);
		cp.insertInfo(Utils.readProperty("firstname"), Utils.readProperty("lastname"), Utils.readProperty("zipcode"));
		OverviewPage ov = new OverviewPage(driver);
		header = ov.getOverviewPageHeader();
		Assert.assertEquals(header, "Checkout: Overview");

	}

	@Test(description = "tc05_Finish Order")
	@Description("Click on finish order to get to the thank you page")
	public void tc05_finishOrder() throws IOException
	{
		OverviewPage ovp = new OverviewPage(driver);
		ovp.clickFinishBtn();
		FinishPage fp = new FinishPage(driver);
		String actual = fp.getThankYouMsg();
		String expected = "THANK YOU FOR YOUR ORDER";
		Assert.assertEquals(actual,expected);
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
