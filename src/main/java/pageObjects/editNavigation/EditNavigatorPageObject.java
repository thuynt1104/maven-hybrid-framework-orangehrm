package pageObjects.editNavigation;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageObjects.PageGeneratorGeneric;
import pageUIs.BasePageUI;
import pageUIs.editNavigation.EditNavigatorPageUI;

public class EditNavigatorPageObject extends BasePage {
    WebDriver driver;

    public EditNavigatorPageObject(WebDriver driver) {
        this.driver = driver;
    }

    //9 hàm nằm trong Edit Employee
    public ContactDetailPageObject openContactDetailPage() {
        waitElementVisible(driver, EditNavigatorPageUI.CONTACT_DETAIL_LINK);
        clickToElement(driver, EditNavigatorPageUI.CONTACT_DETAIL_LINK);
        return PageGeneratorGeneric.getPage(ContactDetailPageObject.class, driver);
    }
    public JobPageObject openJobPage() {
        waitElementClickable(driver, EditNavigatorPageUI.JOB_LINK);
        clickToElement(driver, EditNavigatorPageUI.JOB_LINK);
        return PageGeneratorGeneric.getPage(JobPageObject.class, driver);
    }

    public PersonalDetailPageObject openPersonalDetailPage() {
        waitElementClickable(driver, EditNavigatorPageUI.PERSONAL_DETAILS_LINK);
        clickToElement(driver, EditNavigatorPageUI.PERSONAL_DETAILS_LINK);
        return PageGeneratorGeneric.getPage(PersonalDetailPageObject.class, driver);
    }

    public DependentsPageObject openDenpendentsPage() {
        waitElementClickable(driver, EditNavigatorPageUI.DEPENDENTS_LINK);
        clickToElement(driver, EditNavigatorPageUI.DEPENDENTS_LINK);
        return PageGeneratorGeneric.getPage(DependentsPageObject.class, driver);
    }


}
