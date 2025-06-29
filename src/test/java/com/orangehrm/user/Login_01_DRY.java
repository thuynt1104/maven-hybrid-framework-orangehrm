package com.orangehrm.user;
//import thu vien

import javaSDET.Topic_01_Keywords;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class Login_01_DRY {
    private WebDriver driver;
    private Topic_01_Keywords topic_01_keywords;

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }

    @Test
    public void Login_01_Empty() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();


        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='username']//parent::div/following-sibling::span")).getText(), "Required");
        Assert.assertEquals(driver.findElement(By.xpath("//input[@name='password']//parent::div/following-sibling::span")).getText(), "Required");

    }

    @Test
    public void Login_02_Invalid_Username() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin122");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

        Assert.assertEquals(driver.findElement(By.cssSelector(".orangehrm-login-error p.oxd-alert-content-text")).getText(), "Invalid credentials");

    }

    @Test
    public void Login_03_Invalid_Password() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin1234");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

        Assert.assertEquals(driver.findElement(By.xpath("//div[@class='orangehrm-login-error']//p[contains(@class, 'oxd-alert-content-text')]")).getText(), "Invalid credentials");

    }

    @Test
    public void Login_04_Valid_Username_Password() {
        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");
        driver.findElement(By.xpath("//input[@name='username']")).sendKeys("Admin");
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("admin123");
        driver.findElement(By.cssSelector("button.orangehrm-login-button")).click();

        Assert.assertTrue(isAllLoadingSpinnerInvisible());

        Assert.assertEquals(driver.findElement(By.cssSelector("h6.oxd-topbar-header-breadcrumb-module")).getText(), "Dashboard");

    }

    public boolean isAllLoadingSpinnerInvisible() {
        return new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(By.cssSelector("div.oxd-loading-spinner"))));
    }

    @AfterClass
    public void afterClass() {
        driver.quit();
    }
}
