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
public class ProblemUserBuyAllProductsTest extends BaseTest {


	@Test(description = "tc01_Login")
	@Description("Login with existing user")
	public void tc01_login() throws IOException
	{
		System.out.println("Problem User - Buy All products Test begins");
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


	@Test(description = "tc02_Count Products")
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

	@Test(description = "tc03_Add all products")
	@Description("Add all products to cart")
	public void tc03_addAllProduct() throws IOException
	{
		System.out.println("*******START ADD ALL PRODUCTS****************");
		ProductsPage psp = new ProductsPage(driver);
		//Add all products from Products page to cart
		psp.addAllProducts();
		//Get the number from the cart icon (right top side of the page)
		String i = psp.getcartBadgeNumber();
		int cartCount=Integer.parseInt(i);
		//Make sure the cart icon shows 6
		Assert.assertEquals(cartCount, 6);
		System.out.println("*******END ADD ALL PRODUCTS****************");
	}

	@Test(description = "tc04_Checkout")
	@Description("Fill first name, last name, zip code and move to checkout")
	public void tc04_checkout() throws IOException
	{
		System.out.println("*******START CHECKOUT****************");
//		test = report.startTest("Checkout","The purpose of this TC is to fill personal details and move to checkout overview");
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
		//Insert personal details on the checkout page
		cp.insertInfo(Utils.readProperty("firstname"), Utils.readProperty("lastname"), Utils.readProperty("zipcode"));
		OverviewPage ov = new OverviewPage(driver);
		//Get the header from the Overview page
		header = ov.getOverviewPageHeader();
		//Ensure the header shows "Checkout: Overview"
		Assert.assertEquals(header, "Checkout: Overview");
		System.out.println("*******END CHECKOUT****************");
	}

	@Test(description = "tc05_Finish Order")
	@Description("Click on finish order to get to the thank you page")
	public void tc05_finishOrder() throws IOException
	{
		System.out.println("*******START FINISH ORDER****************");
		OverviewPage ovp = new OverviewPage(driver);
		//Click on finish button from the Overview page
		ovp.clickFinishBtn();
		FinishPage fp = new FinishPage(driver);
		//Get the Thank you page message
		String actual = fp.getThankYouMsg();
		String expected = "THANK YOU FOR YOUR ORDER";
		//Ensure the Thank you page message shows "THANK YOU FOR YOUR ORDER"
		Assert.assertEquals(actual,expected);
		System.out.println("*******END FINISH ORDER****************");

	}

	@Test(description = "tc06_Logout")
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
