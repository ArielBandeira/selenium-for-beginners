package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class ExceptionsTests {

    private WebDriver driver;
    @Parameters({ "browser" })
    @BeforeMethod(alwaysRun = true)
    private void setUp(@Optional("edge") String browser) {

        //CREATE DRIVER
        System.setProperty("webdriver.com.edge.driver", "src/main/resources/msedgedriver.exe");
        driver = new EdgeDriver();

        //maximize browser window
        driver.manage().window().maximize();

    }

    @Test
    public void addNewRowTest() {
        System.out.println("Starting addNewRowTest");

        //open test page
        System.out.println("open test page");
        String url = "https://practicetestautomation.com/practice-test-exceptions/";
        driver.get(url);

        //sleep for 2 seconds
        System.out.println("sleep for 2 seconds");
        sleep(2000);

        //click on add button
        System.out.println("click on add button");
        WebElement addButton = driver.findElement(By.xpath("/html//button[@id='add_btn']"));
        addButton.click();

        //check if row 2 is displayed
        System.out.println("check if row 2 is displayed");
        WebElement row2 = driver.findElement(By.xpath("//div[@id='rows']/div[3]/div[@class='row']/input[@type='text']"));
        Assert.assertTrue(row2.isDisplayed());
        System.out.println("row 2 is displayed");

        //sleep for 5 seconds
        System.out.println("sleep for 5 seconds");
        sleep(5000);

        System.out.println("close browser");
    }




    //sleep for m seconds
    private static void sleep(long m) {
        try {
            Thread.sleep(m);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    //close browser
    @AfterMethod(alwaysRun = true)
    private void tearDown() {
        //close browser
        driver.quit();
    }
}
