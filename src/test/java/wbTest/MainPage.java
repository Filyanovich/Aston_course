package wbTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;

public class MainPage {
    private WebDriver driver;
    private ArrayList<Product> productsList;

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.productsList = new ArrayList<>();
    }

    public ArrayList<Product> getProductsList() {
        return productsList;
    }

    private By productList = By.className("product-card__wrapper");
    private By addProductCardToBasketButton = By.xpath(".//*[contains(@class, 'product-card__add-basket')]");
    private By basketButton = By.xpath(".//*[contains(@class, 'navbar-pc__icon--basket')]");
    private By productName = By.xpath(".//*[@class= 'product-card__name']");
    private By productPrice = By.xpath(".//*[@class= 'price__wrap']//ins");

    //открыть главную страницу
    public void open() {
        driver.get("https://www.wildberries.ru/");
    }

    public String getProductName(int index) {
        return driver.findElements(productName).get(index).getText().substring(2);
    }

    public int getProductPrice(int index) {
        String strPrice = driver.findElements(productPrice).get(index).getText();
        return Integer.parseInt(strPrice.substring(0, strPrice.length() - 2).replace(" ", ""));
    }

    public MainPage addProductToList(int index, int quantity) {
        Actions action = new Actions(driver);
        WebElement element = driver.findElements(productList).get(index);
        action.moveToElement(element).perform();
        driver.findElements(addProductCardToBasketButton).get(index).click();
        this.productsList.add(new Product(this.getProductName(index), this.getProductPrice(index), quantity));

        return this;
    }

    public void clickBasketButton() {
        driver.findElement(basketButton).click();
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (Product product : this.productsList) {
            totalPrice += product.getPrice();
        }
        return totalPrice;
    }

    public int getTotalQuantity() {
        int totalQuantity = 0;
        for (Product product : this.productsList) {
            totalQuantity += product.getQuantity();
        }
        return totalQuantity;
    }

}

