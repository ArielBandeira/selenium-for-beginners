package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Test;

public class PositiveTests {
    @Test
    public void loginTest() {
        System.out.println("Starting login test");


    //CREATE DRIVER
        System.setProperty("webdriver.com.edge.driver", "src/main/resources/msedgedriver.exe");
        WebDriver driver = new EdgeDriver();

    //sleep for 3 seconds
        sleep(3000);

    //maximize browser window
        driver.manage().window().maximize();

    //open test page
        String url = "http://the-internet.herokuapp.com/login";
        driver.get(url);
        System.out.println("chegou aqui");

    //sleep for 2 seconds
        sleep(2000);

    //enter username
        WebElement username = driver.findElement(By.id("username"));
        username.sendKeys("tomsmith");

    //sleep for 1 seconds
        sleep(1000);

    //enter password
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("SuperSecretPassword!");

    //sleep for 3 seconds
        sleep(3000);


    //click login button
        WebElement loginButton = driver.findElement(By.tagName("button"));
        loginButton.click();

    //sleep for 5 seconds
        sleep(5000);

    //verifications:
    //new url
    //    String url = "http://the-internet.herokuapp.com/secure";
    //    driver.get(url);
    //    System.out.println("chegou na página depois de login");

    //logout button is visible
        WebElement logoutButton = driver.findElement(By.xpath("//a[@class='button secondary radius']"));

    //successful login message
        WebElement successMessage = driver.findElement(By.cssSelector("#flash"));

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
