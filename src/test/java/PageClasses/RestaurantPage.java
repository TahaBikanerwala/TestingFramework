package PageClasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import PageBaseTest.PageBaseClass;
import PageBaseTest.PageFooterClass;
import PageBaseTest.PageHeaderClass;

public class RestaurantPage extends PageBaseClass {
	PageFooterClass pageFooter;
	PageHeaderClass pageHeader;
	public RestaurantPage(WebDriver driver) {
		this.driver = driver;
		pageHeader = PageFactory.initElements(driver, PageHeaderClass.class);
		pageFooter = PageFactory.initElements(driver, PageFooterClass.class);
	}
	
	
	public PageHeaderClass getPageHeader() {
		return pageHeader;
	}
	
	public PageFooterClass getPageFooter() {
		return pageFooter;
	}

}
