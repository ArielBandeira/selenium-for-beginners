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
        String url = "https://practicetestautomation.com/practice-test-exceptions/";
        driver.get(url);

        //sleep for 2 seconds
        sleep(2000);

        //click on add button
        WebElement addButton = driver.findElement(By.xpath("/html//button[@id='add_btn']"));
        addButton.click();

        //check if row 2 is displayed
        try {
            WebElement row2Input = driver.findElement(By.xpath("//div[@id='row2']/input"));
            Assert.assertTrue(row2Input.isDisplayed(), "Row 2 input is not displayed");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        //sleep for 5 seconds
        sleep(5000);

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
