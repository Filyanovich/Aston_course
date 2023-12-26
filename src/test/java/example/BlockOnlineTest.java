package example;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.File;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class BlockOnlineTest {
    private WebDriver driver = new ChromeDriver();

    @BeforeClass
    public void setup() {
        System.setProperty("webdriver.chrome.driver", new File("src/test/resources/chromedriver.exe").getAbsolutePath());
        driver.get("https://www.mts.by/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    @Test
    public void nameBlockTest() {
        driver.get("https://www.mts.by/");
        String nameBlock = driver.findElement(By.xpath("//div[@class = 'pay__wrapper']/h2")).getText();
        assertEquals("Онлайн пополнение\n" +
                "без комиссии", nameBlock);
    }

    @Test
    public void logoPaidTest() {
        driver.get("https://www.mts.by/");
        WebElement logo = driver.findElement(By.className("pay__partners"));
        assertTrue(logo.isDisplayed(), "Logo table is not displayed");

        WebElement VisaLogo = logo.findElement(By.xpath(".//img[@alt='Visa']"));
        assertTrue(VisaLogo.isDisplayed(), "Visa logo is not displayed.");

        WebElement verifiedByVisaLogo = logo.findElement(By.xpath(".//img[@alt='Verified By Visa']"));
        assertTrue(verifiedByVisaLogo.isDisplayed(), "Verified By Visa logo is not displayed.");

        WebElement MasterCardLogo = logo.findElement(By.xpath(".//img[@alt='MasterCard']"));
        assertTrue(MasterCardLogo.isDisplayed(), "MasterCard logo is not displayed.");

        WebElement MC_ScureCodeLogo = logo.findElement(By.xpath(".//img[@alt='MasterCard Secure Code']"));
        assertTrue(MC_ScureCodeLogo.isDisplayed(), "MC_ScureCode logo is not displayed.");

        WebElement belcartLogo = logo.findElement(By.xpath(".//img[@alt='Белкарт']"));
        assertTrue(belcartLogo.isDisplayed(), "Belcart logo is not displayed.");

        WebElement mirLogo = logo.findElement(By.xpath(".//img[@alt='МИР']"));
        assertTrue(mirLogo.isDisplayed(), "Mir logo is not displayed.");
    }

    @Test
    public void serviceLinkTest() {
        driver.get("https://www.mts.by/");
        WebElement serviceLink = driver.findElement(By.xpath("//a[text()='Подробнее о сервисе']"));
        assertTrue(serviceLink.isDisplayed(), "Service link is not displayed.");
        serviceLink.click();

        String expectedURL = "https://www.mts.by/help/poryadok-oplaty-i-bezopasnost-internet-platezhey/";
        String gettingURL = driver.getCurrentUrl();
        assertEquals(gettingURL, expectedURL, "Service link is not working correctly, it opened the wrong page");
    }

    @Test
    public void inputDataTest() {
        driver.get("https://www.mts.by/");
        WebElement service = driver.findElement(By.cssSelector("span[class='select__now']"));
        assertEquals("Услуги связи", service.getText(), "Service not correct");
        WebElement phone = driver.findElement(By.cssSelector("input[id='connection-phone']"));
        phone.sendKeys("297777777");
        WebElement contininueButton = driver.findElement(By.xpath("//*[@id=\"pay-connection\"]/button"));
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(ExpectedConditions.
                elementToBeClickable(By.xpath("//*[@id=\"pay-connection\"]/button")));
        contininueButton.click();
    }

    @AfterClass
    public void quitDriver() {
        driver.quit();
    }
}
