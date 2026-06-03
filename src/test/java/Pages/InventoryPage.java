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
    WebElement cartIcon;



      public int getProductCount(){
        return products.size();
    }
    public void addProduct(int index){
        products.get(index).findElement(By.tagName("button")).click();
    }
    public void removeProduct(int index){
        products.get(index).findElement(By.tagName("button")).click();
    }

    public String getCartQuantity(){
        try {
            return cartIcon.getText();
        } catch (Exception e) {
            return "";
        }

    }
    public void clickOnCartIcon(){
          cartIcon.click();
    }
    public WebElement getProductButton(int index) {
        List<WebElement> buttons = driver.findElements(By.cssSelector(".btn_inventory"));
        return buttons.get(index);
    }




}
