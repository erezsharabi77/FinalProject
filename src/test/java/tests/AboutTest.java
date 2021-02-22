package tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import io.qameta.allure.Description;
import pageobjects.AboutPage;
import pageobjects.FinishPage;
import pageobjects.LoginPage;
import pageobjects.ProductsPage;
import utilities.ListenerClass;
import utils.Utils;

@Listeners({ListenerClass.class})
public class AboutTest extends BaseTest {


	@Test(description = "tc01_Login")
	@Description("Login with existing user")
	public void tc01_login() throws IOException
	{
		System.out.println("About Test begins");
		System.out.println("*******START LOGIN****************");
		LoginPage lp = new LoginPage(driver);
		//Login with user=standard_user and password=secret_sauce
		lp.login(Utils.readProperty("user"), Utils.readProperty("password"));
		ProductsPage pp = new ProductsPage(driver);
		//Check if the user is in Products page
		boolean actual = pp.isProductsPage();
//		actual=false;
		Assert.assertTrue(actual);
		System.out.println("*******END LOGIN****************");
	}


	@Test(description = "tc02_About",dependsOnMethods = { "tc01_login" })
	@Description("Click on About and make sure you are in https://saucelabs.com website")
	public void tc02_about() throws IOException
	{
		System.out.println("*******START ABOUT****************");
		ProductsPage pp = new ProductsPage(driver);
		//Click on the 3 small lines of menu (left top hand side of the page)
		pp.clickMenu();
		//Click on "About"
		pp.about();
		AboutPage ap = new AboutPage(driver);
		//Get header of the page to make sure the user is in About page
		String actual = ap.getHeader();
		//Go back from About page to the https://www.saucedemo.com web site
		driver.navigate().back();
		Assert.assertEquals(actual, "For the best customer experience, just add Sauce");
		System.out.println("*******END ABOUT****************");
	}

	

	@Test(description = "tc03_Logout",dependsOnMethods = { "tc01_login", "tc02_about" })
	@Description("Logout from the application")
	public void tc03_logout() throws IOException
	{
		System.out.println("*******START LOGOUT****************");
		FinishPage fp = new FinishPage(driver);
		//Click on the 3 small lines of menu (left top hand side of the page)
		fp.clickMenu();
		//Click on Logout
		fp.logout();
		LoginPage lp = new LoginPage(driver);
		//Make sure the logout has been done successfully by verifying that the user is in Login page
		boolean actual = lp.isItLoginPage();

		Assert.assertTrue(actual);
		System.out.println("*******FINISH LOGOUT****************");
	}
}
