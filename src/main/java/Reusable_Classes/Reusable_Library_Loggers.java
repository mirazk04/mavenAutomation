package Reusable_Classes;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Reusable_Library_Loggers {

    public static void getScreenshot(WebDriver driver, ExtentTest logger, String screenshotName) throws IOException {
        // String path = "C:\\Users\\sumon.kashem\\Desktop\\Screenshots\\";
        String path = "src\\main\\java\\Report_Folder\\ScreenShots\\";
        String fileName = screenshotName + ".png";
        File sourceFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        //Now you can do whatever you need to do with, for example copy somewhere
        FileUtils.copyFile(sourceFile, new File(path + fileName));
        //String imgPath = directory + fileName;
        String image = logger.addScreenCapture("ScreenShots\\" + fileName);
        logger.log(LogStatus.FAIL, "", image);
    }

    //method to navigate using webdriver
    public static WebDriver navigate(String url) throws IOException {

        //log INFO command to navigate
        //logger.log(LogStatus.INFO,"Navigate to url " + url);

        //define the path of the chrome driver
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");

        //quit all open chrome browsers
        //Runtime.getRuntime().exec("taskkill /im chromedriver.exe /f /t");

        //setting up the chrome options
        ChromeOptions options = new ChromeOptions();
        //add the precondition arguments
        options.addArguments("start-maximized", "incognito");

        //define the chrome web driver
        WebDriver driver = new ChromeDriver(options);

        //navigate to url
        driver.navigate().to(url);

        return driver;
    }//end of navigate

    //hovering over an element using mouse Actions
    public static void mouseHover(WebDriver driver, String locator, ExtentTest logger, String elementName) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver,10);
        Actions mouseMove = new Actions(driver);
        try{
            System.out.println("Hovering to element " + elementName);
            logger.log(LogStatus.INFO,"Hovering to element " + elementName);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
            mouseMove.moveToElement(element).perform();
        } catch (Exception err) {
            System.out.println("Unable to hover to element " + elementName + " --" + err);
            logger.log(LogStatus.FAIL,"Unable to hover to element " + elementName + " --" + err);
            getScreenshot(driver,logger,elementName);
        }
    }//end of mouse hover method

    //click on element using explicit wait
    public static void click(WebDriver driver, String locator,ExtentTest logger, String elementName) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver,10);
        try{
            System.out.println("Clicking on element " + elementName);
            logger.log(LogStatus.INFO,"Clicking on element " + elementName);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator))).click();
        } catch (Exception err) {
            System.out.println("Unable to click on element " + elementName + " --" + err);
            logger.log(LogStatus.FAIL,"Unable to click on element " + elementName + " --" + err);
            getScreenshot(driver,logger,elementName);
        }
    }//end of click method

    //click on element using explicit wait with index
    public static void clickByIndex(WebDriver driver, String locator, int indexNumber, ExtentTest logger, String elementName) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver,10);
        try{
            System.out.println("Clicking on element " + elementName);
            logger.log(LogStatus.INFO,"Clicking on element " + elementName);
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator))).get(indexNumber).click();
        } catch (Exception err) {
            System.out.println("Unable to click on element " + elementName + " --" + err);
            logger.log(LogStatus.FAIL,"Unable to click on element " + elementName + " --" + err);
            getScreenshot(driver,logger,elementName);
        }
    }//end of click method

    //entering info on element using explicit wait
    public static void userInput(WebDriver driver, String locator, String userValue, ExtentTest logger, String elementName) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver,10);
        try {
            System.out.println("Entering value on element " + elementName);
            logger.log(LogStatus.INFO,"Entering value on element " + elementName);
            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
            //clear any pre populated data on the text field
            element.clear();
            //enter a new value on the text field
            element.sendKeys(userValue);
        }catch (Exception err) {
            System.out.println("Unable to enter value on element " + elementName + " --" + err);
            logger.log(LogStatus.FAIL,"Unable to enter value on element " + elementName + " --" + err);
            getScreenshot(driver,logger,elementName);
        }
    }//end of sendKeys method

    public static String captureTextByIndex(WebDriver driver, String locator, ExtentTest logger, int index, String elementName) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver,10);
        String text = null;
        try{
            System.out.println("Capturing text on element " + elementName);
            logger.log(LogStatus.INFO,"Capturing text on element " + elementName);
            text = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(locator))).get(index).getText();
        } catch (Exception err) {
            System.out.println("Unable to capture text on element " + elementName + " --" + err);
            logger.log(LogStatus.FAIL,"Unable to capture text on element " + elementName + " --" + err);
            getScreenshot(driver,logger,elementName);
        }

        return text;
    }//end of getText method

    public static void dropDownByText(WebDriver driver, String locator, ExtentTest logger, String userValue, String elementName) throws IOException {
        WebDriverWait wait = new WebDriverWait(driver,10);
        WebElement element = null;
        try{
            System.out.println("Selecting value " + userValue + " from element " + elementName);
            logger.log(LogStatus.INFO,"Selecting value " + userValue + "from element " + elementName);
            element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
            Select dDown = new Select(element);
            dDown.selectByVisibleText(userValue);
        } catch (Exception err) {
            System.out.println("Unable to select value from element " + elementName + " --" + err);
            logger.log(LogStatus.FAIL,"Unable to select value from element " + elementName + " --" + err);
            getScreenshot(driver,logger,elementName);
        }
    }//end of Select by text method
    public static String getDateTime() {
            SimpleDateFormat sdfDateTime;
            String strDateTime;
            sdfDateTime = new SimpleDateFormat("yyyyMMdd'_'HHmmss'_'SSS");
            Date now = new Date();
            strDateTime = sdfDateTime.format(now);
            return strDateTime;
    }//End of get Date Time





}//end of class