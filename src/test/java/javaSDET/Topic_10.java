
package javaSDET;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Topic_10 extends Topic_09{
    //Biến phạm vi toàn cục Class
    String address;
    public Topic_10(String name, String address) {//Phạm vi cục bộ - hàm
        super(name);
        //this là dùng biến phạm vi toàn cục
        this.address = address;
    }

    //khi chạy đa luồng và gọi tới hàm này bắt buộc phải chạy theo thứ tự
    public synchronized WebDriver getWebDriver() {
        WebDriver driver = null;
        if(driver instanceof WebDriver){
            driver = new ChromeDriver();
        }
        return new ChromeDriver();
    }
}
