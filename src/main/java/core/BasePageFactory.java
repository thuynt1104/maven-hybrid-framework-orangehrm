package core;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import pageUIs.BasePageUI;

import java.time.Duration;
import java.util.List;
import java.util.Set;

public class BasePageFactory {

    private final int SHORT_TIME_OUT = 10;
    private final int LONG_TIME_OUT = 30;
    //    private WebDriver driver;
//    1 - Access Modifier: public/ protected/ private/ default
    //public: tất cả các class trong cùng package/ khác package cũng sẽ sử dụng được
    //         chỉ muốn cho class nào kế thừa thì mới sử dụng được thôi => không dùng public
    //protected: Chỉ những class kế thừa mới sử dụng được => dùng protected
    //private : Chỉ có các hàm trong cùng class sử dụng
    //default : Chỉ cho các class trong cùng 1 package sử dụng => không dùng default
    //          Các class trong trong package pageObjects không sử dụng được
//    2 - Kiểu dữ liệu của hàm (Data type): void/ int/ String/ boolean/ WebElement/ List<WebElement>/..
//        Nó sẽ liên quan đến cái chức năng mình viết trong thân hàm
    // Dùng hàm nào của selenium thì sẽ trả về cái đó => theo define kiểu dữ liệu của hàm tương ứng
//    3 - Tên hàm: Đặt tên có nghĩa theo chức năng đang cần viết
//        Convention tuân theo chuẩn của từng ngôn ngữ lập trình (Java)
//        lowerCamelCase: từ đầu tiên viết thường - chữ cái đầu tiên của các từ tiếp theo sẽ viết hoa
//    4 - Có tham số hay ko (tùy vào chức năng cần viết)
     //  Dùng cái hàm nào của selenium

//    5 - Kiểu dữ liệu trả về cho hàm
//        Nếu như có return dữ liệu thì sẽ khớp vs kiểu dữ liệu ở số 2
//        Nếu như có return thì nó là cái step cuối cùng

    //ham khoi tao co nhiem vu lay ra Instance chinh basepage nay
    // Mot bien hoac 1 ham co the goi truc tiep tu pham vi class
    public static BasePageFactory getInstance() {
        return new BasePageFactory();
    }

    //Viết 1 hàm getText của 1 element bất kì
    public void openPageURL(WebDriver driver, String url) {
        driver.get(url);
    }

    public String getTitle(WebDriver driver) {
        return driver.getTitle();
    }

    public String getPageURL(WebDriver driver) {
        return driver.getCurrentUrl();
    }

    public String getPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    public void backToPage(WebDriver driver) {
        driver.navigate().back();
    }

    public void forwardToPage(WebDriver driver) {
        driver.navigate().forward();
    }

    public void refreshPage(WebDriver driver) {
        driver.navigate().refresh();
    }

    public Alert waitAlertPresence(WebDriver driver) {
        return new WebDriverWait(driver, Duration.ofSeconds(5)).until(ExpectedConditions.alertIsPresent());
    }

    public void exceptToAlert(WebDriver driver) {
        //Vừa wait + switch
        waitAlertPresence(driver).accept();

        // chi switch và alert
        // code có wait sẽ ổn định hơn
//        driver.switchTo().alert().accept();
    }

    public void cancelToAlert(WebDriver driver) {
        waitAlertPresence(driver).dismiss();
//        driver.switchTo().alert().dismiss();
    }

    public void sendKeys(WebDriver driver, String keyToSend) {
        waitAlertPresence(driver).sendKeys(keyToSend);
    }

    public String getAlertText(WebDriver driver) {
        return waitAlertPresence(driver).getText();
    }

    public void sleepInSeconds(int timeIsSeconds) {
        try {
            Thread.sleep(timeIsSeconds * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void switchWindowById(WebDriver driver, String windowID) {
        Set<String> windows = driver.getWindowHandles();
        for (String windowHandle : windows) {
            if (!windowHandle.equals(windowID)) {
                driver.switchTo().window(windowHandle);
                break;
            }
        }
    }

    private void switchMultiWindowByTitle(WebDriver driver, String expectedTitle) {
        Set<String> windows = driver.getWindowHandles();
        for (String windowHandle : windows) {
            driver.switchTo().window(windowHandle);
            String currentTitle = driver.getTitle();
            if (expectedTitle.equals(currentTitle)) {
                sleepInSeconds(2);
                break;
            }
        }
    }

    private void closeAllExceptMain(WebDriver driver, String windowID) {
        Set<String> windows = driver.getWindowHandles();
        for (String windowHandle : windows) {
            if (!windowHandle.equals(windowID)) {
                driver.switchTo().window(windowHandle);
                driver.close();
            }
        }
        driver.switchTo().window(windowID);
    }
    private void switchMultiWindowByContain(WebDriver driver, String containTitle) {
        Set<String> windows = driver.getWindowHandles();
        for (String windowHandle : windows) {
            driver.switchTo().window(windowHandle);
            String currentTitle = driver.getTitle();
            assert currentTitle != null;
            if (containTitle.contains(currentTitle)) {
                sleepInSeconds(2);
                break;
            }
        }
    }
    //Ham Web Element

//    private WebElement getWebElement(WebDriver driver, String locator) {
//        return driver.findElement(getByXpath(locator));
//    }
//
//    private List<WebElement> getListWebElement(WebDriver driver, String locator) {
//        return driver.findElements(getByXpath(locator));
//    }

    // Thao tác với Element
    // PageFac sẽ define kiểu WebElement

    public void clickToElement(WebElement element) {
        element.click();
    }

    public void sendKeysToElement(WebElement element, String keyToSend) {
        element.clear();
        element.sendKeys(keyToSend);
    }

    public String getElementText(WebElement element) {
        return element.getText();
    }

    public String getElementDOMAttribute(WebElement element, String attributeName) {
        return element.getDomAttribute(attributeName);
    }

    public String getElementDOMProperty(WebElement element, String attributeName) {
        return element.getDomProperty(attributeName);
    }

    public boolean isElementDisplayed(WebElement element) {
//        waitElementVisible(driver, locator);
        return element.isDisplayed();
    }

    public boolean isElementSelected(WebElement element) {
//        waitElementSelected(driver, locator);
        return element.isSelected();
    }

//    WAIT
    public WebElement waitElementVisible(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIME_OUT)).until(ExpectedConditions.visibilityOf(element));
    }

    public List<WebElement> waitListElementVisible(WebDriver driver, List<WebElement> elements) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIME_OUT)).until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public boolean waitElementSelected(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIME_OUT)).until(ExpectedConditions.elementToBeSelected(element));
    }

    public WebElement waitElementClickable(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIME_OUT)).until(ExpectedConditions.elementToBeClickable(element));
    }

    public boolean waitElementInVisible(WebDriver driver, WebElement element) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIME_OUT)).until(ExpectedConditions.invisibilityOf(element));
    }

    public boolean waitListElementInVisible(WebDriver driver, List<WebElement> elements) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIME_OUT)).until(ExpectedConditions.invisibilityOfAllElements(elements));
    }

}
