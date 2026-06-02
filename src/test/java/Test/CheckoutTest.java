package Test;

import Base.BaseTest;
import Pages.CartPage;
import Pages.CheckoutPage;
import Pages.InventoryPage;
import Pages.LoginPage;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

import static org.testng.Assert.assertEquals;

public class CheckoutTest extends BaseTest {
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
        loginPage.loginSetup(validUsername, validPassword);
        cartPage = new CartPage();
        inventoryPage = new InventoryPage();
        checkoutPage = new CheckoutPage();


    }
    public void addProduct(){
        inventoryPage.addProduct(0);
        cartPage.clickCartIcon();
        cartPage.clickCheckout();
    }

    @Test
    public void checkoutTest(){
        addProduct();
        checkoutPage.setFirstName(FirstName);
        checkoutPage.setLastName(LastName);
        checkoutPage.setPostalCode(PostalCode);
        checkoutPage.clickContinueButton();
        String expectedURL="https://www.saucedemo.com/checkout-step-two.html";

        assertEquals(driver.getCurrentUrl(), expectedURL);

    }



    @AfterMethod
    public void tearDown(){
        driver.quit();
    }






}






