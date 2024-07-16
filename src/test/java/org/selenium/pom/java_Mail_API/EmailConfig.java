package org.selenium.pom.java_Mail_API;

import io.github.cdimascio.dotenv.Dotenv;
import org.selenium.pom.constants.FrameworkConstants;

public class EmailConfig {

    public static final String SERVER = "smtp.gmail.com";
    public static final String PORT = "587";

    public static final String FROM = "testtmail95@gmail.com";
    public static final String PASSWORD = Dotenv.load().get("EMAIL_PASSWORD");;

    /* "**********@gmail.com", */
    public static final String[] TO = {"chulavista225@gmail.com"};
    public static final String SUBJECT = FrameworkConstants.getProjectName();

}
