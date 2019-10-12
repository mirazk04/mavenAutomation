package Yahoo_Page_Object;

import Reusable_Classes.Abstract_Class;
import Reusable_Classes.Reusable_Library_Loggers_POM;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class Yahoo_RegistrationPage extends Abstract_Class {

    ExtentTest logger;

    //create constructor
    public Yahoo_RegistrationPage(WebDriver driver) {
        super();
        PageFactory.initElements(driver, this);
        this.logger = super.logger;
    }

    @FindBy(xpath = "//*[@name='firstName']")
    public static WebElement firstNameLocator;
    @FindBy(xpath = "//*[@name='lastName']")
    public static WebElement lastNameLocator;
    @FindBy(xpath = "//*[@name='yid']")
    public static WebElement emailLocator;
    @FindBy(xpath = "//*[@name='password']")
    public static WebElement passwordLocator;
    @FindBy(xpath = "//*[@name='phone']")
    public static WebElement phoneNumberLocator;
    @FindBy(xpath = "//*[@name='mm']")
    public static WebElement birthMonthLocator;
    @FindBy(xpath = "//*[@name='dd']")
    public static WebElement birthDayLocator;
    @FindBy(xpath = "//*[@name='yyyy']")
    public static WebElement birthYearLocator;
    @FindBy(xpath = "//*[text()='Continue']")
    public static WebElement continueButtonLocator;
    @FindBy(css = "#recaptcha-script > h1")
    public static WebElement messageLocator;

    //fist name method
    public Yahoo_RegistrationPage FirstName(String userInput) throws IOException, InterruptedException {
        Reusable_Library_Loggers_POM.userInput(driver, firstNameLocator, 0, userInput, logger, "Firstname Field");
        return new Yahoo_RegistrationPage(driver);
    }

    //last name method
    public Yahoo_RegistrationPage LastName(String userInput) throws IOException, InterruptedException {
        Reusable_Library_Loggers_POM.userInput(driver, lastNameLocator, 0, userInput, logger, "Lastname Field");
        return new Yahoo_RegistrationPage(driver);
    }

    //email method
    public Yahoo_RegistrationPage Email(String emailAddress) throws IOException, InterruptedException {
        Reusable_Library_Loggers_POM.userInput(driver, emailLocator, 0, emailAddress, logger, "Email Address Field");
        return new Yahoo_RegistrationPage(driver);
    }

    //password method
    public Yahoo_RegistrationPage Password(String userInput) throws IOException, InterruptedException {
        Reusable_Library_Loggers_POM.userInput(driver, passwordLocator, 0, userInput, logger, "Password Field");
        return new Yahoo_RegistrationPage(driver);
    }

    //Phone Number method
    public Yahoo_RegistrationPage PhoneNum(String digits) throws IOException, InterruptedException {
        Reusable_Library_Loggers_POM.userInput(driver, phoneNumberLocator, 0, digits, logger, "Phone Number Field");
        return new Yahoo_RegistrationPage(driver);
    }

    //Birth Month method
    public Yahoo_RegistrationPage BirthMonth(String userInput) throws IOException, InterruptedException {
        Reusable_Library_Loggers_POM.dropDownByText(driver, birthMonthLocator, userInput, 0, logger, "Birth Month Dropdown");
        return new Yahoo_RegistrationPage(driver);
    }

    //Birth Day method
    public Yahoo_RegistrationPage BirthDay(String userInput) throws IOException, InterruptedException {
        Reusable_Library_Loggers_POM.userInput(driver, birthDayLocator, 0, userInput, logger, "Birthday Field");
        return new Yahoo_RegistrationPage(driver);
    }

    //Birth Year method
    public Yahoo_RegistrationPage BirthYear(String userInput) throws IOException, InterruptedException {
        Reusable_Library_Loggers_POM.userInput(driver, birthYearLocator, 0, userInput, logger, "Birthday Field");
        return new Yahoo_RegistrationPage(driver);
    }

    //Continue button method
    public Yahoo_RegistrationPage ContinueButton() throws IOException, InterruptedException {
        Reusable_Library_Loggers_POM.click(driver, continueButtonLocator, 0, logger, "Continue Button");
        return new Yahoo_RegistrationPage(driver);
    }

    //verification method
    //since this is returning a text you can't create this method as a contructor return method
    //has to be regular return method
    public String captchaMessage() throws IOException, InterruptedException {
        Thread.sleep(3000);
        logger.log(LogStatus.INFO, "Switching to Iframe for Verification Message");
        try {
            driver.switchTo().frame("recaptcha-iframe");
        } catch (Exception e) {
            logger.log(LogStatus.FAIL, "Unable to switch to captch iFrame... " + e);
            getScreenshot(driver, logger, "captchaFrameIssue");
        }
        String text = Reusable_Library_Loggers_POM.captureTextByIndex(driver, messageLocator, 0, logger, "Verification Message");
        return text;
    }//end of verification method
}//end of class
