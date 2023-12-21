package org;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlockOnlineTest {
    private WebDriver driver=new ChromeDriver();
    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", new File("src/test/resources/chromedriver.exe").getAbsolutePath());
        driver.get("https://www.mts.by/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }


    @AfterClass
    public void quitDriver() {
        driver.quit();
    }
}
