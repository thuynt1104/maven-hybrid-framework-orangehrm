package pageFactory;

import core.BasePage;
import core.BasePageFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import pageUIs.LoginPageUI;

public class LoginPageObject extends BasePageFactory {
    private WebDriver driver;
    @FindBy (how = How.XPATH, using = "//input[@name='username']")
    private WebElement usernameTextBox;
    @FindBy (how = How.XPATH, using = "//input[@name='password']")
    private WebElement passwordTextBox;
    @FindBy(xpath = "//button[contains(@class,'orangehrm-login-button')]")
    private WebElement loginButton;

    //Define element/locator
    public LoginPageObject(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    // Bắt buộc Locator/Element phải là kiểu dữ liệu WebElement

    public void enterToUsernameTextBox(String username) {
        waitElementVisible(driver, usernameTextBox);
        sendKeysToElement(usernameTextBox, username);
    }

    public void enterToPasswordTextBox(String password) {
        waitElementVisible(driver, passwordTextBox);
        sendKeysToElement(passwordTextBox, password);
    }

    public void clickToLoginButton() {
        waitElementClickable(driver, loginButton);
        clickToElement(loginButton);
    }
}
