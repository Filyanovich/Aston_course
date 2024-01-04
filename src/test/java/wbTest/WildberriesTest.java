package wbTest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.testng.AssertJUnit.assertEquals;

public class WildberriesTest {
    private WebDriver driver;
    private MainPage mainPage;
    private static BasketPage basketPage;

    @BeforeClass
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", new File("src/test/resources/chromedriver.exe").getAbsolutePath());
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        mainPage = PageFactory.initElements(driver, MainPage.class);
        basketPage = PageFactory.initElements(driver, BasketPage.class);
        mainPage.open();
    }

    @Test
    public void wbTest() throws InterruptedException {
        mainPage.addProductToList(2, 1);
        mainPage.clickBasketButton();
        System.out.println(basketPage.addProductList(mainPage.getProductsList().size()));
        mainPage.getProductsList().forEach(product -> assertAll(
                () -> assertEquals(product.getName(), basketPage.getProductsList().get(mainPage.getProductsList().indexOf(product)).getName()),
                () -> assertEquals(product.getPrice(), basketPage.getProductsList().get(mainPage.getProductsList().indexOf(product)).getPrice()),
                () -> assertEquals(product.getQuantity(), basketPage.getProductsList().get(mainPage.getProductsList().indexOf(product)).getQuantity())
        ));
        assertEquals(mainPage.getTotalPrice(), basketPage.getTotalPrice());
        assertEquals(mainPage.getTotalQuantity(), basketPage.getTotalQuantity());

    }

    @AfterClass
    public void quitDriver() {
        driver.quit();
    }

}
