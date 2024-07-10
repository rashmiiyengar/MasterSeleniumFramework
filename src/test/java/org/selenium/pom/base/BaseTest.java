package org.selenium.pom.base;

import io.restassured.http.Cookies;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.constants.DriverType;
import org.selenium.pom.extentreports.ExtentReport;
import org.selenium.pom.factory.DriverManager;
import org.selenium.pom.factory.DriverManagerFactory;
import org.selenium.pom.factory.DriverManagerOriginal;
import org.selenium.pom.utils.CookieUtils;
import org.testng.annotations.*;

import java.io.IOException;
import java.util.List;

public class BaseTest {
    private ThreadLocal<WebDriver>  driver= new ThreadLocal<>();

    public void setDriver(WebDriver driver){
        this.driver.set(driver);
    }

    protected WebDriver getDriver(){
        return  this.driver.get();
    }

    @BeforeSuite
    public void beforeSuiteMethod(){
        ExtentReport.initReports();
    }

    @AfterSuite
    public void afterSuiteMethod() throws IOException {
        ExtentReport.flushReports();
    }

    @Parameters("browser")
    @BeforeMethod
    public synchronized void startDriver(String browser){
      // setDriver(new DriverManagerOriginal().initializeDriver(browser));

        setDriver(DriverManagerFactory.getManager(DriverType.valueOf(browser)).createDriver());
        System.out.println("CURRENT THREAD : " +Thread.currentThread().getName()+ "DRIVER " +driver);
    }

    @AfterMethod
    public synchronized void quitDriver(){

        getDriver().quit();

    }

    public void injectCookiesToBrowser(Cookies cookies){
        List<Cookie> cookiesList = new CookieUtils().convertRestAssuredCookiesToSeleniumCookies(cookies);

        for( Cookie cookie : cookiesList){

            getDriver().manage().addCookie(cookie);
        }

    }
}
