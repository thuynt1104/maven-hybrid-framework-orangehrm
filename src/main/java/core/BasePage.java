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

public class BasePage {
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
    public static BasePage getInstance() {
        return new BasePage();
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

    private WebElement getWebElement(WebDriver driver, String locator) {
        return driver.findElement(getByXpath(locator));
    }

    private List<WebElement> getListWebElement(WebDriver driver, String locator) {
        return driver.findElements(getByXpath(locator));
    }

    private By getByXpath(String locator) {
        return By.xpath(locator);
    }

    public void clickToElement(WebDriver driver, String locator) {
        getWebElement(driver, locator).click();
    }

    public void sendKeysToElement(WebDriver driver, String locator, String keyToSend) {
        getWebElement(driver, locator).sendKeys(keyToSend);
    }

    public String getElementText(WebDriver driver, String locator) {
        return getWebElement(driver, locator).getText();
    }

    public void selectItemDropdown(WebDriver driver, String locator, String valueItem) {
        new Select(getWebElement(driver, locator)).selectByVisibleText(valueItem);
    }

    public String getSelectItemDropdown(WebDriver driver, String locator) {
        return new Select(getWebElement(driver, locator)).getFirstSelectedOption().getText();
    }

    public boolean isDropDownMultiple(WebDriver driver, String locator) {
        return new Select(getWebElement(driver,locator)).isMultiple();
    }

    private void selectItemInSelectableDropdown(WebDriver driver, String parentLocator, String childLocator, String textItem){
        clickToElement(driver, parentLocator);
        driver.findElement(By.cssSelector(parentLocator)).click();
        sleepInSeconds(1);        // Wait explicit
        new WebDriverWait(driver, Duration.ofSeconds(SHORT_TIME_OUT)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector(childLocator)));
        // Tìm và lấy ra all items và lưu vào 1 biến
        List<WebElement> allItems = getListWebElement(driver, childLocator);
        //Duyệt từng element và kiểm tra
        for (WebElement item : allItems) {
            if (item.getText().trim().equals(textItem)){
                item.click();
                sleepInSeconds(1);
                break;
            }
        }
    }

    public String getElementDOMAttribute(WebDriver driver, String locator, String attributeName) {
        return getWebElement(driver, locator).getDomAttribute(attributeName);
    }

    public String getElementDOMProperty(WebDriver driver, String locator, String attributeName) {
        return getWebElement(driver, locator).getDomProperty(attributeName);
    }

    public String getElementCSS(WebDriver driver, String locator, String propertyName) {
        return getWebElement(driver, locator).getCssValue(propertyName);
    }

    public String getHexaByRGBA(String rgbaValue) {
        return Color.fromString(rgbaValue).asHex().toUpperCase();
    }

    public int getListElementNumber(WebDriver driver, String locator) {
        return getListWebElement(driver,locator).size();
    }

    public void checkToCheckBox(WebDriver driver, String locator) {
        if (!isElementSelected(driver, locator)) {
            getWebElement(driver, locator).click();
        }
    }

    public void unCheckToCheckBox(WebDriver driver, String locator) {
        if (isElementSelected(driver, locator)) {
            getWebElement(driver, locator).click();
        }
    }

    public boolean isElementDisplayed(WebDriver driver, String locator) {
        waitElementVisible(driver, locator);
        return getWebElement(driver, locator).isDisplayed();
    }

    public boolean isElementSelected(WebDriver driver, String locator) {
        waitElementSelected(driver, locator);
        return getWebElement(driver, locator).isSelected();
    }

    public boolean isElementEnabled(WebDriver driver, String locator) {
        return getWebElement(driver, locator).isEnabled();
    }

    public void switchToFrame(WebDriver driver, String locator) {
        driver.switchTo().frame(getWebElement(driver, locator));
    }

    public void switchToContent(WebDriver driver, String locator) {
        driver.switchTo().defaultContent();
    }

    public void doubleClick(WebDriver driver, String locator) {
        new Actions(driver).doubleClick(getWebElement(driver,locator)).perform();
    }

    public void rightClick(WebDriver driver, String locator) {
        new Actions(driver).contextClick(getWebElement(driver,locator)).perform();
    }

    public void moveToClick(WebDriver driver, String locator) {
        new Actions(driver).moveToElement(getWebElement(driver,locator)).perform();
    }

