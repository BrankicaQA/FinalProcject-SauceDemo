package Test;

import Base.BaseTest;
import Pages.CartPage;
import Pages.InventoryPage;
import Pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class CartTest extends BaseTest {
    String validUsername = "standard_user";
    String validPassword = "secret_sauce";
    @BeforeMethod
    public void pageSetup(){
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); //ubacila sam i explicitni waiter pa cu ga koristiti pozivom ako mi zatreba
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");

        loginPage = new LoginPage();
        loginPage.loginSetup(validUsername, validPassword);
        cartPage = new CartPage();
        inventoryPage = new InventoryPage();
        inventoryPage.addProduct(0);

    }
    @Test
    public void verifyProductsAppearInCart() {

        inventoryPage.addProduct(1);

        cartPage.clickCartIcon();

        Assert.assertTrue(cartPage.getCartItems().size() >= 2);
    }

    @Test
    public void verifyQuantityOfProductsInCart() {
        cartPage.clickCartIcon();

        Assert.assertEquals(cartPage.getItemQuantity(0), "1");
    }

    @Test
    public void removeProductFromCart() {
        cartPage.clickCartIcon();
        cartPage.removeItem(0);

        Assert.assertTrue(inventoryPage.getCartQuantity().isEmpty());
    }

    @Test
    public void continueShoppingButton() {
        cartPage.clickCartIcon();
        cartPage.clickContinueShopping();

        Assert.assertTrue(inventoryPage.getProductCount() > 0);
    }

    @Test
    public void checkoutButton() {
        cartPage.clickCartIcon();
        cartPage.clickCheckout();

        Assert.assertTrue(driver.getCurrentUrl().contains("checkout-step-one"));
    }



    @AfterMethod
    public void closeApp(){
        driver.quit();
    }
}
