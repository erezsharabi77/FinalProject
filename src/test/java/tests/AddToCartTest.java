//package tests;
//
//import org.testng.annotations.Test;
//import org.testng.Assert;
//
//import io.qameta.allure.Description;
//import io.qameta.allure.Epic;
//import io.qameta.allure.Issue;
//import io.qameta.allure.Severity;
//import io.qameta.allure.SeverityLevel;
//import io.qameta.allure.Story;
//import pageobjects.CompletePage;
//import pageobjects.LoginPage;
//import pageobjects.OverViewPage;
//import pageobjects.ProductsPage;
//import pageobjects.YourCartPage;
//import pageobjects.YourInfoPage;
//import utilities.AllureAttachment;
//
//@Epic("Shopping Cart")
//public class AddToCartTest extends BaseTest1 {
//
//	@Severity(SeverityLevel.NORMAL)
//	@Story("As a User when I add item to Cart than cancel the item should be deleted")
//	@Issue("1")
//	@Description("Add 2 items to Cart than cancel the request")
//	@Test(priority = 1, description = "Add To Cart And Cancel")
//	public void addToCartCancel() throws InterruptedException {
//		LoginPage loginPage = new LoginPage(driver);
//		loginPage.login("standard_user", "secret_sauce");
//
//		ProductsPage pPage = new ProductsPage(driver);
//		pPage.addToCart("Sauce Labs Bolt T-Shirt");
//		pPage.addToCart("Sauce Labs Fleece Jacket");
//		pPage.openCart();
//		YourCartPage ycPage = new YourCartPage(driver);
//		ycPage.checkOut();
//
//		YourInfoPage yipPage = new YourInfoPage(driver);
//		yipPage.addYourInfo("gal", "ester", "555");
//		AllureAttachment.addTextAttachment("Nir Tal Rules");
//		OverViewPage ovPage = new OverViewPage(driver);
//		ovPage.cancel();
//
//		pPage = new ProductsPage(driver);
//		System.out.println(pPage.getNumberOfProductInCart());
//		Assert.assertEquals(pPage.getNumberOfProductInCart(), 2);
//	}
//
//	@Severity(SeverityLevel.CRITICAL)
//	@Story("As a User when I add item to Cart than Confirm there should be order confirmation")
//	@Test(priority = 0, description = "Add To Cart And Confirm")
//	public void addToCartSucceed() {
//		ProductsPage pPage = new ProductsPage(driver);
//		pPage.openCart();
//
//		YourCartPage ycPage = new YourCartPage(driver);
//		ycPage.checkOut();
//
//		YourInfoPage yipPage = new YourInfoPage(driver);
//		yipPage.addYourInfo("gal", "ester", "555");
//
//		OverViewPage ovPage = new OverViewPage(driver);
//		ovPage.finish();
//
//		CompletePage cPage = new CompletePage(driver);
//		Assert.assertEquals(cPage.getMessage(), "THANK YOU FOR YOUR ORDER");
//		Assert.fail();
//	}
//}
