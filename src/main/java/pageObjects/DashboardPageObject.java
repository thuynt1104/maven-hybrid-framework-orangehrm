package pageObjects;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.DashboardPageUI;

public class DashboardPageObject extends BasePage {
    private WebDriver driver;
    public DashboardPageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void clickToPIMModule() {
        waitElementClickable(driver, DashboardPageUI.PIM_MENU);
        clickToElement(driver, DashboardPageUI.PIM_MENU);
    }
}
