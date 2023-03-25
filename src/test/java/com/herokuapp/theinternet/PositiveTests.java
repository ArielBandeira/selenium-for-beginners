package com.herokuapp.theinternet;

import org.openqa.selenium.WebDriver;
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
    //enter password
    //click login button

    //verifications:
    //new url
    //logout button is visible
    //successful login message
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
