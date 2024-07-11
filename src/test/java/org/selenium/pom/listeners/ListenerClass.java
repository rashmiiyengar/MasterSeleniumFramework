package org.selenium.pom.listeners;

import org.selenium.pom.extentreports.ExtentLogger;
import org.selenium.pom.extentreports.ExtentReport;
import org.selenium.pom.utils.ConfigLoader;
import org.selenium.pom.utils.PropertyUtils;
import org.testng.*;

import java.io.IOException;
import java.util.Arrays;

public class ListenerClass implements ITestListener , ISuiteListener {

    public void onTestStart(ITestResult result) {
        // Code to execute before each test starts
            ExtentReport.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Code to execute when a test passes
        ExtentLogger.pass(result.getMethod().getMethodName()+ "is passed. ");
        ExtentLogger.pass(result.getMethod().getMethodName()+ "is passed",true);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Code to execute when a test fails
        //attach screenshot when failed
        ExtentLogger.fail(result.getMethod().getMethodName()+ "is failed",true);
        ExtentLogger.fail(result.getThrowable().toString());
        ExtentLogger.fail(Arrays.toString(result.getThrowable().getStackTrace()));
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Code to execute when a test is skipped
        ExtentLogger.skip(result.getMethod().getMethodName()+ "is skipped. ");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Code to execute when a test fails but is within success percentage
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        // Code to execute when a test fails due to timeout
    }

    @Override
    public void onStart(ITestContext context) {
        // Code to execute before any test method is run
    }

    @Override
    public void onFinish(ITestContext context) {
        // Code to execute after all test methods have run
    }

    // ISuiteListener methods
    @Override
    public void onStart(ISuite suite) {
        // Code to execute before the suite starts
        ExtentReport.initReports();
    }

    @Override
    public void onFinish(ISuite suite) {
        // Code to execute after the suite finishes
        try {
            ExtentReport.flushReports();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
