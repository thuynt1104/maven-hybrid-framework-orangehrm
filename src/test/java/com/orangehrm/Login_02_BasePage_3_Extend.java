package com.orangehrm;
//import thu vien

import core.BasePage;
import javaSDET.Topic_01_Keywords;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Login_02_BasePage_3_Extend extends BasePage {
    private WebDriver driver;
    private Topic_01_Keywords topic_01_keywords;
    private String appUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";


    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void Login_01_Empty() {
        openPageURL(driver, appUrl);
        sendKeysToElement(driver,"//input[@name='username']", "");
        sendKeysToElement(driver,"//input[@name='password']", "");
        clickToElement(driver,"//button[contains(@class,'orangehrm-login-button')]");
//        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

        Assert.assertEquals(getElementText(driver,"//input[@name='username']//parent::div/following-sibling::span"), "Required");
        Assert.assertEquals(getElementText(driver, "//input[@name='password']//parent::div/following-sibling::span"), "Required");
    }

    @Test
    public void Login_02_Invalid_Username() {
        openPageURL(driver, appUrl);
        sendKeysToElement(driver,"//input[@name='username']", "Admin122");
        sendKeysToElement(driver,"//input[@name='password']", "admin123");
        clickToElement(driver,"//button[contains(@class,'orangehrm-login-button')]");

        Assert.assertEquals(getElementText(driver,"//div[@class='orangehrm-login-error']//p[contains(@class, 'oxd-alert-content-text')]"), "Invalid credentials");
    }

    @Test
    public void Login_03_Invalid_Password() {
        openPageURL(driver, appUrl);
        sendKeysToElement(driver,"//input[@name='username']", "Admin");
        sendKeysToElement(driver,"//input[@name='password']", "admin1234");
        clickToElement(driver,"//button[contains(@class,'orangehrm-login-button')]");

        Assert.assertEquals(getElementText(driver,"//div[@class='orangehrm-login-error']//p[contains(@class, 'oxd-alert-content-text')]"), "Invalid credentials");
    }

    @Test
    public void Login_04_Valid_Username_Password() {
        openPageURL(driver, appUrl);
        sendKeysToElement(driver,"//input[@name='username']", "Admin");
        sendKeysToElement(driver,"//input[@name='password']", "admin123");
        clickToElement(driver,"//button[contains(@class,'orangehrm-login-button')]");

        Assert.assertTrue(isAllLoadingSpinnerInvisible());
        Assert.assertEquals(getElementText(driver, "//div[@class='oxd-topbar-header-title']//h6"), "Dashboard");


    }

    public boolean isAllLoadingSpinnerInvisible() {
        return waitListElementInVisible(driver, "//div[@class='oxd-loading-spinner']");
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
