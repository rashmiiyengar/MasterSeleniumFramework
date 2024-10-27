package org.selenium.pom.extentreports;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import org.selenium.pom.utils.ConfigLoader;
import org.selenium.pom.utils.ScreenshotUtils;

import static org.apache.commons.lang3.BooleanUtils.YES;
import com.aventstack.extentreports.markuputils.Markup;
public final class ExtentLogger {

    private ExtentLogger(){}

    public  static void pass(String message){
        ExtentTest extentTest = ExtentManager.getExtentTest();
        if (extentTest != null) {
            extentTest.pass(message);
        } else {
            throw new IllegalStateException("ExtentTest is not initialized. Call createTest before logging.");
        }
        //ExtentManager.getExtentTest().pass(message);
    }

    public static void pass(Markup message) {

        ExtentTest extentTest = ExtentManager.getExtentTest();
        if (extentTest != null) {
            extentTest.pass(message);
        } else {
            throw new IllegalStateException("ExtentTest is not initialized. Call createTest before logging.");
        }
       // ExtentManager.getExtentTest().pass(message);
    }

    public static void fail(String message){

        ExtentTest extentTest = ExtentManager.getExtentTest();
        if (extentTest != null) {
            extentTest.fail(message);
        } else {
            throw new IllegalStateException("ExtentTest is not initialized. Call createTest before logging.");
        }

       // ExtentManager.getExtentTest().fail(message);

    }

    public static void fail(Markup message) {
        ExtentTest extentTest = ExtentManager.getExtentTest();
        if (extentTest != null) {
            extentTest.fail(message);
        } else {
            throw new IllegalStateException("ExtentTest is not initialized. Call createTest before logging.");
        }
        //ExtentManager.getExtentTest().fail(message);
    }
    public static void skip(String message){
        ExtentManager.getExtentTest().skip(message);
    }

    public static void pass(String message, boolean isScreeshotNeeded) {
        if (ConfigLoader.getInstanceMethod().getPassedStepsScreenshot().equalsIgnoreCase(YES)
                && isScreeshotNeeded) {

            ExtentTest extentTest = ExtentManager.getExtentTest();
            if (extentTest != null) {
                ExtentManager.getExtentTest().pass(message,
                        MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
            } else {
                throw new IllegalStateException("ExtentTest is not initialized. Call createTest before logging.");
            }


        } else {
            pass(message);
        }
    }

    public static void pass(Markup message, boolean isScreeshotNeeded) {
        if (ConfigLoader.getInstanceMethod().getPassedStepsScreenshot().equalsIgnoreCase(YES) && isScreeshotNeeded) {


            ExtentManager.getExtentTest().pass(
                    MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
            ExtentManager.getExtentTest().pass(message);
        } else {
            pass(message);
        }
    }

    public static void fail(String message, boolean isScreeshotNeeded) {
        if (ConfigLoader.getInstanceMethod().getFailedStepsScreenshot().equalsIgnoreCase(YES)
                && isScreeshotNeeded) {
            ExtentManager.getExtentTest().fail(message,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
        } else {
            fail(message);
        }
    }


    public static void fail(Markup message, boolean isScreeshotNeeded) {
        if (ConfigLoader.getInstanceMethod().getFailedStepsScreenshot().equalsIgnoreCase(YES) && isScreeshotNeeded) {

            ExtentTest extentTest = ExtentManager.getExtentTest();
            if (extentTest != null) {
                ExtentManager.getExtentTest().fail(
                        MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
                ExtentManager.getExtentTest().fail(message);
            } else {
                throw new IllegalStateException("ExtentTest is not initialized. Call createTest before logging.");
            }


        } else {
            fail(message);
        }
    }

}
