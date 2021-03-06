package pages;

import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class Login extends Base {

	public Login(WebDriver driver) {
		super(driver);
	}

	// start login
	public boolean doLoginFacebook(String FBName, String password, String userName) throws InterruptedException {

		String baseHandle = driver.getWindowHandle();
		
		//Click Facebook image
		click(By.xpath("//img[@type='facebook']"));
		
		//switch to Facebook login window
		
		Thread.sleep(1000);
		Set<String> handels = driver.getWindowHandles();

		for (String h : handels) {
			if (!h.equals(baseHandle))
				driver.switchTo().window(h);
		}
		
		//type user email from XML
		driver.findElement(By.id("email")).sendKeys(userName);
		Thread.sleep(1000);
		//type user password from XML
		driver.findElement(By.id("pass")).sendKeys(password);
		Thread.sleep(1000);
		click(By.name("login"));
		Thread.sleep(3500);
		
		driver.switchTo().window(baseHandle);

//		click(By.xpath("//img[@type='facebook']"));
		Thread.sleep(3000);

		
		String personalMsg = getText(By.cssSelector(".styled__PrimaryText-zzhidz-4.cfoTPh"));

		if (personalMsg.contains(FBName))
			return true;
		else
			return false;

	}

}
