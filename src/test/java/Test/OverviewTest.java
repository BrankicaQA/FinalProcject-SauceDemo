package Test;

import Base.BaseTest;
import Pages.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class OverviewTest extends BaseTest {
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
        overviewPage= new OverviewPage();
    }
    public void precondition(){
        loginPage.loginSetup(validUsername, validPassword);
        inventoryPage.addProduct(0);
        cartPage.clickCartIcon();
        cartPage.clickCheckout();
        checkoutPage.setFirstName(FirstName);
        checkoutPage.setLastName(LastName);
        checkoutPage.setPostalCode(PostalCode);
        checkoutPage.clickContinueButton();



    }
    @Test
    public void testOverviewPageInformation() {
        precondition(); // dovodi korisnika do overview stranice
        // U ovom testu validiram da su na Checkout Overview stranici prisutne sve ključne sekcije i kontrole
        // (Payment Information, Shipping Information, Price Total, Cancel i Finish dugmad, kao i lista korpe),
        // bez detalja o konkretnim proizvodima
        Assert.assertTrue(overviewPage.cartList.isDisplayed());

        Assert.assertEquals(overviewPage.getSummaryInfoByName("Payment Information:"),
                "Payment Information:");

        Assert.assertEquals(overviewPage.getSummaryInfoByName("Shipping Information:"),
                "Shipping Information:");

        Assert.assertEquals(overviewPage.getSummaryInfoByName("Price Total"),
                "Price Total");

        Assert.assertTrue(overviewPage.cancelButton.isDisplayed());
        overviewPage.clickOnCancelButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

        driver.navigate().back();
        Assert.assertTrue(overviewPage.finishButton.isDisplayed());
        overviewPage.clickOnFinishButton();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/checkout-complete.html");




    }

    public void preconditionEmptyCart(){
        loginPage.loginSetup(validUsername, validPassword);
        cartPage.clickCartIcon();


        // Ako postoji proizvod u korpi uklanjam ga preko if metode.
        // S'obzirom da je ovo bug uhvacen kroz manuelno testiranje, ocekivano je da ce pasti asertaciju
        if (!cartPage.getCartItems().isEmpty()) {
            cartPage.clickOnRemoveButton();
        }
        cartPage.clickCheckout();

        checkoutPage.setFirstName(FirstName);
        checkoutPage.setLastName(LastName);
        checkoutPage.setPostalCode(PostalCode);
        checkoutPage.clickContinueButton();
    }
    @Test
    public void testCheckoutWithEmptyCart() {
       preconditionEmptyCart();
       overviewPage.clickOnFinishButton();

        // Očekivano ponašanje: korisnik NE prelazi na step-two
        Assert.assertEquals(driver.getCurrentUrl(),
                "https://www.saucedemo.com/checkout-step-one.html");

        // Provera da se prikazuje error poruka
        Assert.assertTrue(checkoutPage.errorMessage.isDisplayed());

    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }


}
