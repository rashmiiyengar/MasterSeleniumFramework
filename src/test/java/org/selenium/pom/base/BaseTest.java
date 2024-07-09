package org.selenium.pom.base;

import io.restassured.http.Cookies;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.constants.DriverType;
import org.selenium.pom.factory.DriverManager;
import org.selenium.pom.factory.DriverManagerFactory;
import org.selenium.pom.factory.DriverManagerOriginal;
import org.selenium.pom.utils.CookieUtils;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

import java.util.List;

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
