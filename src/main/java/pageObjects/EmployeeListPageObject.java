package pageObjects;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.EmployeeListPageUI;

public class EmployeeListPageObject extends BasePage {
    private WebDriver driver;
    public EmployeeListPageObject(WebDriver driver) {
        this.driver = driver;
    }
    public AddEmployeePageObject clickToAddEmployeeButton() {
        waitElementClickable(driver,EmployeeListPageUI.ADD_EMPLOYEE);
        clickToElement(driver,EmployeeListPageUI.ADD_EMPLOYEE);
//        return PageGeneratorManager.getAddEmployeePage(driver);
        return PageGeneratorGeneric.getPage(AddEmployeePageObject.class,driver);
    }
}
