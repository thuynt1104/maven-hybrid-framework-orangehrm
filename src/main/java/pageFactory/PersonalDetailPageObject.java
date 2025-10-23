package pageFactory;

import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class PersonalDetailPageObject extends BasePageFactory {
    private WebDriver driver;

    @FindBy(xpath = "//input[@name='firstName']")
    private WebElement firstNameTextBox;
    @FindBy(xpath = "//input[@name='lastName']")
    private WebElement lastNameTextBox;
    @FindBy(xpath = "//label[text()='Employee Id']/parent::div/following-sibling::div/input")
    private WebElement idTextBox;
    @FindBy(xpath = "//div[@class='oxd-loading-spinner']")
    private List<WebElement> loadingSpinner;

    public PersonalDetailPageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    public String getFirstNameTextboxValue() {
        waitElementVisible(driver, firstNameTextBox);
        return getElementDOMProperty(firstNameTextBox, "value");
    }

    public String getLastNameTextboxValue() {
        waitElementVisible(driver, lastNameTextBox);
        return getElementDOMProperty(lastNameTextBox, "value");
    }

    public String getEmployeeIDTextBoxValue() {
        waitElementVisible(driver, idTextBox);
        return getElementDOMProperty(idTextBox, "value");
    }

    public boolean isLoadingSpinnerDisappear() {
        return waitListElementInVisible(driver, loadingSpinner);
    }
}
