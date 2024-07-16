package org.selenium.pom.listeners;

import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.selenium.pom.extentreports.ExtentLogger;
import org.selenium.pom.extentreports.ExtentReport;
import org.selenium.pom.utils.ConfigLoader;
import org.selenium.pom.utils.PropertyUtils;
import org.testng.*;

import java.io.IOException;
import java.util.Arrays;

import static org.selenium.pom.constants.FrameworkConstants.*;

public class ListenerClass implements ITestListener , ISuiteListener {

    public void onTestStart(ITestResult result) {
        // Code to execute before each test starts
            ExtentReport.createTest(result.getMethod().getDescription());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Code to execute when a test passes
        String log_text = "<b>" + result.getMethod().getMethodName() + " is passed. </b>" + " " + ICON_SMILEY_PASS;
        Markup markupMessage = MarkupHelper.createLabel(log_text, ExtentColor.GREEN);
        ExtentLogger.pass(markupMessage,true);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Code to execute when a test fails
        // attach screenshot when failed

        ExtentLogger.fail(ICON_BUG+ " " +"<b> <i>" +result.getThrowable().toString()+ "</i></b>");
        String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
        String message = "<details><summary><b><font color=red> Exception occured, click to see details: "
                + ICON_SMILEY_FAIL + " </font></b>" + "</summary>" + exceptionMessage.replaceAll(",", "<br>")
                + "</details> \n";
        ExtentLogger.fail(message);

        String log_text= "<b>" +result.getMethod().getMethodName()+ "is failed. </b>"+" "+ICON_SMILEY_FAIL;
        Markup markup_message = MarkupHelper.createLabel(log_text, ExtentColor.RED);
        ExtentLogger.fail(markup_message, true);

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // Code to execute when a test is skipped
        ExtentLogger.skip(result.getMethod().getMethodName()+ "is skipped. </b>" + " "+ICON_SMILEY_SKIP);
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
