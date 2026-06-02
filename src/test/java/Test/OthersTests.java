package Test;

import Base.BaseTest;
import Pages.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class OthersTests extends BaseTest {
    String validUsername = "standard_user";
    String validPassword = "secret_sauce";
    String FirstName ="Brankica";
    String LastName = "Rajkovic";
    String PostalCode = "18000";
    @BeforeMethod
    public void pageSetup() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); //ubacila sam i explicitni waiter pa cu ga koristiti pozivom ako mi zatreba
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");

        loginPage = new LoginPage();
        cartPage = new CartPage();
        inventoryPage = new InventoryPage();
        checkoutPage = new CheckoutPage();
        overviewPage = new OverviewPage();


    }
    @Test
    public void testSessionTimeout() throws InterruptedException {
        loginPage.loginSetup(validUsername, validPassword);
        inventoryPage.addProduct(0);

        // ostani neaktivan 5 minuta
        Thread.sleep(300000);

        // pokusaj da nastavis kupovinu
        inventoryPage.clickOnCartIcon();


        //  očekujemo da sesija istekne i da se pojavi login stranica
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/");
        Assert.assertTrue(loginPage.errorMessage.isDisplayed(),
                "Session timeout message should be displayed");
    }

    @Test
    public void testCartIconRapidClicks() {
        loginPage.loginSetup(validUsername, validPassword);
        // for petlju pozivam kako bi se vise puta dodao/izbacio proizvod iz korpe
        for (int i = 0; i < 10; i++) {
            inventoryPage.addProduct(0);
            inventoryPage.removeProduct(0);
        }

        //asertacija jer očekujemo da se cart ikonica ponaša konzistentno
        Assert.assertEquals(cartPage.getCartIconCount(), 0);
    }

    @Test
    public void testLoginSqlInjection() {
        loginPage.loginSetup("' OR '1'='1", "secret_sauce");

        //očekujemo neuspešan login
        Assert.assertTrue(loginPage.errorMessage.isDisplayed());
        Assert.assertNotEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }




    @AfterMethod
    public void tearDown(){
        driver.quit();
    }


}
