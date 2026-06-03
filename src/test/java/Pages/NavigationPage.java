package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
// Napravila sam novu granu u kojoj ću koristiti drugačiji pristup za lociranje elemenata.
// Lokatore ću pronalaziti putem metoda, kreiranjem konstruktora i getera.
public class NavigationPage {
    WebDriver driver;
    WebElement burgerMenu;
    WebElement closeMenu;
    WebElement logoutOption;
    WebElement aboutOption;
    WebElement resetAppStateOption;
    WebElement allItemsOption;
    WebElement cartIcon;

    public NavigationPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getBurgerMenu() {
        return driver.findElement(By.id("react-burger-menu-btn"));
    }

    public WebElement getCloseMenu() {
        return driver.findElement(By.id("react-burger-cross-btn"));
    }

    public WebElement getLogoutOption() {
        return driver.findElement(By.id("logout_sidebar_link"));
    }

    public WebElement getAboutOption() {
        return driver.findElement(By.id("about_sidebar_link"));
    }

    public WebElement getResetAppStateOption() {
        return driver.findElement(By.id("reset_sidebar_link"));
    }

    public WebElement getAllItemsOption() {
        return driver.findElement(By.id("inventory_sidebar_link"));
    }

    public WebElement getCartIcon() {
        return driver.findElement(By.className("shopping_cart_link"));
    }
    //-------------------------
}
