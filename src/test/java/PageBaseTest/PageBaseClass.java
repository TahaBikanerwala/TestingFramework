package PageBaseTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import PageClasses.HomePage;
import junit.framework.Assert;
import utilities.ExtentReportManager;

public class PageBaseClass {
	public WebDriver driver;
	public ExtentReports report = ExtentReportManager.getReportInstance();
	public ExtentTest logger;
	String date;

	/****** Launching Web Browser ******/
	public void invokeBrowser(String browserName) {
		try {
			if (browserName.equalsIgnoreCase("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("firefox")) {
				System.setProperty("webdriver.gecko.driver",
						System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");
				driver = new ChromeDriver();
			} else if (browserName.equalsIgnoreCase("edge")) {
				System.setProperty("webdriver.edge.driver",
						System.getProperty("user.dir") + "\\drivers\\msedgedriver.exe");
				driver = new ChromeDriver();
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
	}

	/****** Launching Website ********/
	public HomePage openWebsite(String url) {
		driver.get(url);
		return PageFactory.initElements(driver, HomePage.class);

	}

	/****** Get Title of a Webpage 
	 * @throws IOException ******/
	public void getTitle(String expectedTitle) throws IOException {
		String actualTitle = driver.getTitle();
		try {
			Assert.assertEquals(expectedTitle, actualTitle);
			reportPass("Actual Title: "+actualTitle+"= Expected Title: "+ expectedTitle);
		}
		catch (Exception e) {
			reportFail(e.getMessage());
		}
	}

	public void takeScreeenShot(String filepath) throws IOException {
		TakesScreenshot screenshot = (TakesScreenshot) driver;
		File src = screenshot.getScreenshotAs(OutputType.FILE);
		File dest = new File(System.getProperty("user.dir") + "\\screenshots\\" + filepath);
		FileHandler.copy(src, dest);
//		logger.addScreenCaptureFromPath(System.getProperty("user.dir") + "\\screenshots\\" + filepath + date);
	}

	public String getExcelData(String filepath, int sheet_number, int rowIndex, int cellnum) throws IOException {
		File file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\" + filepath);
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook wb = new XSSFWorkbook(fis);
		XSSFSheet sheet = wb.getSheetAt(sheet_number);
		return sheet.getRow(rowIndex).getCell(cellnum).getStringCellValue();
	}
	
	public String getDate() {
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date());
		return timeStamp;
	}

	public void sendData(String filepath, String value) throws IOException {
		File file = new File(System.getProperty("user.dir") + "\\src\\test\\resources\\" + filepath);
		FileOutputStream fos = new FileOutputStream(file);
		byte b[] = value.getBytes();
		fos.write(b);
	}

	public void reportPass(String reportString) {
		logger.log(Status.PASS, reportString);
	}

	public void reportFail(String reportString) throws IOException {
		logger.log(Status.FAIL, reportString);
		takeScreeenShot("fail.jpg");
		Assert.fail(reportString);
	}

	@AfterMethod
	public void flushReports() {
		report.flush();
	}

}
