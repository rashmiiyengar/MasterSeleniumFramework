package org.selenium.pom.base;

import io.restassured.http.Cookies;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.factory.DriverManager;
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
    public void startDriver(String browser){
       setDriver(new DriverManager().initializeDriver(browser));
       System.out.println("CURRENT THREAD : " +Thread.currentThread().getName()+ "DRIVER " +driver);
    }

    @AfterMethod
    public void quitDriver(){
    getDriver().quit();
    }


    public void injectCookiesToBrowser(Cookies cookies){
        List<Cookie> cookiesList = new CookieUtils().convertRestAssuredCookiesToSeleniumCookies(cookies);

        for( Cookie cookie : cookiesList){

            getDriver().manage().addCookie(cookie);
        }

    }
}
