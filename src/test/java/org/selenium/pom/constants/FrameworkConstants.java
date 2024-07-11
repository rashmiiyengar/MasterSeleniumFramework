package org.selenium.pom.constants;

public final class FrameworkConstants {

    private FrameworkConstants(){}


    public static final String ICON_SOCIAL_LINKEDIN_URL = "https://www.linkedin.com/in/rashmi-soundar-22a62276/";
    public static final String ICON_SOCIAL_GITHUB_URL = "https://github.com/rashmiiyengar";
    public static final String ICON_SOCIAL_LINKEDIN = "<a href='" + ICON_SOCIAL_LINKEDIN_URL
            + "'><i class='fa fa-linkedin-square' style='font-size:24px'></i></a>";
    public static final String ICON_SOCIAL_GITHUB = "<a href='" + ICON_SOCIAL_GITHUB_URL
            + "'><i class='fa fa-github-square' style='font-size:24px'></i></a>";


    public static final String EXTENTREPORTPATH = System.getProperty("user.dir")+"/extent-test-output/index.html";

    public static String getExtentreportpath(){

        return EXTENTREPORTPATH;
    }


}
