package org.selenium.pom.factory;

import org.selenium.pom.constants.DriverType;

public class DriverManagerFactory {

    public static DriverManager_OC getManager(DriverType driverType){

        switch (driverType){

            case CHROME -> {
                return new ChromeDriverManager();
            }
            case FIREFOX -> {
                return  new FirefoxDriverManager();
            }

            default -> throw new IllegalStateException("Invalid driver : " +driverType);


        }
    }
}
