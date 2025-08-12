package pageFactory;

import core.BasePage;
import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import pageUIs.AddEmployeePageUI;

import java.util.List;

public class AddEmployeePageObject extends BasePageFactory {
    private WebDriver driver;

    @FindBy(xpath = "//input[@name='firstName']")
    private WebElement firstNameTextBox;
    @FindBy(xpath = "//input[@name='lastName']")
    private WebElement lastNameTextBox;
    @FindBy(xpath = "//label[text()='Employee Id']/parent::div/following-sibling::div/input")
    private WebElement idTextBox;
    @FindBy(xpath = "//button[contains(string(),'Save')]")
    private WebElement saveButton;
    @FindBy(xpath = "//div[@class='oxd-loading-spinner']")
    private List<WebElement> loadingSpinner;

    public AddEmployeePageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterToFirstNameTextbox(String firstName) {
        waitElementVisible(driver, firstNameTextBox);
        sendKeysToElement(firstNameTextBox, firstName);
    }

    public void enterToLastNameTextbox(String lastName) {
        waitElementVisible(driver, lastNameTextBox);
        sendKeysToElement(lastNameTextBox, lastName);
    }

    public String getEmployeeID() {
        waitElementVisible(driver, idTextBox);
        return getElementDOMProperty(idTextBox, "value");
    }

    public void clickToSaveButton() {
        waitElementClickable(driver, saveButton);
        clickToElement(saveButton);
    }

    public boolean isLoadingSpinnerDisappear() {
        return waitListElementInVisible(driver, loadingSpinner);
    }
}
