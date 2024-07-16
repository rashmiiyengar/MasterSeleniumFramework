package org.selenium.pom.base;

import io.restassured.http.Cookies;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.constants.DriverType;
import org.selenium.pom.factory.DriverManager;
import org.selenium.pom.factory.DriverManagerFactory;
import org.selenium.pom.utils.CookieUtils;
import org.testng.annotations.*;

import java.util.List;

import static org.selenium.pom.factory.DriverManager.unload;

public class BaseTest {
    //private ThreadLocal<WebDriver>  driver= new ThreadLocal<>();

    protected WebDriver getDriver() {
        return DriverManager.getDriver();
    }

    private void setDriver(WebDriver driver) {
        // this.driver.set(driver);
        DriverManager.setDriver(driver);
    }

    @Parameters("browser")
    @BeforeMethod
    public synchronized void startDriver(String browser){
      // setDriver(new DriverManagerOriginal().initializeDriver(browser));
        synchronized (this){
            setDriver(DriverManagerFactory.getManager(DriverType.valueOf(browser)).createDriver());
            System.out.println("CURRENT THREAD : " +Thread.currentThread().getName()+ "DRIVER " +getDriver());
        }
    }

    @AfterMethod
    public void quitDriver(){
        synchronized (this){
            if(getDriver()!=null){
                getDriver().quit();
            }

            unload();
        }

    }

    public void injectCookiesToBrowser(Cookies cookies){
        List<Cookie> cookiesList = new CookieUtils().convertRestAssuredCookiesToSeleniumCookies(cookies);

        for( Cookie cookie : cookiesList){

            getDriver().manage().addCookie(cookie);
        }

    }
}
