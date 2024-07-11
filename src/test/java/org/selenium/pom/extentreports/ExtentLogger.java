package org.selenium.pom.extentreports;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.TakesScreenshot;
import org.selenium.pom.factory.DriverManager;
import org.selenium.pom.factory.DriverManagerFactory;
import org.selenium.pom.utils.ConfigLoader;
import org.selenium.pom.utils.ScreenshotUtils;

public final class ExtentLogger {

    private ExtentLogger(){}

    public  static void pass(String message){
        ExtentManager.getExtentTest().pass(message);
    }

    public static void fail(String message){

        if(ConfigLoader.getInstanceMethod().getFailedStepsScreenshot().equalsIgnoreCase("yes")){
            ExtentManager.getExtentTest().fail(message, MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
        }
        else {
            ExtentManager.getExtentTest().fail(message);
        }

    }

    public static void skip(String message){
        ExtentManager.getExtentTest().skip(message);
    }


}
