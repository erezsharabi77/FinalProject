package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.qameta.allure.Step;

public class LoginPage extends BasePage {
	
	@FindBy(css = "#user-name")
	WebElement user;
	
	@FindBy(css = "#password")
	WebElement pass;
	
	@FindBy(css = "#login-button")
	WebElement loginBtn;
	
	@FindBy(css = ".login-box h3")
	WebElement loginErrMsg;
	
	
	public LoginPage(WebDriver driver) {
		super(driver);
//		user = driver.findElement(By.cssSelector("#user-name"));
//		pass = driver.findElement(By.cssSelector("#password"));
//		loginBtn = driver.findElement(By.cssSelector("#login-button"));
		
	}

	@Step("Login with user: {username}, password: {password} and click on Login button")
	public void login(String username, String password) {
		fillText(user, username);
		fillText(pass, password);
		click(loginBtn);
	}
	
	@Step("Get login error message after typing an invalid username or password")
	public String getLoginErrMsg()
	{
		return getText(loginErrMsg);
	}
	
	@Step("Check if you are in Login page")
	public boolean isItLoginPage()
	{
		return isExist(loginBtn);
	}
}
