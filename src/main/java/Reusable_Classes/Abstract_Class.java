package Reusable_Classes;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

public class Abstract_Class extends Reusable_Library_Loggers_POM{
    public static WebDriver driver =null;
    public static ExtentReports report = null;
    public static ExtentTest logger = null;
    public static Workbook readablefile;
    public static WritableWorkbook writeablefile;
    public static Sheet readablesheet;
    public static WritableSheet wsheet;
    public static int rows;


        @BeforeSuite()
        public static void openBrowser() throws IOException, BiffException, BiffException {
            //path to create new report, with time stamp
            report = new ExtentReports("src\\main\\java\\Report_Folder\\AutomationReport" + getDateTime() + ".html", true);
            logger = report.startTest("Yahoo Tests");
            //access readable excel workbook
            readablefile = Workbook.getWorkbook(new File("src\\main\\resources\\yahoo_pom.xls"));
            // create duplicate workbook to input new data
            writeablefile = Workbook.createWorkbook(new File("src\\main\\resources\\yahoo_pom_results.xls"), readablefile);
            // create writable sheet
            wsheet = writeablefile.getSheet(0);
            // access readable sheet to retrieve data
            readablefile = (Workbook) readablefile.getSheet(0);
            rows = readablesheet.getRows();
            System.out.println("Number of rows in express sheet is: " + rows);

        } // end of before suite

        @Parameters("Browser")
        @BeforeMethod
        public static void captureTestName(Method methodName, String Browser) throws IOException {
            // navigates to website using either firefox or chrome to allow cross browser testing
            if(Browser.equalsIgnoreCase("Firefox")) {
                System.setProperty("webdriver.gecko.driver","");
                // firefox does'nt have chrome options
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
            } else if (Browser.equalsIgnoreCase("Chrome")) {
                driver = navigate(driver,"https://www.google.com");
            }
            //logger below will get the actual name of each of your @Test method(s)
            logger = report.startTest(methodName.getName());
            logger.log(LogStatus.INFO,"Automation Test Scenario Started...");
        }// end of before method

    @AfterMethod
    public static void endTest(){
     //end of report
            report.endTest(logger);
            logger.log(LogStatus.INFO,"Atuomation Test Scenario ended...");
    }// end of after method

    @AfterSuite
    public void closeBrowser() throws IOException, WriteException {
        writeablefile.write();
        writeablefile.close();
        readablefile.close();
        report.flush();
        driver.quit();
        //driver.quit();

    }// end of after suite


}
