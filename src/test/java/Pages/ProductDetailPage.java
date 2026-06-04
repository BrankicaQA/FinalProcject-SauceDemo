package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductDetailPage extends BaseTest {
    public ProductDetailPage() {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "inventory_details_name")
    public WebElement productName;

    @FindBy(className = "inventory_details_desc")
    public WebElement productDescription;

    @FindBy(className = "inventory_details_price")
    public WebElement productPrice;

    @FindBy(className = "inventory_details_img")
    public WebElement productImage;

    @FindBy(css = "button.btn_inventory")
    public WebElement addRemoveButton;

    @FindBy(className = "shopping_cart_link")
    public WebElement cartIcon;

    @FindBy(id = "back-to-products")
    public WebElement backToProductsButton;

    public void clickAddRemoveButton() {
        addRemoveButton.click();
    }

    public void clickCartIcon() {
        cartIcon.click();
    }

    public void clickBackToProducts() {
        backToProductsButton.click();
    }

    public String getProductName() {
        return productName.getText();
    }

    public String getProductPrice() {
        return productPrice.getText();
    }

    public String getProductDescription() {
        return productDescription.getText();
    }

    public String getButtonText() {
        return addRemoveButton.getText();
    }
    public boolean isProductImageDisplayed(){
        return productImage.isDisplayed();
    }
}