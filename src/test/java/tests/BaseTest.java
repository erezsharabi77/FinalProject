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
	public static ExtentTest test;
	public static ExtentReports report;
	static String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(Calendar.getInstance().getTime());
	
//	@BeforeTest
//	public void startReport(){
//		
//		report = new ExtentReports (System.getProperty("user.dir") + "/test-output/" + "Execution_" + timeStamp + "/FinalProjectExtentReport.html", true);
//		//extent.addSystemInfo("Environment","Environment Name")
//		report
//		.addSystemInfo("Host Name", "EREZ SHARABI SUITE")
//		.addSystemInfo("Environment", "Automation Testing")
//		.addSystemInfo("User Name", "Erez Sharabi");
//		//loading the external xml file (i.e., extent-config.xml) which was placed under the base directory
//		//You could find the xml file below. Create xml file in your project and copy past the code mentioned below
//		report.loadConfig(new File(System.getProperty("user.dir")+"\\extent-config.xml"));
//
//	}

//	@AfterTest
//	public void endReport(){
//		// writing everything to document
//		//flush() - to write or update test information to your report. 
//		report.flush();
//		//Call close() at the very end of your session to clear all resources. 
//		//If any of your test ended abruptly causing any side-affects (not all logs sent to ExtentReports, information missing), this method will ensure that the test is still appended to the report with a warning message.
//		//You should call close() only once, at the very end (in @AfterSuite for example) as it closes the underlying stream. 
//		//Once this method is called, calling any Extent method will throw an error.
//		//close() - To close all the operation
//		report.close();
//	}


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
		System.setProperty("webdriver.chrome.driver", "C:\\automation\\drivers\\chromedriver.exe"); 
		ChromeOptions cOptions = new ChromeOptions();
		cOptions.addArguments("disable-infobars");
		driver = new ChromeDriver(cOptions);
        testContext.setAttribute("WebDriver", this.driver);
		driver.manage().window().maximize();
		driver.get(Utils.readProperty("url"));
		System.out.println("1");
//		driver.get("https://www.saucedemo.com/index.html");

		//		TasksPage tp = new TasksPage(driver);
		//		tp.waitingForLoading();
	}

	@AfterClass
	public void tearDown() {
		System.out.println("The flow is completed");
		driver.quit();
	}

	/*
	 * This method will run after wach test,
	 * it will take screen shot only for tests that failed
	 */
//	@AfterMethod
//	public void getResult(ITestResult result){
//		if(result.getStatus() == ITestResult.FAILURE){
//			test.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
//			test.log(LogStatus.FAIL, "Test Case Failed is "+result.getThrowable());
//		}else if(result.getStatus() == ITestResult.SKIP){
//			test.log(LogStatus.SKIP, "Test Case Skipped is "+result.getName());
//		}
//		// ending test
//		//endTest(logger) : It ends the current test and prepares to create HTML report
//		report.endTest(test);
//	}

//	public static String capture(WebDriver driver) throws IOException {
//		File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
////		File Dest = new File("src/../ErrImages/" + System.currentTimeMillis()+ ".png");
//		String str = UUID.randomUUID().toString();
//		File Dest = new File(System.getProperty("user.dir") + "/test-output/" + "Execution_" + timeStamp + "/" + str + ".png");
//		String errflpath = Dest.getAbsolutePath();
//		FileUtils.copyFile(scrFile, Dest);
//		return errflpath;
//	}
}
