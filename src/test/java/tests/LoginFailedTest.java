package tests;

import static org.testng.Assert.fail;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import pageobjects.CheckoutPage;
import pageobjects.LoginPage;
import pageobjects.ProductPage;
import pageobjects.ProductsPage;
import pageobjects.YourCartPage;
import utils.Excel;

public class LoginFailedTest extends BaseTest {
	
	
	@Test(dataProvider="getDataFromExcel", description="Use incorect login information")
	public void tc01_loginFailed(String user,String password) throws IOException
	{
//		test = report.startTest("Login Negative Test","The purpose of this TC is to use incorect login information");		
		LoginPage lp = new LoginPage(driver);
//		lp.login("standard_user", "secret_sauce");
		lp.login(user, password);
		
		String expected = "Epic sadface: Username and password do not match any user in this service";
		String actual = lp.getLoginErrMsg();
//		if (actual.equalsIgnoreCase(expected)) {
//			test.log(LogStatus.PASS, "login Negative test was passed successfully");
//		}
//		else {
//			test.log(LogStatus.FAIL,test.addScreenCapture(capture(driver))+ "TC has failed. login Negative test was failed");
//			fail();
//		}
		
		Assert.assertEquals(actual, expected);
		
	}
	
	/*
	 * This is example of a method that return 2 dimensional object (like a table)
	 * Using the @DataProvider the method above will get the parameters for each iteration.
	 */
	@DataProvider
	public Object[][] getData(){
		Object[][] myData = {
				{"user1","123"},
				{"gal","123"},
				{"yonit","1#444"},
				{"gal","123456878"},
		};
		return myData;
	}
	
	/*
	 * Use the Excel class to read data from excel
	 */
	@DataProvider
	public Object[][] getDataFromExcel(){
		String excelPath = System.getProperty("user.dir") + "\\src/test/java\\data\\input.xlsx";//System.getProperty("user.dir") + "\\src\\eply\\com\\data\\input.xlsx";
		System.out.println(excelPath);
		Object[][] table = Excel.getTableArray(excelPath, "Login");
		return table;
	}
}
