 package utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.ChartLocation;


public class ExtentReportManager {
	
	public static ExtentReports report;
	
	public static ExtentReports getReportInstance() {
		if(report==null) {
		
			String reportName = new SimpleDateFormat("yyyy-MM-dd.HH.mm.ss").format(new Date()) + ".html";
			ExtentHtmlReporter htmlReporter =  new ExtentHtmlReporter(System.getProperty("user.dir") + "\\test-output\\" + reportName );
			report = new ExtentReports();
			report.attachReporter(htmlReporter);
			
			report.setSystemInfo("OS", "Windows 8");
			report.setSystemInfo("Environment", "UAT");
			report.setSystemInfo("Build Number", "1.0.0");
			report.setSystemInfo("Browser", "Chrome");
			
			htmlReporter.config().setDocumentTitle("Testing Framework Results");
			htmlReporter.config().setReportName("Test Report");
			htmlReporter.config().setTestViewChartLocation(ChartLocation.TOP);
			htmlReporter.config().setTimeStampFormat("MMM dd yyyy HH:mm:ss");
		
		}
		return report;
	}
}
