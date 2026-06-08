package Test;

import Base.BaseTest;
import Pages.CartPage;
import Pages.InventoryPage;
import Pages.LoginPage;
import Pages.ProductDetailPage;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class ProductDetailTest extends BaseTest {
    // Kredencijali validnog korisnika koji će se koristiti za logovanje pre svakog testa
    String validUsername = "standard_user";
    String validPassword = "secret_sauce";
    //U @BeforeMethod metodi se vrši inicijalno podešavanje okruženja koje je potrebno za izvršavanje svakog testa.
    // Pokreće se Firefox browser pomoću FirefoxDriver klase, zatim se kreira eksplicitni wait (WebDriverWait) koji će se koristiti za čekanje elemenata
    // kada je to potrebno tokom testiranja. Podešava se i implicitno čekanje kako bi Selenium automatski čekao da se elementi pojave u DOM-u pre nego što izbaci grešku.
    // Nakon toga se prozor browsera maksimalizuje kako bi svi elementi bili pravilno vidljivi i dostupni za interakciju, a zatim se otvara početna stranica aplikacije Saucedemo.
    // Na kraju se inicijalizuju Page Object klase koje će se koristiti u testovima (LoginPage, InventoryPage, CartPage i ProductDetailPage), i vrši se
    // logovanje validnim korisnikom kako bi svaki test počinjao iz iste početne, validne sesije aplikacije.
    @BeforeMethod
    public void pageSetup() {
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); //ubacila sam i explicitni waiter pa cu ga koristiti pozivom ako mi zatreba
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");

        loginPage = new LoginPage();
        inventoryPage = new InventoryPage();
        cartPage = new CartPage();
        productDetailPage= new ProductDetailPage();

        loginPage.loginSetup(validUsername, validPassword);}

//U ovom testu se proverava kompletan tok rada sa proizvodom “Sauce Labs Backpack”, od dodavanja u korpu sa Inventory stranice,
// preko provere badge-a na korpi, do otvaranja Product Detail stranice i validacije promene dugmeta između “Add to cart” i “Remove”.
// Zatim se proverava da li se proizvod ispravno prikazuje u korpi sa tačnim nazivom, cenom i količinom, kao i navigacija kroz aplikaciju i povratak na Inventory stranicu.
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
//U ovom testu se porede podaci o proizvodu između Inventory i Product Detail stranice.
// Proverava se da li su naziv, cena i opis konzistentni na obe strane, kao i da li je slika proizvoda ispravno prikazana na stranici sa detaljima,
// čime se validira doslednost prikaza podataka kroz aplikaciju.
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
    //Zatvaranje browsera nakon svakog testa
    @AfterMethod
    public void tearDown(){
        driver.quit();
    }



    }
