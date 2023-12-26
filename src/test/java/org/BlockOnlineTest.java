package org;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BlockOnlineTest {

    private WebDriver driver;

    @BeforeEach
    public void setup() {
        System.setProperty("webdriver.chrome.driver", new File("src/test/resources/chromedriver.exe").getAbsolutePath());
        driver = new ChromeDriver();
        driver.get("https://www.mts.by/");
        driver.manage().window().maximize();
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("document.getElementsByClassName('cookie show')[0].style.display='none';");
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

    }

    @Test
    public void inputDataTest() {
        WebElement service = driver.findElement(By.cssSelector("span[class='select__now']"));
        assertEquals("Услуги связи", service.getText(), "Service not correct");

        WebElement phone = driver.findElement(By.cssSelector("input[id='connection-phone']"));
        WebElement sum = driver.findElement(By.cssSelector("input[id='connection-sum']"));
        WebElement contininueButton = driver.findElement(By.xpath("//*[@id=\"pay-connection\"]/button"));

        phone.sendKeys("297777776");
        sum.sendKeys("10.50");
        contininueButton.click();


        driver.switchTo().frame(driver.findElement(By.className("bepaid-iframe")));
        WebElement sumInHeader = driver.findElement(By.className("header__payment-amount"));
        WebElement sumInButton = driver.findElement(By.xpath("//div[@class='card-page__card']//button"));
        WebElement phoneInFrame = driver.findElement(By.className("header__payment-info"));

        WebElement numberCard = driver.findElement(By.xpath("//div[@class='content ng-tns-c47-1']/label"));
        WebElement expirationDate = driver.findElement(By.xpath("//div[@class='content ng-tns-c47-4']/label"));
        WebElement cvc = driver.findElement(By.xpath("//div[@class='content ng-tns-c47-5']/label"));
        WebElement userName = driver.findElement(By.xpath("//div[@class='content ng-tns-c47-3']/label"));

        WebElement iconMC = driver.findElement(By.xpath("//div[@class='cards-brands ng-tns-c47-1']/div/img[1]"));
        WebElement iconVisa = driver.findElement(By.xpath("//div[@class='cards-brands ng-tns-c47-1']/div/img[2]"));
        WebElement iconBelkart = driver.findElement(By.xpath("//div[@class='cards-brands ng-tns-c47-1']/div/img[3]"));

        assertEquals("Номер карты", numberCard.getText());
        assertEquals("Срок действия", expirationDate.getText());
        assertEquals("CVC", cvc.getText());
        assertEquals("Имя держателя (как на карте)", userName.getText());

        assertTrue(iconMC.isDisplayed());
        assertTrue(iconVisa.isDisplayed());
        assertTrue(iconBelkart.isDisplayed());

        //Не получилось сравнить с действительным значением
        //пробовала через .contains("phone.getText")
        System.out.println(phoneInFrame.getText());
        System.out.println(sumInHeader.getText());
        System.out.println(sumInButton.getText());

        driver.switchTo().defaultContent();

    }

    @Test
    public void connectionWindowTest() {
        WebElement service = driver.findElement(By.cssSelector("span[class='select__now']"));
        assertEquals("Услуги связи", service.getText());
        String phone = driver.findElement(By.
                xpath("//form[@class='pay-form opened']//input[@id='connection-phone']")).getAttribute("placeholder");
        String sum = driver.findElement(By.
                xpath("//form[@class='pay-form opened']//input[@id='connection-sum']")).getAttribute("placeholder");
        String email = driver.findElement(By.
                xpath("//form[@class='pay-form opened']//input[@id='connection-email']")).getAttribute("placeholder");

        assertEquals(phone, "Номер телефона");
        assertEquals(sum, "Сумма");
        assertEquals(email, "E-mail для отправки чека");
    }

    @Test
    public void homeInternetWindowTest() {
        WebElement selectBlock = driver.findElement(By.className("select__wrapper"));
        selectBlock.click();
        WebElement arrearsBlock = selectBlock.findElement(By.xpath("//li[@class='select__item'][1]"));
        arrearsBlock.click();

        String phone = driver.findElement(By.
                xpath("//form[@class='pay-form opened']//input[@id='internet-phone']")).getAttribute("placeholder");
        String sum = driver.findElement(By.
                xpath("//form[@class='pay-form opened']//input[@id='internet-sum']")).getAttribute("placeholder");
        String email = driver.findElement(By.
                xpath("//form[@class='pay-form opened']//input[@id='internet-email']")).getAttribute("placeholder");

        assertEquals(phone, "Номер абонента");
        assertEquals(sum, "Сумма");
        assertEquals(email, "E-mail для отправки чека");
    }

    @Test
    public void installmentWindowTest() {
        WebElement selectBlock = driver.findElement(By.className("select__wrapper"));
        selectBlock.click();
        WebElement arrearsBlock = selectBlock.findElement(By.xpath("//li[@class='select__item'][2]"));
        arrearsBlock.click();

        String name = driver.findElement(By.
                xpath("//form[@class='pay-form opened']//input[@id='score-instalment']")).getAttribute("placeholder");
        String sum = driver.findElement(By.
                xpath("//form[@class='pay-form opened']//input[@id='instalment-sum']")).getAttribute("placeholder");
        String email = driver.findElement(By.
                xpath("//form[@class='pay-form opened']//input[@id='instalment-email']")).getAttribute("placeholder");

        assertEquals(name, "Номер счета на 44");
        assertEquals(sum, "Сумма");
        assertEquals(email, "E-mail для отправки чека");
    }

    @Test
    public void ArrearsWindowTest() {

        WebElement selectBlock = driver.findElement(By.className("select__wrapper"));
        selectBlock.click();
        WebElement arrearsBlock = selectBlock.findElement(By.xpath("//li[@class='select__item'][3]"));
        arrearsBlock.click();

        String name = driver.findElement(By.
                xpath("//form[@class='pay-form opened']//input[@id='score-arrears']")).getAttribute("placeholder");
        String sum = driver.findElement(By.
                xpath("//form[@class='pay-form opened']//input[@id='arrears-sum']")).getAttribute("placeholder");
        String email = driver.findElement(By.
                xpath("//form[@class='pay-form opened']//input[@id='arrears-email']")).getAttribute("placeholder");

        assertEquals(name, "Номер счета на 2073");
        assertEquals(sum, "Сумма");
        assertEquals(email, "E-mail для отправки чека");
    }


    @AfterClass
    public void quitDriver() {
        driver.quit();
    }
}
