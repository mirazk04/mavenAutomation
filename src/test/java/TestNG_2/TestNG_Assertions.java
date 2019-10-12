package TestNG_2;

import Reusable_Classes.Reusable_Library;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

public class TestNG_Assertions {
    //declare the driver here to be reused on all annotations method
    WebDriver driver = null;

    @BeforeSuite
    public void openBrowser() throws IOException {

        //navigate command to open the url
       driver = Reusable_Library.navigate("https://www.google.com");

    }//end of before suite

    @Test
    public void googleSearch(){
        //verify google title matches using Hard Assert
       // Assert.assertEquals(driver.getTitle(),"Google");
        //using soft assert to verify the title
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(driver.getTitle(),"google");
        //enter a keyword on google search field
        Reusable_Library.userInput(driver,"//*[@name='q']","Cars","Search Field");

        //to catch exception from softAssert
        softAssert.assertAll();
    }//end of test annotation

    @AfterSuite
    private void close(){
        //quit the driver
        //driver.quite();

    }//end of after suite




}//end of parent class