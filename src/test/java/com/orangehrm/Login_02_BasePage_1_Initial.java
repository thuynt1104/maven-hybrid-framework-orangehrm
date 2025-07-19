package com.orangehrm;
//import thu vien

import core.BasePage;
import javaSDET.Topic_01_Keywords;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Login_02_BasePage_1_Initial {
    private WebDriver driver;
    private Topic_01_Keywords topic_01_keywords;
    private BasePage basePage;
    private String appUrl = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";


    @BeforeClass
    public void beforeClass() {
        driver = new FirefoxDriver();
        basePage = new BasePage();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
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
