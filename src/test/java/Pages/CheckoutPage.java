package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BaseTest {
    public CheckoutPage(){
        PageFactory.initElements(driver, this);}

    @FindBy(id="first-name")
    public WebElement firstName;
    @FindBy(id="last-name")
    public WebElement lastName;
    @FindBy(id="postal-code")
    public WebElement postalCode;
    @FindBy(className = "error-message-container")
    public WebElement errorMessage;
    @FindBy(id="continue")
    public WebElement continueButton;
    @FindBy(id="cancel")
    public WebElement cancelButton;

    //--------------------- pravljenje metoda na osnogu definisanih lokatora i Webelemenata

    public void setFirstName(String FirstName){
        firstName.sendKeys(FirstName);
    }
    public void setLastName(String LastName){
        lastName.sendKeys(LastName);
    }
    public void setPostalCode(String PostalCode){
        postalCode.sendKeys(PostalCode);
    }

    public void clickContinueButton(){
        continueButton.click();
    }
    public void clickCancelButton(){
        cancelButton.click();
    }















}