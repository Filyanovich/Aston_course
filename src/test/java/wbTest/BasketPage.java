package wbTest;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.ArrayList;

public class BasketPage {
    private WebDriver driver;
    private ArrayList<Product> productsList;

    public BasketPage(WebDriver driver) {
        this.driver = driver;
        this.productsList = new ArrayList<>();
    }

    public ArrayList<Product> getProductsList() {
        return productsList;
    }

    private By productName = By.className("good-info__good-name");
    private By productPrice = By.xpath(".//*[@class = 'list-item__price']/div[1]");
    private By productQuantity = By.xpath(".//*[@class = 'count__input-number']/input");
    private By totalQuantity = By.xpath(".//*[contains(@class, 'basket-order__b-top')]/div/span[1]");
    private By totalPrice = By.xpath(".//*[contains(@class, 'basket-order__b-top')]/div/span[2]");

    public String getProductName(int index) {
        return driver.findElements(productName).get(index).getText();
    }

    public int getProductPrice(int index) {
        String strPrice = driver.findElements(productPrice).get(index).getText();
        return Integer.parseInt(strPrice.substring(0, strPrice.length() - 2).replace(" ", ""));

    }

    public int getProductQuantity(int index) {
        return Integer.parseInt(driver.findElements(productQuantity).get(index).getAttribute("value"));
    }

    public int getTotalQuantity() {
        return Integer.parseInt(driver.findElement(totalQuantity).getText().substring(8, 9));
    }

    public int getTotalPrice() throws InterruptedException {
        String strTotalSum = driver.findElement(totalPrice).getText();
        Thread.sleep(1000);
        return Integer.parseInt(strTotalSum.substring(0, strTotalSum.length() - 2).replace(" ", ""));
    }

    public ArrayList<Product> addProductList(int quantity) throws InterruptedException {
        for (int i = quantity - 1; i >= 0; i--) {
            this.productsList.add(new Product(this.getProductName(i), this.getProductPrice(i), this.getProductQuantity(i)));
        }
        return productsList;
    }

}
