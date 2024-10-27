package org.selenium.pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverManager implements DriverManager_OC {
    @Override
    public WebDriver createDriver() {
        //This clears any cached drivers, ensuring the latest driver is downloaded if needed.
        WebDriverManager.chromedriver().clearDriverCache();
        //Sets the cache path to Drivers and downloads the required ChromeDriver binary if not already available.
        WebDriverManager.chromedriver().cachePath("Drivers").setup();
        WebDriver  driver=  new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }


}
