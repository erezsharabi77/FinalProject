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


	@Test(description = "Login")
	@Description("Login with an existing user and password")
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
	@Description("Count Products on the products pages")
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

	@Test(description = "Add Product")
	@Description("Add first product to cart")
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

	@Test(description = "Add Product")
	@Description("Add second product to cart")
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

	@Test(description = "Remove all products")
	@Description("Remove all products from your cart page")
	public void tc05_removeAllProduct() throws IOException
	{
//		test = report.startTest("Checkout","The purpose of this TC is to remove all products in the checkout page");
		ProductsPage psp = new ProductsPage(driver);
		psp.openCart();

		YourCartPage ycp = new YourCartPage(driver);
		ycp.removeAllProduct();
		Assert.assertTrue(!ycp.areProductsExist());
//		if (ycp.areProductsExist()) {
//			test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+ "TC has failed. Products still exist although they should have all been removed");
//			fail();
//		}
//		else
//			test.log(LogStatus.PASS, "All products were removed successfully");
	}


	@Test(description = "Logout")
	@Description("Logout from the application")
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
