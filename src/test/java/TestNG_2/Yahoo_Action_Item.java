package TestNG_2;

import Reusable_Classes.Reusable_Library;
import org.openqa.selenium.*;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;
import java.util.List;

public class Yahoo_Action_Item {

        //declare the driver here to be reused on all annotations method
        WebDriver driver = null;

        @BeforeSuite
        public void openBrowser() throws IOException {
            //navigate command to open the url
            driver = Reusable_Library.navigate("https://www.yahoo.com");

        }//end of before suite

        @Test
        public void YahooLogIn(){

            //using soft assert to verify the title
            SoftAssert softAssert = new SoftAssert();
            softAssert.assertEquals(driver.getTitle(),"yahoo");

            //Count of options on the side panel
            List<WebElement> tabsCount = driver.findElements(By.xpath("//*[contains(@class,'Mstart(21px)']"));
            System.out.println("tab count is" + tabsCount.size());

            //Step.4 Enter Nutrition on search
            Reusable_Library.userInput(driver,"//*[@id='uh-search-box']","Nutrition","Yahoo search");

            //Step 5. Click "Search" button
            Reusable_Library.click(driver,"//*[@id='yui_3_18_0_4_1569691494018_918']","Enter Search");

            //Step 6. scroll down the page
            JavascriptExecutor jse = (JavascriptExecutor)driver;
            jse.executeScript("scroll(0,5000)");




        }//end of test annotation




}
