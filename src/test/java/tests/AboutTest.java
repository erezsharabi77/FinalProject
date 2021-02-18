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
import pageobjects.ProductsPage;
import pageobjects.YourCartPage;
import utilities.ListenerClass;
import utils.Utils;

@Listeners({ListenerClass.class})
public class AboutTest extends BaseTest {


	@Test(description = "tc01_Login")
	@Description("Login with existing user")
	public void tc01_login() throws IOException
	{
		System.out.println("Buy All products Test begins");
		System.out.println("*******START LOGIN****************");
		LoginPage lp = new LoginPage(driver);
		lp.login(Utils.readProperty("user"), Utils.readProperty("password"));
		ProductsPage pp = new ProductsPage(driver);
		boolean actual = pp.isProductsPage();
//		actual=false;
		Assert.assertTrue(actual);
		System.out.println("*******END LOGIN****************");
	}


	@Test(description = "tc02_About")
	@Description("Click on About and make sure you are in https://saucelabs.com website")
	public void tc02_about() throws IOException
	{
		System.out.println("*******START ABOUT****************");
		ProductsPage pp = new ProductsPage(driver);
		pp.clickMenu();
		pp.about();
		AboutPage ap = new AboutPage(driver);
		String actual = ap.getHeader();
		driver.navigate().back();
		Assert.assertEquals(actual, "For the best customer experience, just add Sauce");
		System.out.println("*******END ABOUT****************");
	}

	

	@Test(description = "tc03_Logout")
	@Description("Logout from the application")
	public void tc03_logout() throws IOException
	{
		System.out.println("*******START LOGOUT****************");
		FinishPage fp = new FinishPage(driver);
		fp.clickMenu();
		fp.logout();
		LoginPage lp = new LoginPage(driver);
		boolean actual = lp.isItLoginPage();

		Assert.assertTrue(actual);
		System.out.println("*******FINISH LOGOUT****************");
	}
}
