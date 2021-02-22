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
		//Login with user=standard_user and password=secret_sauce
		lp.login(Utils.readProperty("problemuser"), Utils.readProperty("password"));
		ProductsPage pp = new ProductsPage(driver);
		//Check if the user is in Products page
		boolean actual = pp.isProductsPage();
		Assert.assertTrue(actual);
		System.out.println("*******END LOGIN****************");
	}

	@Test(priority= 1,description = "tc02_Count Products", dependsOnMethods = { "tc01_login"})
	@Description("Count products from the Products page and verify it shows 6 products")
	public void tc02_countProducts() throws IOException
	{
		System.out.println("*******START COUNT PRODUCTS****************");
		ProductsPage pp = new ProductsPage(driver);
		//Get number of products appear in Products page
		int actual = pp.getNoOfProducts();
		int expected = 6;
		//Make sure there are 6 actual products in Products page
		Assert.assertEquals(actual, expected);
		System.out.println("*******END COUNT PRODUCTS****************");
	}
	
	@Test(priority= 2,description = "tc03_Sort Products By Price",dependsOnMethods = { "tc01_login"})
	@Description("Sort products in Products Page by price from low to high")
	public void tc03_sortProductsByPriceLowToHigh() throws IOException
	{
		System.out.println("*******START SORT PRODUCTS BY PRICE****************");
		ProductsPage pp = new ProductsPage(driver);
		//Select "Price (low to high)" from the drop down
		pp.sortProducts("Price (low to high)");
		//Make sure the products are sorted from low to high
		Assert.assertTrue(pp.AreProductsSortedByPriceLowToHigh());
		System.out.println("*******END SORT PRODUCTS BY PRICE****************");
	}

	@Test(priority= 3,description = "tc04_Logout",dependsOnMethods = { "tc01_login"})
	@Description("Logout from the application")
	public void tc04_logout() throws IOException
	{
		System.out.println("*******START LOGOUT****************");
		FinishPage fp = new FinishPage(driver);
		//Click on the 3 small lines of menu (left top hand side of the page)
		fp.clickMenu();
		//Click on logout from the menu page
		fp.logout();
		LoginPage lp = new LoginPage(driver);
		//Make sure the logout has been done successfully by verifying that the user is in Login page
		boolean actual = lp.isItLoginPage();

		Assert.assertTrue(actual);
		System.out.println("*******FINISH LOGOUT****************");
	}
}
