package calculate;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.testng.AssertJUnit.assertEquals;

@Listeners(io.qameta.allure.testng.AllureTestNg.class)
public class CalculateTest {

    AndroidDriver driver = null;

    @Step("Запуск приложения")
    @BeforeClass
    public void initialise() {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "Galaxy A51");
        capabilities.setCapability("uiautomator2ServerInstallTimeout", 60000);
        capabilities.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, "com.google.android.calculator");
        capabilities.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, "com.android.calculator2.Calculator");
        capabilities.setCapability(MobileCapabilityType.NO_RESET, true);

        try {
            driver = new AndroidDriver(
                    new URL("http://127.0.0.1:4723/wd/hub/"), capabilities);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        } catch (MalformedURLException e) {
            System.out.println(e.getMessage());
        }
    }

    private By plus = By.id("com.google.android.calculator:id/op_add");
    private By minus = By.id("com.google.android.calculator:id/op_sub");
    private By divided = By.id("com.google.android.calculator:id/op_div");
    private By multiplied = By.id("com.google.android.calculator:id/op_mul");
    private By resultButton = By.id("com.google.android.calculator:id/eq");
    private By result = By.id("com.google.android.calculator:id/result_final");

    @Step("Проверка операции сложения")
    @Test
    public void additionTest() {
        driver.findElement(By.id("com.google.android.calculator:id/digit_2")).click();
        driver.findElement(plus).click();
        driver.findElement(By.id("com.google.android.calculator:id/digit_3")).click();
        driver.findElement(resultButton).click();

        assertEquals(driver.findElement(result).getText(), "5");

    }

    @Step("Проверка операции вычитания")
    @Test
    public void subtractionTest() {
        driver.findElement(By.id("com.google.android.calculator:id/digit_8")).click();
        driver.findElement(minus).click();
        driver.findElement(By.id("com.google.android.calculator:id/digit_1")).click();
        driver.findElement(resultButton).click();

        assertEquals(driver.findElement(result).getText(), "7");
    }

    @Step("Проверка операции деления")
    @Test
    public void dividedTest() {
        driver.findElement(By.id("com.google.android.calculator:id/digit_1")).click();
        driver.findElement(By.id("com.google.android.calculator:id/digit_2")).click();
        driver.findElement(divided).click();
        driver.findElement(By.id("com.google.android.calculator:id/digit_3")).click();
        driver.findElement(resultButton).click();
        assertEquals(driver.findElement(result).getText(), "4");
    }

    @Step("Проверка операции умножения")
    @Test
    public void multipliedTest() {
        driver.findElement(By.id("com.google.android.calculator:id/digit_2")).click();
        driver.findElement(multiplied).click();
        driver.findElement(By.id("com.google.android.calculator:id/digit_9")).click();
        driver.findElement(resultButton).click();
        assertEquals(driver.findElement(result).getText(), "18");
    }

    @Step("Закрыть сессию")
    @AfterClass
    public void quitDriver() {
        driver.quit();
    }


}


