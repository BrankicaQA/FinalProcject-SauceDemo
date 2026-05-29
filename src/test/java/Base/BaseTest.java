package Base;

import Pages.LoginPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    public static WebDriver driver;
    public WebDriverWait wait;
    //ovde na neki nacin "registrujem" sve klase stranica
    public LoginPage loginPage;

    @BeforeClass
    public void setup(){
        WebDriverManager.firefoxdriver().setup();

    }

    public void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    } //ubacila sam i ovu metodu ako budem imala problema sa nalazenjem elementa na stranici ako se prethodno ne skroluje do tog elementa

}
