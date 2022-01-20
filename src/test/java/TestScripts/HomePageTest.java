package TestScripts;

import java.io.IOException;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

import PageBaseTest.PageBaseClass;
import PageBaseTest.PageFooterClass;
import PageBaseTest.PageHeaderClass;
import PageClasses.HomePage;
import PageClasses.RestaurantPage;


public class HomePageTest extends PageBaseClass{

	String baseURL;
	HomePage homepage;
	RestaurantPage restaurantPage;
	PageHeaderClass pageHeader;
	PageFooterClass pageFooter;
	PageBaseClass base = new PageBaseClass();
	
	
	@BeforeSuite
	public void setUpinitial() throws IOException, InterruptedException {
		logger = report.createTest("Search");
		base.invokeBrowser("chrome");
		logger.log(Status.INFO, "Opening Browser");
		base.takeScreeenShot("screenshot1.jpg");
		String data = base.getExcelData("sample.xls", 0, 0, 0);
		System.out.println(data);
		base.sendData("sample.txt", data);
		homepage = base.openWebsite("https://www.zomato.com/");
		Thread.sleep(2000);
		base.takeScreeenShot("1-openwebsite.jpg");
	}

	@Test(priority=0)
	public void searchRestaurant() throws IOException {
		logger = report.createTest("Searching");
		restaurantPage = homepage.search("Patel");
		logger.log(Status.INFO, "First Search");
		base.takeScreeenShot("2-search.jpg");
		pageHeader = restaurantPage.getPageHeader();
		restaurantPage= pageHeader.search("x");
		logger.log(Status.INFO, "Second Search from Header");
		base.takeScreeenShot("3-second_search.jpg");
		pageFooter = restaurantPage.getPageFooter();
		pageFooter.selectCountry();
		logger.log(Status.PASS, "Done");
		
	}

}
