package org.selenium.pom.base;

import org.openqa.selenium.WebDriver;
import org.selenium.pom.factory.DriverManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {
    private ThreadLocal<WebDriver>  driver= new ThreadLocal<>();

    public void setDriver(WebDriver driver){
      this.driver.set(driver);
    }

    protected WebDriver getDriver(){
        return  this.driver.get();
    }
    @Parameters("browser")

    @BeforeMethod
    public void startDriver(String browser){
       setDriver(new DriverManager().initializeDriver(browser));
       System.out.println("CURRENT THREAD : " +Thread.currentThread().getName()+ "DRIVER " +driver);
    }

    @AfterMethod
    public void quitDriver(){
    getDriver().quit();
    }
}
