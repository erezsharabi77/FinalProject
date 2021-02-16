package tests;

import static org.testng.Assert.fail;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import pageobjects.CheckoutPage;
import pageobjects.FinishPage;
import pageobjects.LoginPage;
import pageobjects.ProductPage;
import pageobjects.ProductsPage;
import pageobjects.YourCartPage;
import utils.Utils;

public class CheckoutNegativeTest extends BaseTest {


	@Test
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


	@Test
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

	@Test
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

	@Test
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
//		else {
//			test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+ "TC has failed. Product '" + productName + "' was not added to cart");
//			fail();
//		}
		pp.back();

	}

	@Test
	public void tc05_checkout() throws IOException
	{
//		test = report.startTest("Checkout Negative","The purpose of this Negative TC is to keep the personal fields empty and try to move to checkout overview");
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

		//Click on checkout button from Your Cart page
		ycp.checkout();

		CheckoutPage cp = new CheckoutPage(driver);
		//Click continue without fill any of the personal details fields
		cp.clickContinue();
		//Get error message presented on the page
		String errMsg = cp.getErrMsg();
		Assert.assertEquals(errMsg, "Error: First Name is required");
//		if (errMsg.equalsIgnoreCase("Error: First Name is required")) {
//			test.log(LogStatus.PASS, test.addScreenCapture(capture(driver))+ "Negative checkout TC was passed successfully");
//		}
//		else {
//			test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+ "TC has failed");
//			fail();
//		}
	}


	@Test
	public void tc06_logout() throws IOException
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
