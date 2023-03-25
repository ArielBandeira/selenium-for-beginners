package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PositiveTests {
    @Test
    public void loginTest() {
        System.out.println("Starting login test");


    //CREATE DRIVER
        System.setProperty("webdriver.com.edge.driver", "src/main/resources/msedgedriver.exe");
        WebDriver driver = new EdgeDriver();

    //sleep for 3 seconds
        sleep(2000);

    //maximize browser window
        driver.manage().window().maximize();

    //open test page
        String url = "http://the-internet.herokuapp.com/login";
        driver.get(url);
        //driver.navigate().to("http://the-internet.herokuapp.com/login");

    //enter username
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tomsmith");

    //enter password
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("SuperSecretPassword!");

    //click login button
        WebElement loginButton = driver.findElement(By.tagName("button"));
        loginButton.click();

    //sleep for 3 seconds
        sleep(3000);

    //verifications:
    //new url
        String expectedUrl = "http://the-internet.herokuapp.com/secure";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl, "Actual page url is not the same as expected");

    //logout button is visible
        WebElement logoutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));
        Assert.assertTrue(logoutButton.isDisplayed(), "Logout button is not visible");

    //successful login message
        WebElement successMessage = driver.findElement(By.xpath("//div[@id='flash']"));
        String expectedMessage = "You logged into a secure area";
        String actualMessage = successMessage.getText();
        Assert.assertTrue(actualMessage.contains(expectedMessage),"Actual message is not the same as expected.\nActual Message: " + actualMessage
                + "\nExpected Message: " + expectedMessage);

    //close browser
        driver.quit();

   }

    private static void sleep(long m) {
        try {
            Thread.sleep(m);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
