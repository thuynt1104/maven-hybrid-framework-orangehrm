package javaSDET;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static java.io.File.separator;

public class Topic_08 {
    WebDriver driver;

    //non-static
    String names = "abc";

    //phạm vi biến static là cho cả system , các class khác sử dụng
    static String name =  "automation";

    // Bat buoc viet hoa, là 1 hằng số, không được phép thay đổi
    static final String AGE = "32";

    @Test
    public void TC_01() throws InterruptedException {
        Topic_08 tp = new Topic_08();
        // bien non-static phải khởi tạo lại class để sử dụng, phạm vi đối tượng
        tp.names = names;


        //biến static không cần khởi tạo class để sử dụng, phạm vi class
        Topic_08.name = name;
        String osName = System.getProperty("os.name");
        //Condition Statement
        //if-else
        if (osName.toLowerCase().contains("windows")) {
            System.out.println("Windows");
        } else if (osName.toLowerCase().contains("linux")) {
            System.out.println("Linux");
        }
        String browserName = "Chrome";
        //switch-case
        switch (browserName){
            case "chrome":
                driver = new ChromeDriver();
                break;
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "Edge":
                driver = new EdgeDriver();
                break;
            default:
                throw new RuntimeException("Unsupported browser: " + browserName);
        }
        //Loop Statement

        //classic for
        //for
        int studentNumber = 10;
        for (int i = 0; i < studentNumber; i++) {
            System.out.println("Student number " + i);
        }
        //for + if
        for (int i = 0; i < studentNumber; i++) {
            if (i==5){
                System.out.println("Student number " + i);
            }
        }
        List<String> listStudent = new ArrayList<>();
        //for+each
        for (String std: listStudent){
            System.out.println(std);
        }
        //bieu thuc vong lap
        //while - kiểm tra điều kiện trước rồi mới thực thi
        int x=0;
        while (x < studentNumber) {
            System.out.println("Student number " + x);
            x++;
        }
        //do-while : thực thi trước rồi mới kiểm tra điều kiện - ít khi sử dụng
        int z = 11;
        do {
            System.out.println("Student number " + z);
            z++;
        }while (z < studentNumber);

        //try - catch nếu như element không đc tìm thấy thì cũng không lỗi
        try{
            //happy case
            driver.findElement(By.cssSelector("")).isDisplayed();
        } catch (NoSuchElementException e) {
            System.out.println(e.getMessage());
        } finally {
            //chay xong thi dong - nếu như có thì bắt buộc chạy qua
            driver.quit();
        }

        Thread.sleep(5000);

    }
}
