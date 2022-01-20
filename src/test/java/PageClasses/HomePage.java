package PageClasses;

import java.awt.List;
import java.util.ArrayList;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import PageBaseTest.PageBaseClass;
import PageBaseTest.PageFooterClass;
import PageBaseTest.PageHeaderClass;


public class HomePage extends PageBaseClass{
	PageFooterClass pageFooter;
//	PageHeaderClass pageHeader;
	public HomePage(WebDriver driver) {
		this.driver = driver;
//		pageHeader = PageFactory.initElements(driver, PageHeaderClass.class);
		pageFooter = PageFactory.initElements(driver, PageFooterClass.class);
		
	}
	

	@FindBy(linkText = "Log in")
	WebElement login;
	
	@FindBy(xpath="//div[@role='button' and @aria-label='Continue with Google']")
	WebElement gmail;
	
	@FindBy(xpath="//input[@placeholder='Search for restaurant, cuisine or a dish']")
	WebElement search;
	
	@FindBys({ 
		@FindBy(xpath="//div[@class='sc-FQuPU bQIMAA']") })
	 java.util.List<WebElement> restaurants;
	
	
//	@FindBy(xpath="//input[@type='email']")
//	WebElement mail;
	
//	public PageHeaderClass getPageHeader() {
//		return pageHeader;
//	}
//	
	public PageFooterClass getPageFooter() {
		return pageFooter;
	}

	public RestaurantPage search(String value) {
		search.sendKeys(value);
		search.click();
		restaurants.get(0).click();
		return PageFactory.initElements(driver, RestaurantPage.class);
	}
	
	

	
	
	

}
