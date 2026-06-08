package Pages;

import Base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class OverviewPage extends BaseTest {
    public OverviewPage() {
        PageFactory.initElements(driver, this);
    }
    @FindBy(className = "cart_list")
    public WebElement cartList;
    @FindBy(className = "summary_info_label")
    List<WebElement> summaryInfoLabels;
    @FindBy(id= "cancel")
    public WebElement cancelButton;
    @FindBy(id="finish")
    public WebElement finishButton;

    //-------------------------

    public String getSummaryInfoByName(String name) {
        return summaryInfoLabels.stream()
                .map(WebElement::getText)
                .filter(text -> text.contains(name))
                .findFirst()
                .orElse("Not found");
    }
    public void clickOnCancelButton(){
        cancelButton.click();
    }
    public void clickOnFinishButton(){
        finishButton.click();
    }
}