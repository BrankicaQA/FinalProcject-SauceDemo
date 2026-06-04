package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class InventoryPage extends BaseTest {
    public InventoryPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "inventory_item")
    List<WebElement> products;

    @FindBy(className = "shopping_cart_badge")
    public WebElement cartIcon;
    @FindBy(css = ".product_sort_container")
    public WebElement sortDropdown;
    @FindBy(id = "item_4_title_link")
    public WebElement backpackLink;
    @FindBy(className = "shopping_cart_badge")
    public WebElement cartBadge;
    @FindBy(className = "inventory_item_name")
    public WebElement productName;
    @FindBy(className = "inventory_item_price")
    public WebElement productPrice;
    @FindBy(className = "inventory_item_desc")
    public WebElement productDescription;
    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    public WebElement addToCartBackpack;

    //------------------------------
    //  Broj proizvoda
    public int getProductCount(){
        return products.size();
    }
    // Dodavanje proizvoda po indexu
    public void addProduct(int index){
        products.get(index).findElement(By.tagName("button")).click();
    }
    // Uklanjanje proizvoda po indexu
    public void removeProduct(int index){
        products.get(index).findElement(By.tagName("button")).click();
    }
    // Dohvat badge-a
    public String getCartQuantity(){
        try {
            return cartIcon.getText();
        } catch (Exception e) {
            return "";
        }
    }
    public void addBackpackToCart() {
        addToCartBackpack.click();
    }
    public void openBackpackDetails() {
        backpackLink.click();
    }
    public String getProductName() {
        return productName.getText();
    }
    public String getProductPrice() {
        return productPrice.getText();
    }
    public String getProductDescription() {
        return productDescription.getText();
    }
}