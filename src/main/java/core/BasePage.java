package core;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class BasePage {
    WebDriver driver;

    /**
     *
     * @param locator
     * @return
     */
    public WebElement getWebElement(String locator) {
        return driver.findElement(By.cssSelector(locator));
    }
}
