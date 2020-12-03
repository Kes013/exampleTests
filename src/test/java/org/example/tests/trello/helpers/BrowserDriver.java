package org.example.tests.trello.helpers;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BrowserDriver {

    public static WebDriver driver;
    public static WebDriverWait waitVar;

    public static WebDriver chrome() {
        System.setProperty("webdriver.chrome.driver", "webdriver/chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        driver= new ChromeDriver(chromeOptions);
        waitVar = new WebDriverWait(driver, 10);
        return driver;
    }

    public static WebDriver firefox() {
        System.setProperty("webdriver.gecko.driver", "webdriver/geckodriver.exe");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addArguments("--start-maximized");
        driver= new FirefoxDriver(firefoxOptions);
        waitVar = new WebDriverWait(driver, 10);
        return driver;
    }
}
