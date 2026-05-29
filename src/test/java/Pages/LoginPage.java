package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseTest {
/*koristim anotaciju za pretpragu elementa (na osnovu lokatora) na stranici.
Kreiram konsturktor klase koji nosi ime stranice i koristim pomocni mehanicazam POM-a (PageFactory) koji automatski povezuje anotaciju sa elementom na stranici
Nakon kreiranja anotacija kreiram metode koje cu kasnije pozivati u testu
Ovaj princip rada primenicu na sve klse unutar Pages paketa*/

    public LoginPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="user-name")
    public WebElement usernameField;

    @FindBy(id= "password")
    public WebElement passwordField;

    @FindBy(id= "login-button")
    public WebElement loginButton;

    //----------------



}
