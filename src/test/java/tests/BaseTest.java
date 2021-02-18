package tests;

import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.github.bonigarcia.wdm.WebDriverManager;
import utils.Utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.testng.ITestContext;

public class BaseTest {
	WebDriver driver;

	@BeforeClass
	public void setup(ITestContext testContext) {
		//for ie: 
		//		System.setProperty("webdriver.ie.driver", "C:\\Automation\\Drivers\\IEDriverServer.exe"); 
		//		driver = new InternetExplorerDriver(); 

		//for Firefox 
		//		System.setProperty("webdriver.gecko.driver", "C:\\Automation\\Drivers\\geckodriver.exe"); 
		//		driver = new FirefoxDriver();
		//for Chrome
//		System.setProperty("webdriver.chrome.driver", "C:\\automation\\drivers\\chromedriver.exe");
//		driver = new ChromeDriver();
//		driver.manage().window().maximize();
//		driver.get(Utils.readProperty("url"));
//		System.setProperty("webdriver.chrome.driver", "C:\\automation\\drivers\\chromedriver.exe");
		WebDriverManager.chromedriver().setup();
		ChromeOptions cOptions = new ChromeOptions();
		cOptions.addArguments("disable-infobars");
		driver = new ChromeDriver(cOptions);
        testContext.setAttribute("WebDriver", this.driver);
		driver.manage().window().maximize();
		driver.get(Utils.readProperty("url"));
		System.out.println("BEGIN TEST SET");
//		driver.get("https://www.saucedemo.com/index.html");

		//		TasksPage tp = new TasksPage(driver);
		//		tp.waitingForLoading();
	}

	@AfterClass
	public void tearDown() {
		System.out.println("The flow is completed");
		driver.quit();
	}


}
