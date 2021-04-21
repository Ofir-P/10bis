package pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public class Order extends Base {

	public Order(WebDriver driver) {
		super(driver);
	}

	
	public boolean orderPage(String payment) throws InterruptedException {
		// Click on Chicken Salad meal
		click(By.id("dishDescription_1870613_4"));
		// Adding two more meals to the order
		click(By.cssSelector("#modals > div > div > div > div > div > div.styled__Scroller-xuhu2v-25.iJQBHD > div.styled__HideOnMobileOnly-h0boa1-12.fnvhzV > div > div.styled__DishRoot-xuhu2v-2.dSkulA > div.styled__Content-xuhu2v-6.FFErs > div.styled__UserAssignAndAmountContainer-xuhu2v-14.Sa-Duk > div.styled__PriceAndAmount-xuhu2v-9.cZujjd > div > div > button.Button-sc-60bpt4-0.AmountCounter__AmountButton-sc-12fyc5x-2.AmountCounter__IncreaseButton-sc-12fyc5x-3.cmDWmk.eHaUVG.kfbwWJ"));
		click(By.cssSelector("#modals > div > div > div > div > div > div.styled__Scroller-xuhu2v-25.iJQBHD > div.styled__HideOnMobileOnly-h0boa1-12.fnvhzV > div > div.styled__DishRoot-xuhu2v-2.dSkulA > div.styled__Content-xuhu2v-6.FFErs > div.styled__UserAssignAndAmountContainer-xuhu2v-14.Sa-Duk > div.styled__PriceAndAmount-xuhu2v-9.cZujjd > div > div > button.Button-sc-60bpt4-0.AmountCounter__AmountButton-sc-12fyc5x-2.AmountCounter__IncreaseButton-sc-12fyc5x-3.cmDWmk.eHaUVG.kfbwWJ"));
		click(By.xpath("/html/body/div[2]/div[2]/div[2]/div/div/div/div/div/div[4]/div/button/div/div"));
		Thread.sleep(5000);
		// Click on Payment button
		click(By.xpath("/html/body/div[2]/div[2]/div[1]/section/div[3]/div[2]/aside/div/div[2]/div[1]/button/div"));
		Thread.sleep(2500);
		
		payment = getText(By.id("modal-title"));

		if (payment.contains("תשלום"))
			return true;
		else
			return false;

	}

}
