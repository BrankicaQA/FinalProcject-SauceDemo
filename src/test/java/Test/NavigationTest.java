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

    @Test
    public void testResetAppState() {
        navigationPage.openBurgerMenu();
        navigationPage.clickResetAppState();
        boolean cartEmpty = driver.findElements(By.className("shopping_cart_badge")).isEmpty();
        Assert.assertTrue(cartEmpty, "Cart was not emptied after reset");
    }

    @Test
    public void testAllItemsOption() {
        navigationPage.openBurgerMenu();
        navigationPage.clickAllItems();
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory.html"),
                "User was not redirected to inventory page");
    }

    @Test
    public void testCloseBurgerMenuOutsideClick() {
        navigationPage.openBurgerMenu();
        // Simulate outside click → click on page body
        driver.findElement(By.className("inventory_list")).click();
        Assert.assertFalse(navigationPage.getLogoutOption().isDisplayed(),
                "Burger menu did not close after outside click");
    }

    @Test
    public void testNavigationOptionsVisible() {
        navigationPage.openBurgerMenu();
        Assert.assertTrue(navigationPage.getAllItemsOption().isDisplayed(), "All Items not visible");
        Assert.assertTrue(navigationPage.getAboutOption().isDisplayed(), "About not visible");
        Assert.assertTrue(navigationPage.getLogoutOption().isDisplayed(), "Logout not visible");
        Assert.assertTrue(navigationPage.getResetAppStateOption().isDisplayed(), "Reset App State not visible");
    }

    @Test
    public void testCloseBurgerMenuWithX() {
        navigationPage.openBurgerMenu();
        navigationPage.closeBurgerMenu();
        Assert.assertFalse(navigationPage.getLogoutOption().isDisplayed(),
                "Burger menu did not close after clicking X");
    }



    @AfterMethod
    public void tearDown(){
        driver.quit();
    }
}
