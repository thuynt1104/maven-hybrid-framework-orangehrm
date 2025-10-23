package pageObjects.editNavigation;

import core.BasePage;
import org.openqa.selenium.WebDriver;

public class ContactDetailPageObject extends EditNavigatorPageObject {
    private WebDriver driver;
    public ContactDetailPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }
}
