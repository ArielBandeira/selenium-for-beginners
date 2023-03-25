package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeTests {

    @Test
    public void incorrectUsernameTest() {

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
        WebElement username = driver.findElement(By.xpath("/html//input[@id='username']"));
        username.sendKeys("incorrect");

        //enter correct password
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("SuperSecretPassword!");

        //click login button
        WebElement loginButton = driver.findElement(By.xpath("//form[@id='login']/button[@class='radius']"));
        loginButton.click();

        //sleep for 3 seconds
        sleep(5000);

        //fail login message
        WebElement failMessage = driver.findElement(By.xpath("/html//div[@id='flash']"));
        String expectedFailMessage = "Your username is invalid!";
        String actualFailMessage = failMessage.getText();
        Assert.assertTrue(actualFailMessage.contains(expectedFailMessage), "Fail message is not the same as expected\nActual message: "
                + actualFailMessage + "\nExpected message: " + expectedFailMessage);

        //close browser
        driver.quit();

    }

    @Test
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

    }

    private static void sleep(long m) {
        try {
            Thread.sleep(m);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
