package org.selenium.pom.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverManager implements DriverManager_OC {

    @Override
    public WebDriver  createDriver() {
        WebDriverManager.firefoxdriver().clearDriverCache();
        WebDriverManager.firefoxdriver().cachePath("Drivers").setup();
        WebDriver driver=  new FirefoxDriver();
        driver.manage().window().maximize();
        return driver;

    }

}