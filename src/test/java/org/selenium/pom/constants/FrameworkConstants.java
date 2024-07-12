package org.selenium.pom.constants;

import org.selenium.pom.utils.ConfigLoader;
import org.selenium.pom.utils.PropertyUtils;

public final class FrameworkConstants {

    private FrameworkConstants(){}

    private static final String EXCEL_PATH = System.getProperty("user.dir")+"/src/test/resources/excel/testdataupdated.xlsx";
    public static final String ICON_SOCIAL_LINKEDIN_URL = "https://www.linkedin.com/in/rashmi-soundar-22a62276/";
    public static final String ICON_SOCIAL_GITHUB_URL = "https://github.com/rashmiiyengar";
    public static final String ICON_SOCIAL_LINKEDIN = "<a href='" + ICON_SOCIAL_LINKEDIN_URL
            + "'><i class='fa fa-linkedin-square' style='font-size:24px'></i></a>";
    public static final String ICON_SOCIAL_GITHUB = "<a href='" + ICON_SOCIAL_GITHUB_URL
            + "'><i class='fa fa-github-square' style='font-size:24px'></i></a>";

    public static String extentReportFilePath = "";
    public static final String EXTENTREPORTPATH = System.getProperty("user.dir")+"/extent-test-output";

    public static String getExtentreportpath(){
            if(extentReportFilePath.isEmpty()){

                extentReportFilePath= createReportPath();
            }
        return extentReportFilePath;
    }

    private static String createReportPath(){

        if(ConfigLoader.getInstanceMethod().getOverrideReports().equalsIgnoreCase("no")){
            return  EXTENTREPORTPATH+System.currentTimeMillis()+"/AutomationReport.html";
        }
        else{
            return EXTENTREPORTPATH+"/AutomationReport.html";
        }
    }

    public static String getExcelPath() {
        return EXCEL_PATH;
    }


}
