package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;

public class InvalidElementStateExceptionTest {
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
    public void editInputTextTest() {
        System.out.println("Starting editInputTextTest");

        //Open page
        String url = "https://practicetestautomation.com/practice-test-exceptions/";
        driver.get(url);

        //Click edit button
        WebElement editButton = driver.findElement(By.xpath("//div[@id='row1']/button[@id='edit_btn']"));
        editButton.click();

        //Clear input field
        WebElement inputTextRow1 = driver.findElement(By.xpath("//div[@id='row1']/input"));
        inputTextRow1.clear();

        //Type text into the input field
        inputTextRow1.sendKeys("Lasagna");

        //Verify text changed
        String value = inputTextRow1.getAttribute("value");
        Assert.assertEquals(value, "Lasagna", "Input 1 field value is not expected");

    }

    //close browser
    @AfterMethod(alwaysRun = true)
    private void tearDown() {
        //close browser
        driver.quit();
    }
}
