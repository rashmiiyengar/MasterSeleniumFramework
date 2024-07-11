package org.selenium.pom.extentreports;

import com.aventstack.extentreports.ExtentTest;

public class ExtentManager {

    private  ExtentManager(){
    }

    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    static ExtentTest getExtentTest() {//default -can be only accessed within package
        return extentTest.get();
    }

    static void setExtentTest(ExtentTest test) {
       extentTest.set(test);
    }

    static  void unload(){
        extentTest.remove();
    }


}
