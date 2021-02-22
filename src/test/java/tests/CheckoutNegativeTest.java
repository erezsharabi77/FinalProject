package tests;

import static org.testng.Assert.fail;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import io.qameta.allure.Description;
import pageobjects.CheckoutPage;
import pageobjects.FinishPage;
import pageobjects.LoginPage;
import pageobjects.ProductPage;
import pageobjects.ProductsPage;
import pageobjects.YourCartPage;
import utils.Utils;

public class CheckoutNegativeTest extends BaseTest {


	@Test(description = "tc01_Login")
	@Description("Login with existing user")
	public void tc01_login() throws IOException
	{
		System.out.println("Checkout negative test begins");
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


	@Test(description = "tc02_Count Products",dependsOnMethods = { "tc01_login" })
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

	@Test(description = "tc03_Add product to cart",dependsOnMethods = { "tc01_login" })
	@Description("Add first product to the cart")
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

	@Test(description = "tc04_Add product to cart",dependsOnMethods = { "tc01_login" })
	@Description("Add second product to the cart")
	public void tc04_addProduct2() throws IOException
	{
		System.out.println("*******START SECOND FIRST PRODUCT****************");
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
		System.out.println("*******END SECOND FIRST PRODUCT****************");

	}

	@Test(description = "tc05_Checkout", dependsOnMethods = { "tc01_login" })
	@Description("Fill first name, last name, zip code and move to checkout")
	public void tc05_checkout() throws IOException
	{
		System.out.println("*******START CHECKOUT****************");
		ProductsPage psp = new ProductsPage(driver);
		//Open cart to get to Your Cart page
		psp.openCart();

		YourCartPage ycp = new YourCartPage(driver);
		//Get the header as appear on Your Cart page
		String header = ycp.getYourCartPageHeader();
		//Make sure the header shows "Your Cart"
		Assert.assertEquals(header, "Your Cart");

		//Click on checkout button from Your Cart page
		ycp.checkout();

		CheckoutPage cp = new CheckoutPage(driver);
		//Click continue without fill any of the personal details fields
		cp.clickContinue();
		//Get error message presented on the page
		String errMsg = cp.getErrMsg();
		//Ensure the error message presented on the page is "Error: First Name is required"
		Assert.assertEquals(errMsg, "Error: First Name is required");
		System.out.println("*******END CHECKOUT****************");
	}


	@Test(description = "tc06_Logout",dependsOnMethods = { "tc01_login"})
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
