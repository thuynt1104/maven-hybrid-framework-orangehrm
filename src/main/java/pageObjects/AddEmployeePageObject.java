package pageObjects;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.AddEmployeePageUI;
import pageUIs.BasePageUI;

public class AddEmployeePageObject extends BasePage {
    private WebDriver driver;
    public AddEmployeePageObject(WebDriver driver) {
        this.driver = driver;
    }

    public void enterToFirstNameTextbox(String firstName) {
        waitElementVisible(driver, AddEmployeePageUI.FIRST_NAME_TEXTBOX);
        sendKeysToElement(driver, AddEmployeePageUI.FIRST_NAME_TEXTBOX, firstName);
    }

    public void enterToLastNameTextbox(String lastName) {
        waitElementVisible(driver, AddEmployeePageUI.LAST_NAME_TEXTBOX);
        sendKeysToElement(driver, AddEmployeePageUI.LAST_NAME_TEXTBOX, lastName);
    }

    public String getEmployeeID() {
        waitElementVisible(driver, AddEmployeePageUI.ID_TEXTBOX);
        return getElementDOMProperty(driver, AddEmployeePageUI.ID_TEXTBOX, "value");
    }

    public PersonalDetailPageObject clickToSaveButton() {
        waitElementClickable(driver, AddEmployeePageUI.SAVE_BUTTON);
        clickToElement(driver, AddEmployeePageUI.SAVE_BUTTON);
        waitListElementInVisible(driver, AddEmployeePageUI.SPINNER_ICON);
//        return PageGeneratorManager.getPersonalDetailPage(driver);
        return PageGeneratorGeneric.getPage(PersonalDetailPageObject.class, driver);
    }
}
