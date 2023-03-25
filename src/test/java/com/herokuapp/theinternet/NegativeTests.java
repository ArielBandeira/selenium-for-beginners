package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class NegativeTests {

    @Parameters({ "username", "password", "expectedMessage" })
    @Test(priority = 1, groups = { "negativeTests", "smokeTests" })
    public void negativeLoginTest(String username, String password, String expectedFailMessage) {

        System.out.println("Starting negativeLoginTest with " + username + " and " + password);

        //CREATE DRIVER
        System.setProperty("webdriver.com.edge.driver", "src/main/resources/msedgedriver.exe");
        WebDriver driver = new EdgeDriver();

        //sleep for 3 seconds
        sleep(3000);

        //maximize browser window
        driver.manage().window().maximize();

        //open test page
        String url = "https://the-internet.herokuapp.com/login";
        driver.get(url);

        //verifications:
        //incorrect username and correct password

        //enter incorrect username
        WebElement usernameElement = driver.findElement(By.xpath("/html//input[@id='username']"));
        usernameElement.sendKeys(username);

        //enter correct password
        WebElement passwordElement = driver.findElement(By.name("password"));
        passwordElement.sendKeys(password);

        //click login button
        WebElement loginButton = driver.findElement(By.xpath("//form[@id='login']/button[@class='radius']"));
        loginButton.click();

        //sleep for 3 seconds
        sleep(5000);

        //fail login message
        WebElement failMessage = driver.findElement(By.xpath("/html//div[@id='flash']"));
        String actualFailMessage = failMessage.getText();
        Assert.assertTrue(actualFailMessage.contains(expectedFailMessage), "Fail message is not the same as expected\nActual message: "
                + actualFailMessage + "\nExpected message: " + expectedFailMessage);

        //close browser
        driver.quit();

    }

/*    @Test(priority = 2, groups = { "negativeTests" })
    public void incorrectPasswordTest() {

        //CREATE DRIVER
        System.setProperty("webdriver.com.edge.driver", "src/main/resources/msedgedriver.exe");
        WebDriver driver = new EdgeDriver();

        //sleep for 3 seconds
        sleep(3000);

        //maximize browser window
        driver.manage().window().maximize();

        //open test page
        String url = "https://the-internet.herokuapp.com/login";
        driver.get(url);

        //verifications:
        //correct username and incorrect password

        //enter correct username
        WebElement username = driver.findElement(By.xpath("/html//input[@id='username']"));
        username.sendKeys("tomsmith");

        //enter incorrect password
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("incorrectpassword");

        //click login button
        WebElement loginButton = driver.findElement(By.xpath("//form[@id='login']/button[@class='radius']"));
        loginButton.click();

        //sleep for 3 seconds
        sleep(5000);

        //fail login message
        WebElement failMessage = driver.findElement(By.xpath("/html//div[@id='flash']"));
        String expectedFailMessage = "Your password is invalid!";
        String actualFailMessage = failMessage.getText();
        Assert.assertTrue(actualFailMessage.contains(expectedFailMessage), "Fail message is not the same as expected\nActual message: "
                + actualFailMessage + "\nExpected message: " + expectedFailMessage);

        //close browser
        driver.quit();

    }*/

    private static void sleep(long m) {
        try {
            Thread.sleep(m);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
