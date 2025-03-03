package org.selenium.pom.listeners;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.selenium.pom.extentreports.ExtentLogger;
import org.selenium.pom.extentreports.ExtentReport;
import org.selenium.pom.utils.ConfigLoader;
import org.selenium.pom.utils.EmailSendUtils;
import org.selenium.pom.utils.PropertyUtils;
import org.testng.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.Arrays;

import static org.selenium.pom.constants.FrameworkConstants.*;

public class ListenerClass implements ITestListener , ISuiteListener {


    static int count_passedTCs;
    static int count_skippedTCs;
    static int count_failedTCs;
    static int count_totalTCs;


    public void onTestStart(ITestResult result) {
        // Code to execute before each test starts
        count_totalTCs++;
        String testDescription = result.getMethod().getDescription();
        ExtentReport.createReportTest(result.getMethod().getMethodName());
//        if (testDescription != null) {
//            ExtentReport.createReportTest(testDescription);
//        } else {
//            ExtentReport.createReportTest(result.getMethod().getMethodName());
//        }
        System.out.println("Extent report created " +result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        // Code to execute when a test passes
        count_passedTCs = count_passedTCs + 1;
        String log_text = "<b>" + result.getMethod().getMethodName() + " is passed. </b>" + " " + ICON_SMILEY_PASS;
        Markup markupMessage = MarkupHelper.createLabel(log_text, ExtentColor.GREEN);
        ExtentLogger.pass(markupMessage,true);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        // Code to execute when a test fails
        // attach screenshot when failed
        count_failedTCs = count_failedTCs + 1;
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
        // Code to execute when a test is
        count_skippedTCs = count_skippedTCs + 1;
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
        System.out.println("Suite started: " + suite.getName());
        ExtentReport.initReports();
    }

    @Override
    public void onFinish(ISuite suite) {
        // Code to execute after the suite finishes
        try {
            ExtentReport.flushReports();
            EmailSendUtils.sendEmail(count_totalTCs, count_passedTCs, count_failedTCs, count_skippedTCs);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
