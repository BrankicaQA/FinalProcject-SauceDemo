package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseTest {
/*U klasi LoginPage koristim @FindBy anotacije za deklaraciju elemenata na stranici.
Konstruktor klase inicijalizuje elemente pomoću PageFactory.initElements, što je deo Page Object Model (POM) mehanizma.
Na ovaj način se obezbeđuje da su svi elementi spremni za upotrebu u testovima.
Nakon definisanja anotacija kreiram metode koje enkapsuliraju interakciju sa elementima (npr. unos korisničkog imena, lozinke, klik na login dugme),
čime se postiže čitljivost i ponovna upotrebljivost u testovima.*/

    public LoginPage(){
        PageFactory.initElements(driver, this);
    }

    @FindBy(id="user-name")
    public WebElement usernameField;
    @FindBy(id= "password")
    public WebElement passwordField;
    @FindBy(id= "login-button")
    public WebElement loginButton;
    @FindBy(id="inventory_container")
    public WebElement inventoryContainer;
    @FindBy(css="div.error-message-container.error")
    public WebElement errorMessage;

    //----------------

    public void loginSetup(String username, String password){
        usernameField.clear();
        usernameField.sendKeys(username);
        passwordField.clear();
        passwordField.sendKeys(password);
        loginButton.click();
    }

}
