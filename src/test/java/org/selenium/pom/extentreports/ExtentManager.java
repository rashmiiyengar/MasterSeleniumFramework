package org.selenium.pom.extentreports;

import com.aventstack.extentreports.ExtentTest;

public class ExtentManager {

    private  ExtentManager(){
    }

    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    static ExtentTest getExtentTest() {//default -can be only accessed within package
        System.out.println("Inside getextenttest: " + extentTest);
        return extentTest.get();
    }

    static void setExtentTest(ExtentTest test) {
        System.out.println("Inside setExtentTest: test -> " + test);
        extentTest.set(test);
    }

    static  void unload(){
        extentTest.remove();
    }


}
