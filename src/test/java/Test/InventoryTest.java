package Test;

import Base.BaseTest;
import Pages.InventoryPage;
import Pages.LoginPage;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class InventoryTest extends BaseTest {

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
        inventoryPage = new InventoryPage();
    }
    @Test
    public void verifyInventoryPage() {
        Assert.assertTrue(inventoryPage.getProductCount() > 0,
                "All products should be visible on inventory page");
    }
    @Test
    public void addSingleProductToCart() {
        inventoryPage.addProduct(0);
        Assert.assertEquals(inventoryPage.getCartQuantity(), "1",
                "Cart icon should show quantity 1");
    }
    @Test
    public void addMultipleProductsToCart() {
        inventoryPage.addProduct(0);
        inventoryPage.addProduct(1);
        inventoryPage.addProduct(2);
        Assert.assertEquals(inventoryPage.getCartQuantity(), "3");
    }

    @Test
    public void removeProductFromCart() {
        inventoryPage.addProduct(0);
        inventoryPage.removeProduct(0);
        Assert.assertTrue(inventoryPage.getCartQuantity().isEmpty());
    }
@AfterMethod
    public void closeApp(){
        driver.close();
}


}
