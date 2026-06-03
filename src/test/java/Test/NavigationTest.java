package Test;

import Base.BaseTest;
import Pages.InventoryPage;
import Pages.LoginPage;
import Pages.NavigationPage;
import org.openqa.selenium.By;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class NavigationTest extends BaseTest {
    String validUsername = "standard_user";
    String validPassword = "secret_sauce";
    @BeforeMethod
    public void setupPage(){
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); //ubacila sam i explicitni waiter pa cu ga koristiti pozivom ako mi zatreba
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");

        loginPage = new LoginPage();
        loginPage.loginSetup(validUsername, validPassword);
        inventoryPage = new InventoryPage();

        navigationPage = new NavigationPage(driver);
    }

    @Test
    public void testBurgerMenuOpens() {
        navigationPage.openBurgerMenu();
        Assert.assertTrue(navigationPage.getLogoutOption().isDisplayed(),
                "Burger menu did not open correctly");
    }

    @Test
    public void testLogoutOption() {
        navigationPage.openBurgerMenu();
        navigationPage.clickLogout();
        Assert.assertTrue(driver.getCurrentUrl().contains("saucedemo.com"),
                "User was not redirected to login page");
    }

    @Test
    public void testAboutOption() {
        navigationPage.openBurgerMenu();
        navigationPage.clickAbout();
        Assert.assertTrue(driver.getCurrentUrl().contains("saucelabs.com"),
                "User was not redirected to Sauce Labs website");
    }

    @Test //ovaj test ocekujemo da ce da padne na drugu asertaciju jer je uocen BUG
    public void testResetAppState() {
        inventoryPage.addProduct(0);
        navigationPage.openBurgerMenu();
        navigationPage.clickResetAppState();

        Assert.assertTrue(driver.findElements(By.className("shopping_cart_icon")).isEmpty());

        //kroz maunelno tesitranje uocen je bug da se  dugme "Remove" ne resetuje na "Add to cart".
        //S'obzirom da sam dodala proizvod sa indeksom 0 onda je logicno da proveravam i da li se isit resetovao nakon pozivanja "clickResetAppState()" metode

        Assert.assertEquals(inventoryPage.getProductButton(0).getText(), "Add to cart");

    }

    @Test
    public void testAllItemsOption() {
        navigationPage.openBurgerMenu();
        navigationPage.clickAllItems();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));
    }

    @Test
    public void testCloseBurgerMenuOutsideClick() {
        navigationPage.openBurgerMenu();

        driver.findElement(By.className("inventory_list")).click();
        Assert.assertFalse(navigationPage.getLogoutOption().isDisplayed());
    }

    @Test
    public void testNavigationOptionsVisible() {
        navigationPage.openBurgerMenu();
        Assert.assertTrue(navigationPage.getAllItemsOption().isDisplayed());
        Assert.assertTrue(navigationPage.getAboutOption().isDisplayed());
        Assert.assertTrue(navigationPage.getLogoutOption().isDisplayed());
        Assert.assertTrue(navigationPage.getResetAppStateOption().isDisplayed());
    }

    @Test
    public void testCloseBurgerMenuWithX() {
        navigationPage.openBurgerMenu();
        navigationPage.closeBurgerMenu();

        Assert.assertTrue(driver.findElements(By.id("bm-menu")).isEmpty());
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"));

        Assert.assertTrue(driver.findElement(By.className("inventory_list")).isDisplayed());
    }



    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
