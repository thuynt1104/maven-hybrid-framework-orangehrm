package pageObjects;

import core.BasePage;
import org.openqa.selenium.WebDriver;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePage {
    private WebDriver driver;
    //Ham khoi tao
    //Map driver tu test class sang PageObject class
    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
    }
    //Ham se duoc chay dau tien khi class nay duoc goi toi
    //Neu khong viet contractor cho no thi no se tu tao cho minh 1 contractor rong
    //Cung ten voi no
    //Co 1 hoac nhieu ham khoi tao
    //Khong co gia tri tra ve
    //The hien tinh chat da hinh trong OPP

    public void enterToUsernameTextBox(String username) {
        //Rap action + ui
        waitElementVisible(driver,LoginPageUI.USERNAME_TEXTBOX);
        sendKeysToElement(driver, LoginPageUI.USERNAME_TEXTBOX, username);
    }

    public void enterToPasswordTextBox(String password) {
        waitElementVisible(driver,LoginPageUI.PASSWORD_TEXTBOX);
        sendKeysToElement(driver, LoginPageUI.PASSWORD_TEXTBOX, password);
    }

    public DashboardPageObject clickToLoginButton() {
        waitElementClickable(driver,LoginPageUI.LOGIN_BUTTON);
        clickToElement(driver,LoginPageUI.LOGIN_BUTTON);
        return PageGeneratorGeneric.getPage(DashboardPageObject.class, driver);

    }
}
