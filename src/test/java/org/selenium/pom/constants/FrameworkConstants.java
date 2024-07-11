package org.selenium.pom.constants;

public final class FrameworkConstants {

    private FrameworkConstants(){}

    public static final String EXTENTREPORTPATH = System.getProperty("user.dir")+"/extent-test-output/index.html";

    public static String getExtentreportpath(){

        return EXTENTREPORTPATH;
    }


}
