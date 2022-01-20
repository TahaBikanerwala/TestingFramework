package PageBaseTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageFooterClass extends PageBaseClass {
	public PageFooterClass(WebDriver driver) {
		this.driver = driver;
	}
	
	@FindBy(xpath="//p[text()='Who We Are']")
	WebElement whoweaare;
	
	@FindBy(xpath="//*[@id=\"root\"]/div/footer/div/section/section/div[1]/div/div[2]/span[2]")
	WebElement dropdownCountry;
	
	
	@FindBy(xpath="//span[text()='United Kingdom']")
	WebElement Uk;
	
	public void selectCountry() {
		dropdownCountry.click();
		Uk.click();
	}

}
