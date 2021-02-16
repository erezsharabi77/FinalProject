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


	@Test(description = "Login")
	@Description("Login with existing user")
	public void tc01_login() throws IOException
	{
//		test = report.startTest("Login","Simple Login");
		LoginPage lp = new LoginPage(driver);
		lp.login(Utils.readProperty("user"), Utils.readProperty("password"));
//		test.log(LogStatus.PASS, "Login has been started");
		ProductsPage pp = new ProductsPage(driver);
		boolean actual = pp.isProductsPage();
		Assert.assertTrue(actual);
//		if (!actual) {
//			test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+ "Test Failed");
//			fail();
//		}
//		test.log(LogStatus.PASS, test.addScreenCapture(capture(driver))+ "Login has been done successfully");

	}


	@Test(description = "Count Products")
	@Description("Count products from the Products page and verify it shows 6 products")
	public void tc02_countProducts() throws IOException
	{
//		test = report.startTest("Count Products","Count Products in products page");
		ProductsPage pp = new ProductsPage(driver);
		int actual = pp.getNoOfProducts();
		int expected = 6;
		Assert.assertEquals(actual, expected);
//		if (actual != expected) {
//			test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+ "Test Failed. Actual was " + actual + " while expected was " + expected);
//			fail();
//		}
//		else
//			test.log(LogStatus.PASS, "Count TC has been done successfully");

	}

	@Test(description = "Add product to cart")
	@Description("Add first product to the cart")
	public void tc03_addProduct1() throws IOException
	{
		String productName = Utils.readProperty("product1");
//		test = report.startTest("Add Product","Add product '" + productName + "' to cart");
		ProductsPage psp = new ProductsPage(driver);
		psp.chooseProduct(productName);

		ProductPage pp = new ProductPage(driver);

		pp.addToCart();
		String i = pp.getcartBadgeNumber();
		int cartCount=Integer.parseInt(i);
		Assert.assertEquals(cartCount, 1);
//		if (cartCount == 1) {
//			test.log(LogStatus.PASS, "Product '" + productName + "' was added to cart successfully");
//			test.log(LogStatus.PASS, "Add Product TC was done successfully");
//		}
//		else {
//			test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+ "TC has failed. Product '" + productName + "' was not added to cart");
//			fail();
//		}
		pp.back();

	}

	@Test(description = "Add product to cart")
	@Description("Add second product to the cart")
	public void tc04_addProduct2() throws IOException
	{
		String productName = Utils.readProperty("product2");
//		test = report.startTest("Add Product","Add product '" + productName + "' to cart");
		ProductsPage psp = new ProductsPage(driver);
		psp.chooseProduct(productName);

		ProductPage pp = new ProductPage(driver);

		pp.addToCart();
		String i = pp.getcartBadgeNumber();
		int cartCount=Integer.parseInt(i);
		Assert.assertEquals(cartCount, 2);
//		if (cartCount == 2) {
//			test.log(LogStatus.PASS, "Product '" + productName + "' was added to cart successfully");
//			test.log(LogStatus.PASS, "Add Product TC was done successfully");
//		}
//		else
//		{
//			test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+ "TC has failed. Product '" + productName + "' was not added to cart");
//			fail();
//		}
		pp.back();

	}

	@Test(description = "Checkout")
	@Description("Fill first name, last name, zip code and move to checkout")
	public void tc05_checkout() throws IOException
	{
//		test = report.startTest("Checkout","The purpose of this TC is to fill personal details and move to checkout overview");
		ProductsPage psp = new ProductsPage(driver);
		psp.openCart();

		YourCartPage ycp = new YourCartPage(driver);
		String header = ycp.getYourCartPageHeader();
		Assert.assertEquals(header, "Your Cart");
//		if (header.equalsIgnoreCase("Your Cart")) {
//			test.log(LogStatus.PASS, "Move from Products page to Your cart page was done successfully");
//		}
//		else
//		{
//			test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+ "TC has failed. The user is not in Your Cart page");
//			fail();
//		}

		ycp.checkout();

		CheckoutPage cp = new CheckoutPage(driver);
		cp.insertInfo(Utils.readProperty("firstname"), Utils.readProperty("lastname"), Utils.readProperty("zipcode"));
		OverviewPage ov = new OverviewPage(driver);
		header = ov.getOverviewPageHeader();
		Assert.assertEquals(header, "Checkout: Overview");
//		if (header.equalsIgnoreCase("Checkout: Overview")) {
//			test.log(LogStatus.PASS, "Add Product TC was done successfully");
//		}
//		else {
//			test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+ "TC has failed. The user is not in Overview page");
//			fail();
//		}
	}

	@Test(description = "Finish Order")
	@Description("Click on finish order to get to the thank you page")
	public void tc06_finishOrder() throws IOException
	{
//		test = report.startTest("Finish Order","The purpose of this TC is to complete an order");
		OverviewPage ovp = new OverviewPage(driver);
		ovp.clickFinishBtn();
		FinishPage fp = new FinishPage(driver);
		String actual = fp.getThankYouMsg();
		String expected = "THANK YOU FOR YOUR ORDER";
//		if (actual.equalsIgnoreCase(expected)) {
//			test.log(LogStatus.PASS, "Finish order has been done successfully");
//		}
//		else {
//			test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+ "TC has failed");
//			fail();
//		}
		Assert.assertEquals(actual, expected);
	}

	@Test(description = "Logout")
	@Description("Logout from the application")
	public void tc07_logout() throws IOException
	{
//		test = report.startTest("Logout","The purpose of this TC is to logout");
		FinishPage fp = new FinishPage(driver);
		fp.clickMenu();
		fp.logout();
		LoginPage lp = new LoginPage(driver);
		boolean actual = lp.isItLoginPage();
//		if (actual) {
//			test.log(LogStatus.PASS, "logout was done successfully");
//		}
//		else {
//			test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+ "TC has failed. Logout was failed");
//			fail();
//		}
		Assert.assertTrue(actual);
	}
}
