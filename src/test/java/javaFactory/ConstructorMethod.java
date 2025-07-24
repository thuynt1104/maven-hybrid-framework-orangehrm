package javaFactory;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ConstructorMethod {
    public static void main(String[] args) {
        FirefoxDriver ffdriver;
        //1
        ffdriver = new FirefoxDriver();
        //2
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        ffdriver = new FirefoxDriver(options);
        //3


        WebDriverWait explicitWait;
        explicitWait = new WebDriverWait(ffdriver, Duration.ofSeconds(10));
        explicitWait = new WebDriverWait(ffdriver, Duration.ofSeconds(10), Duration.ofSeconds(1));

    }
}
