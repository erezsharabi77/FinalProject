package tests;

import java.io.IOException;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import pageobjects.AboutPage;
import pageobjects.CheckoutPage;
import pageobjects.FinishPage;
import pageobjects.LoginPage;
import pageobjects.OverviewPage;
import pageobjects.ProductPage;
import pageobjects.ProductsPage;
import pageobjects.YourCartPage;
import utilities.ListenerClass;
import utils.Utils;

@Listeners({ListenerClass.class})
public class ProblemUserBuyProductTest extends BaseTest {


	@Test(description = "tc01_Login")
	@Description("Login with existing user")
	public void tc01_login() throws IOException
	{
		System.out.println("Problem User - Buy Product Test begins");
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


	@Test(description = "tc02_Add product to cart",dependsOnMethods = { "tc01_login"})
	@Description("Add product to the cart")
	public void tc02_addProduct() throws IOException
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

	@Test(description = "tc03_Checkout", dependsOnMethods = { "tc01_login" })
	@Description("Fill first name, last name, zip code and move to checkout")
	public void tc03_checkout() throws IOException
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
		//Insert personal details on the checkout page
		cp.insertInfo(Utils.readProperty("firstname"), Utils.readProperty("lastname"), Utils.readProperty("zipcode"));
		OverviewPage ov = new OverviewPage(driver);
		//Get the header from the Overview page
		header = ov.getOverviewPageHeader();
		//Ensure the header shows "Checkout: Overview"
		Assert.assertEquals(header, "Checkout: Overview");
		System.out.println("*******END CHECKOUT****************");
	}

	@Test(description = "tc04_Finish Order", dependsOnMethods = { "tc01_login" })
	@Description("Click on finish order to get to the thank you page")
	public void tc04_finishOrder() throws IOException
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

	@Test(description = "tc05_Logout",dependsOnMethods = { "tc01_login"})
	@Description("Logout from the application")
	public void tc05_logout() throws IOException
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
