package tests;

import static org.testng.Assert.fail;

import java.io.IOException;

import org.testng.annotations.Test;

import pageobjects.FinishPage;
import pageobjects.LoginPage;
import pageobjects.ProductsPage;
import utils.Utils;

public class SortProductsByPriceTest extends BaseTest {

	@Test
	public void tc01_login() throws IOException
	{
//		test = report.startTest("Login","Simple Login");
		LoginPage lp = new LoginPage(driver);
		lp.login(Utils.readProperty("user"), Utils.readProperty("password"));
//		test.log(LogStatus.PASS, "Login has been started");
		ProductsPage pp = new ProductsPage(driver);
		boolean actual = pp.isProductsPage();
		//		Assert.assertTrue(actual);
		if (!actual) {
//			test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+ "Test Failed");
			fail();
		}
//		test.log(LogStatus.PASS, test.addScreenCapture(capture(driver))+ "Login has been done successfully");

	}

	@Test
	public void tc02_countProducts() throws IOException
	{
//		test = report.startTest("Count Products","Count Products in products page");
		ProductsPage pp = new ProductsPage(driver);
		int actual = pp.getNoOfProducts();
		int expected = 6;
		//		Assert.assertEquals(actual, expected);
		if (actual != expected) {
//			test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+ "Test Failed. Actual was " + actual + " while expected was " + expected);
			fail();
		}
//		else
//			test.log(LogStatus.PASS, "Count TC has been done successfully");

	}
	
	@Test
	public void tc03_sortProductsByPriceLowToHigh() throws IOException
	{
//		test = report.startTest("Sort Products Price","The purpose of this TC is to sort all products by their price from low to high");
		ProductsPage pp = new ProductsPage(driver);
		
		pp.sortProducts("Price (low to high)");
		
		if (pp.AreProductsSortedByPriceLowToHigh()) {
//			test.log(LogStatus.PASS, "All products are sorted from low to high");
		}
		else
		{
//			test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+ "Test Failed");
			fail();
		}
//		int actual = pp.getNoOfProducts();
//		int expected = 6;
//		//		Assert.assertEquals(actual, expected);
//		if (actual != expected) {
//			test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+ "Test Failed. Actual was " + actual + " while expected was " + expected);
//			fail();
//		}
//		else
//			test.log(LogStatus.PASS, "Count TC has been done successfully");

	}

	@Test
	public void tc04_logout() throws IOException
	{
//		test = report.startTest("Logout","The purpose of this TC is to logout");
		FinishPage fp = new FinishPage(driver);
		fp.clickMenu();
		fp.logout();
		LoginPage lp = new LoginPage(driver);
		boolean actual = lp.isItLoginPage();
		if (actual) {
//			test.log(LogStatus.PASS, "logout was done successfully");
		}
		else {
//			test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+ "TC has failed. Logout was failed");
			fail();
		}
		//		Assert.assertTrue(actual);
	}
}
