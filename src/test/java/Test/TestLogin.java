package Test;

import Base.BaseTest;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class TestLogin extends BaseTest {
//plan mi je da se svaki tet otvara u novom browser-u pa cu tako i napraviti before metodu. Tako da ce ici u After merodi zatvranje browser-a.

    @BeforeMethod
    public void pageSetup(){
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10)); //ubacila sam i explicitni waiter pa cu ga koristiti pozivom ako mi zatreba
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.manage().window().maximize();
        driver.navigate().to("https://www.saucedemo.com/");

//Nakon kreiranja klasa u paketu Pages, registrujem iliti iniciram ih unutar klase BaseTest a onda na osnovu njih kreiram objekte tih klasa u klasi Test






    }
}
