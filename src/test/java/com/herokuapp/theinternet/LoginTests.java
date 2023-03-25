package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

public class LoginTests {

    private WebDriver driver;
    @Parameters({ "browser" })
    @BeforeMethod(alwaysRun = true)
    private void setUp(@Optional("edge") String browser) {

        //CREATE DRIVER
/*        switch (browser) {
            case "chrome":
                System.setProperty("webdriver.com.chrome.driver", "src/main/resources/chromedriver.exe");
                driver = new ChromeDriver();
                break;

            case "firefox":
                System.setProperty("webdriver.com.gecko.driver", "src/main/resources/geckodriver.exe");
                driver = new FirefoxDriver();
                break;

            default:
                System.out.println("Do not know how to start " + browser + ", starting edge instead");
                System.setProperty("webdriver.com.edge.driver", "src/main/resources/msedgedriver.exe");
                driver = new EdgeDriver();
                break;
        }*/

        System.setProperty("webdriver.com.edge.driver", "src/main/resources/msedgedriver.exe");
        driver = new EdgeDriver();

        //sleep for 3 seconds
        sleep(2000);

        //maximize browser window
        driver.manage().window().maximize();

    }

    @Test(priority = 1, groups = { "positiveTests", "smokeTests" })
    public void positiveLoginTest() {

        System.out.println("Starting login test");

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

    }

    @Parameters({ "username", "password", "expectedMessage" })
    @Test(priority = 2, groups = { "negativeTests", "smokeTests" })
    public void negativeLoginTest(String username, String password, String expectedFailMessage) {

        System.out.println("Starting negativeLoginTest with " + username + " and " + password);

        //open test page
        String url = "https://the-internet.herokuapp.com/login";
        driver.get(url);

        //enter username
        WebElement usernameElement = driver.findElement(By.xpath("/html//input[@id='username']"));
        usernameElement.sendKeys(username);

        //enter password
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