    public void ragAndDropClick(WebDriver driver, String sourceLocator, String targetLocator) {
        new Actions(driver).dragAndDrop(getWebElement(driver, sourceLocator), getWebElement(driver, targetLocator)).perform();
    }

    public void sendKeyBoardToElement(WebDriver driver, String locator, String keyToSend) {
        new Actions(driver).sendKeys(getWebElement(driver, locator), keyToSend).perform();
    }

    public Object executeForBrowser(WebDriver driver,String javaScript) {
        return ((JavascriptExecutor) driver).executeScript(javaScript);
    }

    public String getElementTextByJS(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript ( "return arguments[0].textContent;", getWebElement(driver, locator));
    }

    public String getInnerText(WebDriver driver) {
        return (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText;");
    }

    public boolean isExpectedTextInInnerText( WebDriver driver,String textExpected) {
        String textActual = (String) ((JavascriptExecutor) driver).executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0];");
        return textActual.equals(textExpected);
    }

    public void scrollToBottomPage(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void navigateToUrlByJS(WebDriver driver, String url) {
        ((JavascriptExecutor) driver).executeScript("window.location = '" + url + "'");
        sleepInSeconds(3);
    }

    public void hightlightElement(WebDriver driver, String locator) {
        WebElement element = getWebElement(driver, locator);
        String originalStyle = getElementDOMAttribute(driver, locator, "style");
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, "border: 2px solid red; border-style: dashed;");
        sleepInSeconds(2);
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', arguments[1])", element, originalStyle);
    }

    public void clickToElementByJS(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", getWebElement(driver, locator));
        sleepInSeconds(3);
    }

    public void scrollToElementOnTop(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", getWebElement(driver, locator));
    }

    public void scrollToElementOnDown(WebDriver driver, String locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", getWebElement(driver, locator));
    }

    public void setAttributeInDOM(WebDriver driver, String locator, String attributeName, String attributeValue) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('" + attributeName + "', '" + attributeValue +"');", getWebElement(driver, locator));
    }

    public void removeAttributeInDOM(WebDriver driver, String locator, String attributeRemove) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", getWebElement(driver, locator));
    }

    public void sendkeyToElementByJS(WebDriver driver, String locator, String value) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('value', '" + value + "')", getWebElement(driver, locator));
    }

    public String getAttributeInDOM(WebDriver driver, String locator, String attributeName) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].getAttribute('" + attributeName + "');", getWebElement(driver, locator));
    }

    public String getElementValidationMessage(WebDriver driver, String locator) {
        return (String) ((JavascriptExecutor) driver).executeScript("return arguments[0].validationMessage;", getWebElement(driver, locator));
    }

    public boolean isImageLoaded(WebDriver driver, String locator) {
        return (boolean) ((JavascriptExecutor) driver).executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != 'undefined' && arguments[0].naturalWidth > 0", getWebElement(driver, locator));
    }

    public WebElement getElement(WebDriver driver, String locator) {
        return driver.findElement(By.xpath(locator));
    }

//    WAIT
    public WebElement waitElementVisible(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIME_OUT)).until(ExpectedConditions.visibilityOfElementLocated(getByXpath(locator)));
    }

    public List<WebElement> waitListElementVisible(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIME_OUT)).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getByXpath(locator)));
    }

    public boolean waitElementSelected(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIME_OUT)).until(ExpectedConditions.elementToBeSelected(getByXpath(locator)));
    }

    public WebElement waitElementClickable(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIME_OUT)).until(ExpectedConditions.elementToBeClickable(getByXpath(locator)));
    }

    public boolean waitElementInVisible(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIME_OUT)).until(ExpectedConditions.invisibilityOfElementLocated(getByXpath(locator)));
    }

    public boolean waitListElementInVisible(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIME_OUT)).until(ExpectedConditions.invisibilityOfAllElements(getListWebElement(driver, locator)));
    }

    public WebElement waitElementPresence(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIME_OUT)).until(ExpectedConditions.presenceOfElementLocated(getByXpath(locator)));
    }

    public List<WebElement> waitListElementPresence(WebDriver driver, String locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(LONG_TIME_OUT)).until(ExpectedConditions.presenceOfAllElementsLocatedBy(getByXpath(locator)));
    }

    public boolean isLoadingSpinnerDisappear(WebDriver driver) {
        return waitListElementInVisible(driver, BasePageUI.SPINNER_ICON);
    }


}
