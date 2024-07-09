package org.selenium.pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.selenium.pom.constants.DriverType;

public class DriverManagerOriginal {


    public WebDriver initializeDriver(String browser) {
        WebDriver driver;
        //String localBrowser;
         browser = System.getProperty("browser",browser);

        driver = switch (DriverType.valueOf(browser)) {
            case CHROME -> {
                WebDriverManager.chromedriver().cachePath("Drivers").setup();
                yield new ChromeDriver();
            }
            case FIREFOX -> {
                WebDriverManager.firefoxdriver().cachePath("Drivers").setup();
                yield new FirefoxDriver();
            }
            default -> throw new IllegalStateException("Invalid browser name : " + browser);
        };

        //WebDriver driver = new FirefoxDriver();
        driver.manage().window().maximize();
       // driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        return  driver;
    }


}
