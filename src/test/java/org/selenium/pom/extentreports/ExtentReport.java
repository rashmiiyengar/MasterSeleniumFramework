package org.selenium.pom.extentreports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public final class ExtentReport {

    private ExtentReport(){}
    private static ExtentReports extentReport;
    public static ExtentTest test;

    public static void initReports(){
        if(Objects.isNull(extentReport)) {
            extentReport = new ExtentReports();
            ExtentSparkReporter spark = new ExtentSparkReporter("index.html");
            extentReport.attachReporter(spark);
            spark.config().setTheme(Theme.DARK);
            spark.config().setDocumentTitle("Test Results");
            spark.config().setReportName("Master Selenium Framework Report");
        }
    }

    public  static  void flushReports() throws IOException {

        if(Objects.nonNull(extentReport)){
            extentReport.flush();
            Desktop.getDesktop().browse(new File("index.html").toURI());
        }
    }

    public static void createTest(String testcaseName){
      test=  extentReport.createTest(testcaseName);
    }


}
