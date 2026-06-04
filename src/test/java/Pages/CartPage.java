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

    //---------------------


    // Vraća listu svih proizvoda iz korpe
    public List<WebElement> getCartItems() {
        return cartItems;
    }

    // Vraća količinu za proizvod na zadatoj poziciji
    public String getItemQuantity(int index) {
        return cartItems.get(index)
                .findElement(By.className("cart_quantity"))
                .getText();
    }

    // Uklanja proizvod sa zadate pozicije u korpi
    public void removeItem(int index) {
        cartItems.get(index).findElement(By.tagName("button")).click();
    }

    public void clickContinueShopping() {
        continueShoppingButton.click();
    }

    public void clickCheckout() {
        checkoutButton.click();
    }

    // Otvaranje korpe klikom na ikonicu
    public void clickCartIcon() {
        cartIcon.click();
    }

    // Uklanjanje Bike Light proizvoda iz korpe
    public void clickOnRemoveButton() {
        removeButton.click();
    }

    // Vraća broj proizvoda prikazan na ikoni korpe
    public int getCartIconCount() {
        try {
            return Integer.parseInt(cartIcon.getText());
        } catch (Exception e) {
            // Ako broj nije prikazan, korpa se smatra praznom
            return 0;
        }
    }
// Koristim Stream API da pretraži sve proizvode u korpi.
// Stream API je deo Jave koji omogućava da radiš sa kolekcijama (liste, skupovi itd.) na jednostavniji i čitljiviji način, bez klasičnih for petlji.)
// anyMatch() vraća true ako bar jedan proizvod sadrži zadati naziv,
// što koristimo za proveru da li je proizvod dodat u korpu.
    public boolean containsProduct(String productName) {
        return cartItems.stream().anyMatch(item -> item.getText().contains(productName));
    }

    // Vraća naziv proizvoda sa zadate pozicije
    public String getProductName(int index) {
        return cartItems.get(index).findElement(By.className("inventory_item_name")).getText();
    }

    // Vraća cenu proizvoda sa zadate pozicije
    public String getProductPrice(int index) {
        return cartItems.get(index).findElement(By.className("inventory_item_price")).getText();
    }

    // Proverava da li je određeni proizvod prikazan u korpi
    public boolean isProductDisplayed(String productName) {
        return cartItems.stream().anyMatch
                (item -> item.findElement(By.className("inventory_item_name")).getText().equals(productName));
    }

    // Vraća ukupan broj proizvoda u korpi
    public int getNumberOfProductsInCart() {
        return cartItems.size();
    }

    // Proverava da li je korpa prazna
    public boolean isCartEmpty() {
        return cartItems.isEmpty();
    }
}
