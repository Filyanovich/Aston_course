package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlockOnlineTest {
        static WebDriver driver;

    @BeforeAll
    public static void setup() {
        System.setProperty("webdriver.chrome.driver",new File("src/test/resources/chromedriver.exe").getAbsolutePath());
        WebDriver driver= new ChromeDriver();
        driver.get("https://www.mts.by/");
        driver.manage().window().maximize();
        //задержка на выполнение теста = 10 сек.
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }
//    @Test
//    public void AllTests() {
//        System.setProperty("webdriver.chrome.driver", new File("src/test/resources/chromedriver.exe").getAbsolutePath());
//        driver = new ChromeDriver();
//        driver.get("https://www.mts.by/");
//        driver.manage().window().maximize();
//
//    }

    @Test
    public void nameBlockTest() {
        driver = new ChromeDriver();
        driver.get("https://www.mts.by/");
        String nameBlock = driver.findElement(By.xpath("//div[@class = 'pay__wrapper']/h2")).getText();
        assertEquals("Онлайн пополнение\n" +
                "без комиссии", nameBlock);

    }

    @Test
    public void logoTest(){
        driver = new ChromeDriver();
        driver.get("https://www.mts.by/");


    }

    @AfterAll
    public static void quitDriver() {
        driver.quit();
    }
}
