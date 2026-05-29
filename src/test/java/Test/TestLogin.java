package Test;

import Base.BaseTest;
import Pages.LoginPage;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.Objects;

public class TestLogin extends BaseTest {
//plan mi je da se svaki tet otvara u novom browser-u pa cu tako i napraviti before metodu. Tako da ce ici u After merodi zatvranje browser-a.
    String validUsername = "standard_user";
    String validPassword = "secret_sauce";
    String emptyPasswordField = "";
    @BeforeMethod
    public void pageSetup(){
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); //ubacila sam i explicitni waiter pa cu ga koristiti pozivom ako mi zatreba
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");

//Nakon kreiranja klasa u paketu Pages, registrujem iliti iniciram ih unutar klase BaseTest a onda na osnovu njih kreiram objekte tih klasa u klasi Test

        loginPage = new LoginPage();



    }

    @Test
    public void loginTest() {
        loginPage.loginSetup(validUsername, validPassword);

        String expectedURL = "https://www.saucedemo.com/inventory.html";
        Assert.assertEquals(driver.getCurrentUrl(), expectedURL);
        Assert.assertFalse(Objects.requireNonNull(driver.getCurrentUrl()).contains("login"));
        Assert.assertTrue(loginPage.inventoryContainer.isDisplayed());
    }

    @Test
    public void loginWithEmptyPasswordTest(){
        loginPage.loginSetup(validUsername, emptyPasswordField);

        Assert.assertTrue(loginPage.errorMessage.isDisplayed());
    }

    @AfterMethod
    public void closeApp(){
        driver.close();
    }
}
