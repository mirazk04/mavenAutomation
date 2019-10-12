package TestNG;

import Reusable_Classes.Reusable_Library;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import java.io.IOException;

public class WeightWatchers_TestNG {
    //declare driver variable outside so it can be re used on all annotation methods
    WebDriver driver = null;

    @BeforeSuite
    public void openBrowser() throws IOException {

        //define the driver here
        driver = Reusable_Library.navigate("https://www.weightwatchers.com/us");

    }

    @Test(priority = 1)
    public void clickonStudio() throws InterruptedException {

        //re navigate to weight watchers
        driver.navigate().to("https://www.weightwatchers.com/us");

        //timeout to capture the page title
        Thread.sleep(3000);
        //verifying the page title
        String title1 = driver.getTitle();
        if (title1.equals("WW (Weight Watchers): Weight Loss & Wellness Help")) {
            System.out.println("Title matches");
        } else {
            System.out.println("Title doesn't match " + title1);
        }

        //click on 'Find a Meeting' button
        Reusable_Library.click(driver, "//*[@class='find-a-meeting']", "Find a Meeting");


    }//end of priority 1

    @Test(priority = 2)
    public void captureOpHour() throws InterruptedException {
        //timeout to capture the page title 2
        Thread.sleep(3000);
        //verifying the page title 2
        String title2 = driver.getTitle();
        if (title2.equals("Find a Studio & Meeting Near You |")) {
            System.out.println("Title matches");
        } else {
            System.out.println("Title doesn't match " + title2);
        }

        //enter zipcode
        Reusable_Library.userInput(driver, "//*[@id='meetingSearch']", "11218", "Enter Location");
        //click on Search button
        Reusable_Library.click(driver, "//*[@spice='SEARCH_BUTTON']", "Search Button");

        //caputre the first result for meeting location
        String meetLocation = Reusable_Library.captureTextByIndex(driver, "//*[@class='location__name']", 0, "Meeting Location");
        //click on first element using index
        Reusable_Library.clickByIndex(driver, "//*[@class='location__name']", 0, "Location Name Link");

        //caputre the current day operation hour
        String opHour = null;
        //if the operation hour is not present for a zipcode then store the variable inside try catch
        try {
            Thread.sleep(2800);
            opHour = driver.findElements(By.xpath("//*[contains(@class,'currentday')]")).get(0).getText();
        } catch (Exception e) {
            opHour = "Current Operation Hour is not available for this Zip Code";
        }

    }//end of test priority 2

    @AfterSuite
    public void closeBrowser(){

        driver.quit();

    }


}//end of class

