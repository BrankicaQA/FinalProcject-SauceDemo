package Pages;

import Base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage extends BaseTest {
    public CartPage(){
        PageFactory.initElements(driver, this);
    }
    @FindBy(className = "cart_item")
    List<WebElement> cartItems;

    @FindBy(id = "continue-shopping")
    WebElement continueShoppingButton;

    @FindBy(id = "checkout")
    WebElement checkoutButton;

    @FindBy(className = "shopping_cart_link")
    WebElement cartIcon;

    @FindBy(id="remove-sauce-labs-bike-light")
    WebElement removeButton;

    public List<WebElement> getCartItems(){
        return cartItems;
    }

    public String getItemQuantity(int index){
        return cartItems.get(index).findElement(By.className("cart_quantity")).getText();
    }

    public void removeItem(int index){
        cartItems.get(index).findElement(By.tagName("button")).click();
    }

    public void clickContinueShopping(){
        continueShoppingButton.click();
    }

    public void clickCheckout(){
        checkoutButton.click();
    }

    public void clickCartIcon(){
        cartIcon.click();
    }

    public void clickOnRemoveButton(){
        removeButton.click();
    }

    public int getCartIconCount() {
        try {
            return Integer.parseInt(cartIcon.getText());
        } catch (Exception e) {
            // Ako badge ne postoji ili je prazan, vraćamo 0
            return 0;
        }
    }
}
