package org.selenium.pom.extentreports;

import com.aventstack.extentreports.MediaEntityBuilder;
import org.openqa.selenium.TakesScreenshot;
import org.selenium.pom.factory.DriverManager;
import org.selenium.pom.factory.DriverManagerFactory;
import org.selenium.pom.utils.ConfigLoader;
import org.selenium.pom.utils.ScreenshotUtils;

import static org.apache.commons.lang3.BooleanUtils.YES;

public final class ExtentLogger {

    private ExtentLogger(){}

    public  static void pass(String message){
        ExtentManager.getExtentTest().pass(message);
    }

    public static void fail(String message){

        ExtentManager.getExtentTest().fail(message);

    }

    public static void skip(String message){
        ExtentManager.getExtentTest().skip(message);
    }
    public static void pass(String message, boolean isScreeshotNeeded) {
        if (ConfigLoader.getInstanceMethod().getPassedStepsScreenshot().equalsIgnoreCase(YES)
                && isScreeshotNeeded) {
            ExtentManager.getExtentTest().pass(message,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
        } else {
            pass(message);
        }
    }

    public static void fail(String message, boolean isScreeshotNeeded) {
        // if
        // (PropertyUtils.get(ConfigProperties.FAILED_STEPS_SCREENSHOT).equalsIgnoreCase("yes")
        // && isScreeshotNeeded) {
        if (ConfigLoader.getInstanceMethod().getFailedStepsScreenshot().equalsIgnoreCase(YES)
                && isScreeshotNeeded) {
            ExtentManager.getExtentTest().fail(message,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
        } else {
            fail(message);
        }
    }


}
