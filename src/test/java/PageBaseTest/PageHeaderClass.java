package PageBaseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import PageClasses.RestaurantPage;

public class PageHeaderClass extends PageBaseClass {

	public PageHeaderClass(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(xpath="//input[@placeholder='Search for restaurant, cuisine or a dish']")
	WebElement searchbar;
	
	@FindBys({ 
		@FindBy(xpath="//*[@class='sc-lnmtFM ACCIP']") })
	 java.util.List<WebElement> restaurants;
	
	public RestaurantPage search(String value) {
		searchbar.sendKeys(value);
		searchbar.click();
		restaurants.get(0).click();
		return PageFactory.initElements(driver, RestaurantPage.class);
	}

}
