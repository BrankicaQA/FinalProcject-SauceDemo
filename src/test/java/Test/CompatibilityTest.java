package Test;

import Base.BaseTest;
import Pages.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.time.Duration;

public class CompatibilityTest extends BaseTest {
    String validUsername = "standard_user";
    String validPassword = "secret_sauce";
    @BeforeClass
    public void setup() throws IOException {
        WebDriverManager.chromedriver().setup();}
    @BeforeMethod
    public void pageSetup() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); //ubacila sam i explicitni waiter pa cu ga koristiti pozivom ako mi zatreba
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");

        loginPage = new LoginPage();
        inventoryPage = new InventoryPage();
        loginPage.loginSetup(validUsername, validPassword);
    }
    @Test
    public void testLoginChromeBrowser() {
        Assert.assertEquals(driver.getCurrentUrl(),
                "https://www.saucedemo.com/inventory.html");
    }

    @AfterMethod
    public void tearDown(){
        driver.quit();
    }

}
