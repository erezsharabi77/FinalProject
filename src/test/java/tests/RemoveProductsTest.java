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
		System.out.println("Remove All Products Test begins");
		System.out.println("*******START LOGIN****************");
		LoginPage lp = new LoginPage(driver);
		//Login with user=standard_user and password=secret_sauce
		lp.login(Utils.readProperty("user"), Utils.readProperty("password"));
		ProductsPage pp = new ProductsPage(driver);
		//Check if the user is in Products page
		boolean actual = pp.isProductsPage();
		Assert.assertTrue(actual);
		System.out.println("*******END LOGIN****************");
	}


	@Test(description = "tc02_Count Products", dependsOnMethods = { "tc01_login"})
	@Description("Count Products on the products pages")
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

	@Test(description = "tc03_Add Product", dependsOnMethods = { "tc01_login"})
	@Description("Add first product to cart")
	public void tc03_addProduct1() throws IOException
	{
		System.out.println("*******START ADD FIRST PRODUCT****************");
		//Get product name from configuration.properties file under data package
		String productName = Utils.readProperty("product1");
		ProductsPage psp = new ProductsPage(driver);
		//Click on the product to get to its page
		psp.chooseProduct(productName);

		ProductPage pp = new ProductPage(driver);
		//Get product name from the Product page and ensure it's equal to the product name as appears in Products page
		String prdName = pp.getProductName();
		Assert.assertEquals(prdName, productName);
		//Click on Add to cart button
		pp.addToCart();
		//Get the number of products from the cart icon
		String i = pp.getcartBadgeNumber();
		int cartCount=Integer.parseInt(i);
		//Make sure the cart icon shows 1
		Assert.assertEquals(cartCount, 1);
		//Go back to the Products page
		pp.back();
		System.out.println("*******END ADD FIRST PRODUCT****************");
	}

	@Test(description = "tc04_Add Product", dependsOnMethods = { "tc01_login"})
	@Description("Add second product to cart")
	public void tc04_addProduct2() throws IOException
	{
		System.out.println("*******START ADD SECOND PRODUCT****************");
		//Get product name from configuration.properties file under data package
		String productName = Utils.readProperty("product2");
		ProductsPage psp = new ProductsPage(driver);
		//Click on the product to get to its page
		psp.chooseProduct(productName);

		ProductPage pp = new ProductPage(driver);
		//Get product name from the Product page and ensure it's equal to the product name as appears in Products page
		String prdName = pp.getProductName();
		Assert.assertEquals(prdName, productName);
		//Click on Add to cart button
		pp.addToCart();
		//Get the number of products from the cart icon
		String i = pp.getcartBadgeNumber();
		int cartCount=Integer.parseInt(i);
		//Make sure the cart icon shows 1
		Assert.assertEquals(cartCount, 2);
		//Go back to the Products page
		pp.back();
		System.out.println("*******END ADD SECOND PRODUCT****************");

	}

	@Test(description = "tc05_Remove all products", dependsOnMethods = { "tc01_login", "tc03_addProduct1", "tc04_addProduct2"})
	@Description("Remove all products from your cart page")
	public void tc05_removeAllProduct() throws IOException
	{
		System.out.println("*******START REMOVE ALL PRODUCTS****************");
		ProductsPage psp = new ProductsPage(driver);
		//Click on the cart icon to open cart and get to Your Cart page
		psp.openCart();

		YourCartPage ycp = new YourCartPage(driver);
		//Remove all products from Your Cart page
		ycp.removeAllProduct();
		//Make sure there are no products listed in the Your Cart page
		Assert.assertTrue(!ycp.areProductsExist());
		System.out.println("*******END REMOVE ALL PRODUCTS****************");
	}


	@Test(description = "tc06_Logout", dependsOnMethods = { "tc01_login"})
	@Description("Logout from the application")
	public void tc06_logout() throws IOException
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
