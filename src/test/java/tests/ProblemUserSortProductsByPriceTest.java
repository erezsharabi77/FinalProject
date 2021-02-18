package tests;

import static org.testng.Assert.fail;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import pageobjects.FinishPage;
import pageobjects.LoginPage;
import pageobjects.ProductsPage;
import utils.Utils;

public class ProblemUserSortProductsByPriceTest extends BaseTest {

	@Test(priority= 0, description = "tc01_Login")
	@Description("Login with existing user")
	public void tc01_login() throws IOException
	{
		System.out.println("Problem User - Sort Products by price Test begins");
		System.out.println("*******START LOGIN****************");
		LoginPage lp = new LoginPage(driver);
		lp.login(Utils.readProperty("problemuser"), Utils.readProperty("password"));
		ProductsPage pp = new ProductsPage(driver);
		boolean actual = pp.isProductsPage();
		Assert.assertTrue(actual);
		System.out.println("*******END LOGIN****************");
	}

	@Test(priority= 1,description = "tc02_Count Products")
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
	
	@Test(priority= 2,description = "tc03_Sort Products By Price")
	@Description("Sort products in Products Page by price from low to high")
	public void tc03_sortProductsByPriceLowToHigh() throws IOException
	{
		System.out.println("*******START SORT PRODUCTS BY PRICE****************");
		ProductsPage pp = new ProductsPage(driver);
		
		pp.sortProducts("Price (low to high)");
		
		Assert.assertTrue(pp.AreProductsSortedByPriceLowToHigh());
		System.out.println("*******END SORT PRODUCTS BY PRICE****************");
	}

	@Test(priority= 3,description = "tc04_Logout")
	@Description("Logout from the application")
	public void tc04_logout() throws IOException
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
