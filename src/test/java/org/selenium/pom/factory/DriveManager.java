package org.selenium.pom.factory;

import org.openqa.selenium.WebDriver;

public final class DriveManager {

    private DriveManager() {
    }

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();

    public static WebDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(WebDriver driverref) {
        driver.set(driverref);
    }

    public static void unload() {
        driver.remove();
    }

}
