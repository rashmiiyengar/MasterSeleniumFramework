package org.selenium.pom.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.selenium.pom.factory.DriveManager;
import org.selenium.pom.factory.DriverManager;

public final class ScreenshotUtils {

    private ScreenshotUtils(){

    }

    public static String getBase64Image(){

        return ((TakesScreenshot) DriveManager.getDriver()).getScreenshotAs(OutputType.BASE64);
    }
}
