package tests;

import org.testng.annotations.Test;
import org.xml.sax.SAXException;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import atu.testrecorder.ATUTestRecorder;
import atu.testrecorder.exceptions.ATUTestRecorderException;
import pages.Main;
import utilites.GetDriver;
import utilites.Utilities;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

import javax.xml.parsers.ParserConfigurationException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.WebDriver;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;


public class CareerPageTest {

	// Global variables 
	// Add extent reports
	private ExtentReports extent;
	private ExtentTest myTest;
	private static String reportPath = System.getProperty("user.dir") + "\\test-output\\CareerPage_Report.html";
	private static String videoPath = System.getProperty("user.dir") + "\\test-output\\videos";

	private WebDriver driver;
	private String baseUrl;
	
	
	//pages
	private Main main;
	
	
	private static final Logger logger = LogManager.getLogger(CareerPageTest.class);
	ATUTestRecorder recorder;
	

	@BeforeClass
	public void beforeClass() throws ParserConfigurationException, SAXException, IOException, ATUTestRecorderException {
		PropertyConfigurator.configure(System.getProperty("user.dir") + "/log4j.properties");

		extent = new ExtentReports(reportPath);
		extent.loadConfig(new File(System.getProperty("user.dir") + "\\resources\\extent-config.xml"));
		
		baseUrl = Utilities.getDataFromXML("info.xml", "website", 0);
		String browser =Utilities.getDataFromXML("info.xml", "browser", 0);;
		
		driver = GetDriver.getDriver(browser, baseUrl);
		
		main = new Main(driver);

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
	

	/*  Prerequisite: Getting into https://www.10bis.com/
	 * 		Given: Client is in site 
	 * 		When: Click on Career Page
	 *  	Then: Getting into career page
	 */
	
	@Test(priority = 1, enabled = true, description = "Verify career page field")
	public void goToCareerPage() throws InterruptedException, IOException {
		logger.info("Going to Career page");
		main.careerPage();
		//Assert.assertTrue(incognito_Registeration_Error.alreadyOwnAccount());
		logger.info("Successfully changed to Career Page by driver.switchTo");

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
