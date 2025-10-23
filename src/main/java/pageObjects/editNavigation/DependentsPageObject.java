package pageObjects.editNavigation;

import core.BasePage;
import org.openqa.selenium.WebDriver;

public class DependentsPageObject extends EditNavigatorPageObject {
    private WebDriver driver;
    public DependentsPageObject(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

}
