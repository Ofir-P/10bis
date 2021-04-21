package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class Restaurant extends Base {

	public Restaurant(WebDriver driver) {
		super(driver);
	}

	
	public boolean restaurantSearch(String rName) throws InterruptedException {
		// Search for BSR Restaurant and press on it
		WebElement searchBar = driver.findElement(By.xpath("/html/body/div[2]/div[2]/header/div[2]/div/div[4]/div/div[1]"));
		searchBar.click();
		Thread.sleep(1500);
		WebElement searchBarInput = driver.findElement(By.xpath("/html/body/div[2]/div[2]/header/div[2]/div/div[4]/div/div[1]/input"));
		searchBarInput.sendKeys("BSR");
		Thread.sleep(1500);
		click(By.cssSelector("#searchResults > div > a"));
		
		rName = getText(By.xpath("//*[@id=\"root\"]/div[2]/div[1]/section/header/div/div[2]/div[1]"));

		if (rName.contains("BSR"))
			return true;
		else
			return false;

	}

}
