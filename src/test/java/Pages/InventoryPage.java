package Pages;

import Base.BaseTest;
import org.openqa.selenium.support.PageFactory;

public class InventoryPage extends BaseTest {
    public InventoryPage(){
        PageFactory.initElements(driver, this);
    }


}
