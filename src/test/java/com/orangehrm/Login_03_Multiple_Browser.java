package com.orangehrm;
//import thu vien

import core.BasePage;
import core.BaseTest;
import javaSDET.Topic_01_Keywords;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.time.Duration;

public class Login_03_Multiple_Browser extends BaseTest {
    private WebDriver driver;
    private BasePage basePage;
    private String appUrl;

    @Parameters({"appUrl","browser"})
    @BeforeClass
    public void beforeClass(String appURL, String browserName) {
        this.appUrl = appURL;
        basePage = BasePage.getInstance();
        driver = getBrowserDriver(appURL, browserName);
    }

    @Test
    public void Login_01_Empty() {
        basePage.openPageURL(driver, appUrl);
        basePage.sendKeysToElement(driver,"//input[@name='username']", "");
        basePage.sendKeysToElement(driver,"//input[@name='password']", "");
        basePage.clickToElement(driver,"//button[contains(@class,'orangehrm-login-button')]");
//        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

        Assert.assertEquals(basePage.getElementText(driver,"//input[@name='username']//parent::div/following-sibling::span"), "Required");
        Assert.assertEquals(basePage.getElementText(driver, "//input[@name='password']//parent::div/following-sibling::span"), "Required");
    }

    @Test
    public void Login_02_Invalid_Username() {
        basePage.openPageURL(driver, appUrl);
        basePage.sendKeysToElement(driver,"//input[@name='username']", "Admin122");
        basePage.sendKeysToElement(driver,"//input[@name='password']", "admin123");
        basePage.clickToElement(driver,"//button[contains(@class,'orangehrm-login-button')]");

        Assert.assertEquals(basePage.getElementText(driver,"//div[@class='orangehrm-login-error']//p[contains(@class, 'oxd-alert-content-text')]"), "Invalid credentials");
    }

    @Test
    public void Login_03_Invalid_Password() {
        basePage.openPageURL(driver, appUrl);
        basePage.sendKeysToElement(driver,"//input[@name='username']", "Admin");
        basePage.sendKeysToElement(driver,"//input[@name='password']", "admin1234");
        basePage.sendKeysToElement(driver,"//input[@name='password']", "admin1234");
        basePage.clickToElement(driver,"//button[contains(@class,'orangehrm-login-button')]");

        Assert.assertEquals(basePage.getElementText(driver,"//div[@class='orangehrm-login-error']//p[contains(@class, 'oxd-alert-content-text')]"), "Invalid credentials");
    }

    @Test
    public void Login_04_Valid_Username_Password() {
        basePage.openPageURL(driver, appUrl);
        basePage.sendKeysToElement(driver,"//input[@name='username']", "Admin");
        basePage.sendKeysToElement(driver,"//input[@name='password']", "admin123");
        basePage.clickToElement(driver,"//button[contains(@class,'orangehrm-login-button')]");

        Assert.assertTrue(isAllLoadingSpinnerInvisible());
        Assert.assertEquals(basePage.getElementText(driver, "//div[@class='oxd-topbar-header-title']//h6"), "Dashboard");


    }

    public boolean isAllLoadingSpinnerInvisible() {
        return basePage.waitListElementInVisible(driver, "//div[@class='oxd-loading-spinner']");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
