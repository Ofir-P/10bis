package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


public class Main extends Base {

	public Main(WebDriver driver) {
		super(driver);
	}

	// start registration
	public boolean register() throws InterruptedException {

		click(By.xpath("//button[text()='הרשמה']"));
		if (isExist(By.id("modal-title")))
			return true;
		else
			return false;

	}
	
	// start login
		public boolean login() throws InterruptedException {
			
			//Click connection
			click(By.xpath("//button[text()='התחברות']"));
			
			if (isExist(By.xpath("//*[@id=\"modal-title\"][text()='איזה כיף שחזרת אלינו!']")))
				return true;
			else
				return false;
		}
		
		// restaurant
		public boolean restaurant() throws InterruptedException {
			
			
			if ((driver.getCurrentUrl().contains("BSR"))) {
				return true;
			}
			else
				return false;
		}
		
		// order page
		public boolean order() throws InterruptedException {
			
			
			if ((driver.getCurrentUrl().contains("BSR"))) {
				return true;
			}
			else
				return false;
		}
		

	// select account
	public boolean selectAccount() throws InterruptedException {

		click(By.cssSelector("#other > div.question.first-question > div"));
		click(By.xpath(
				"//div[@class='dropdown custom-select step-one open']//li[@data-value='freelancer-or-smb'][@class='option']"));
		return true;
	}

}
