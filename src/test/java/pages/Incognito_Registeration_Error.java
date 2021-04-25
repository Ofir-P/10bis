package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;



public class Incognito_Registeration_Error extends Base {

	public Incognito_Registeration_Error(WebDriver driver) {
		super(driver);
	}

	// start Registration
	public boolean alreadyOwnAccount() throws InterruptedException {
		
		//Already has account
		click(By.xpath("//*[@id=\"login_tab_button\"]/h3"));
		driver.findElement(By.id("email")).click();
		// Sending enter key to press Login
		driver.findElement(By.id("email")).sendKeys(Keys.ENTER);

		
		
		if (isExist(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div/div/div/div/div[2]/div/form/div[2]/div/div/div[2]")))
			return true;
		else
			return false;

	}

	
}
