package Base;

import Pages.InventoryPage;
import Pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

public class BaseTest {
    public static WebDriver driver;
    public WebDriverWait wait;
    //ovde inicijalizujem sve klase paketa Pages
    public LoginPage loginPage;
    public InventoryPage inventoryPage;
    public ExcelReader excelReader;


    @BeforeClass
    public void setup() throws IOException {
        WebDriverManager.firefoxdriver().setup();
/*U ovom delu koda koristicu ExcelReader klasu koja se koristi da odvoji test podatke od samog koda.
Na ovaj način svi username-i i password-i stoje u Excel fajlu,
pa se testovi lakše održavaju i menjaju bez potrebe da se menja Java kod.
Ovaj pristup se zove data-driven testing i omogućava da jedan test pokrije više scenarija*/

        excelReader = new ExcelReader("AllUsers.xlsx");

    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    } //ubacila sam i ovu metodu ako budem imala problema sa nalazenjem elementa na stranici ako se prethodno ne skroluje do tog elementa

}
