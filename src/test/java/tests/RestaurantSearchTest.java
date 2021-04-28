package tests;

import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import pages.Login;
import pages.Main;
import pages.Restaurant;
import utilites.GetDriver;
import utilites.Utilities;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;


public class RestaurantSearchTest {

	// Global variables 
	// Add extent reports
	private ExtentReports extent;
	private ExtentTest myTest;
	private static String reportPath = System.getProperty("user.dir") + "\\test-output\\RestaurantSearchReport.html";
	private static String videoPath = System.getProperty("user.dir") + "\\test-output\\videos";
	ATUTestRecorder recorder;
	private WebDriver driver;

	//pages
	private Main main;
	private Login login;
	private Restaurant restaurant;
	
	private static final Logger logger = LogManager.getLogger(RestaurantSearchTest.class);
	private static String userName;
	private static String password;
	private static String browser;
	private static String baseUrl;
	private static String FBName;
	private static String rName;
	

	@BeforeClass
	public void beforeClass() throws ParserConfigurationException, SAXException, IOException {
		PropertyConfigurator.configure(System.getProperty("user.dir") + "/log4j.properties");

		extent = new ExtentReports(reportPath);
		extent.loadConfig(new File(System.getProperty("user.dir") + "\\resources\\extent-config.xml"));
		
		baseUrl = Utilities.getDataFromXML("info.xml", "website", 0);
		browser = Utilities.getDataFromXML("info.xml", "browser", 0);
		userName = Utilities.getDataFromXML("info.xml", "userName", 0);
		password = Utilities.getDataFromXML("info.xml", "password", 0);
		FBName = Utilities.getDataFromXML("info.xml", "FBName", 0);
		rName = Utilities.getDataFromXML("info.xml", "rName", 0);
		
		
		driver = GetDriver.getDriver(browser, baseUrl, userName);
		
		main = new Main(driver);
		login = new Login(driver);
		restaurant = new Restaurant(driver);
	}

	
	
	@BeforeMethod
	public void beforeMethod(Method method) throws IOException, ATUTestRecorderException {
		myTest = extent.startTest(method.getName());
		myTest.log(LogStatus.INFO, "Starting test", "Start test");
		DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd HH-mm-ss");
		Date date = new Date();
		recorder = new ATUTestRecorder(videoPath, "Test " + this.getClass().getSimpleName() + " "+ dateFormat.format(date),false);
		recorder.start();
	}
	

	
	/*  Prerequisite: Getting into https://www.10bis.co.il/
	 * 		Given: Client click Login   
	 * 		When: Give Facebook login details and click login
	 *  	Then: Getting into 10bis as registered user
	 */
	
	
	@Test(priority = 1, enabled = true, description = "Login 10bis using Facebook")
	public void LoginUsingFacebook() throws InterruptedException, IOException, ParserConfigurationException, SAXException {

		logger.info("Going to connection page");
		main.login();
		logger.info("Going to login with facebook details");
		Assert.assertTrue(login.doLoginFacebook(FBName, password, userName), "Could not login with Facebook account, Check your logs");
		logger.info("Successfully Got into 10bis as registered user page (Facebook Login)");

	}
	
	/*  Prerequisite: Logged in https://www.10bis.co.il/
	 * 		Given: Client click on Search bar   
	 * 		When: Typing restaurant name and pressing enter
	 *  	Then: Getting into the restaurant page
	 */
	
	@Test(priority = 2, enabled = true, description = "Searching for BSR Resturant")
	public void SearchRestaurant() throws InterruptedException, IOException, ParserConfigurationException, SAXException {

		logger.info("Typing Resturant name in search bar");
		main.restaurant();
		logger.info("Going to restaurant page");
		Assert.assertTrue(restaurant.restaurantSearch(rName), "Could not match your Resturant name with the Resturant that you chose, Check your logs");
		logger.info("Successfully Got into the resturant page you wanted!");

	}
	
	
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException, ATUTestRecorderException {

		if (result.getStatus() == ITestResult.FAILURE) {
			myTest.log(LogStatus.FAIL, "Test failed: " + result.getName());
			myTest.log(LogStatus.FAIL, "Test failed reason: " + result.getThrowable());
			myTest.log(LogStatus.FAIL, myTest.addScreenCapture(Utilities.takeScreenShot(driver)));
		}
		else {
			myTest.log(LogStatus.PASS, result.getName(), "Verify successful ");
			myTest.log(LogStatus.PASS, myTest.addScreenCapture(Utilities.takeScreenShot(driver)));

		}

		myTest.log(LogStatus.INFO, "Finish test", "Finish test ");
		extent.endTest(myTest);
		recorder.stop();
		//return to base URL 
		//driver.get(baseUrl);
	}

	@AfterClass
	public void afterClass() {
		extent.flush();
		extent.close();
		driver.quit();

	}
	
}
