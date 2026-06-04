package Test;

import Base.BaseTest;
import Pages.CartPage;
import Pages.InventoryPage;
import Pages.LoginPage;
import Pages.ProductDetailPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ProductDetailTest extends BaseTest {
    String validUsername = "standard_user";
    String validPassword = "secret_sauce";
    @BeforeMethod
    public void pageSetup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); //ubacila sam i explicitni waiter pa cu ga koristiti pozivom ako mi zatreba
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");

        loginPage = new LoginPage();
        inventoryPage = new InventoryPage();
        cartPage = new CartPage();
        productDetailPage= new ProductDetailPage();

        loginPage.loginSetup(validUsername, validPassword);}


    @Test(priority = 1)
    public void verifyProductDetailPageForSauceLabsBackpack() {

        inventoryPage.addBackpackToCart();
        Assert.assertEquals(inventoryPage.cartBadge.getText(), "1");

        inventoryPage.openBackpackDetails();
        Assert.assertEquals(productDetailPage.getButtonText(), "Remove");

        productDetailPage.clickAddRemoveButton();
        Assert.assertEquals(productDetailPage.getButtonText(), "Add to cart");

        productDetailPage.clickAddRemoveButton();
        Assert.assertEquals(productDetailPage.getButtonText(), "Remove");

        productDetailPage.clickCartIcon();
        Assert.assertTrue(cartPage.containsProduct("Sauce Labs Backpack"));
        Assert.assertEquals(cartPage.getProductName(0), "Sauce Labs Backpack");
        Assert.assertEquals(cartPage.getProductPrice(0), "$29.99");
        Assert.assertEquals(cartPage.getItemQuantity(0), "1");

        driver.navigate().back();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory-item"));

        productDetailPage.clickBackToProducts();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory"));
    }

    @Test(priority = 2)
    public void compareProductDetailsBetweenInventoryAndProductDetailPage() {

        String inventoryName = inventoryPage.getProductName();
        String inventoryPrice = inventoryPage.getProductPrice();
        String inventoryDescription = inventoryPage.getProductDescription();

        inventoryPage.openBackpackDetails();
        Assert.assertEquals(productDetailPage.getProductName(), inventoryName);
        Assert.assertEquals(productDetailPage.getProductPrice(), inventoryPrice);
        Assert.assertTrue(productDetailPage.getProductDescription().contains(inventoryDescription));
        Assert.assertTrue(productDetailPage.isProductImageDisplayed());
    }




    @AfterMethod
    public void tearDown(){
        driver.quit();
    }



    }
