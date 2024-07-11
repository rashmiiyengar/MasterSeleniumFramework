package org.selenium.pom.utils;

import org.selenium.pom.constants.EnvType;

import java.util.Properties;

public class ConfigLoader {

    private static final String ENV = "env";
    private static final String CONFIG_PROPERTIES = "_config.properties";
    private static final String OVERRIDE_REPORTS = "override_reports";

    /* Default config file is stg_config.properties */
    private static final String STG_CONFIG_PROPERTIES = "stg" + CONFIG_PROPERTIES;
    private static final String PROD_CONFIG_PROPERTIES = "prod" + CONFIG_PROPERTIES;
    private static final String RESOURCES_PATH = System.getProperty("user.dir") + "/src/test/resources/";
    private static final String BASE_URL = "baseUrl";

    private static final String SKIPPED_STEPS_SCREENSHOT = "skipped_steps_screenshot";
    private static final String PASSED_STEPS_SCREENSHOT = "passed_steps_screenshot";
    private static final String FAILED_STEPS_SCREENSHOT = "failed_steps_screenshot";


    private final Properties properties;
    private static ConfigLoader configLoader;

    private ConfigLoader(){
    String env = System.getProperty("env", String.valueOf(EnvType.STAGE));
    switch (EnvType.valueOf(env)){

        case STAGE:
            properties = getConfigPropertyFile(STG_CONFIG_PROPERTIES);
            break;
        case PROD:
            properties = getConfigPropertyFile(PROD_CONFIG_PROPERTIES);
            break;
        default: throw
                new IllegalStateException("invalid env tyoe" +env);
        }

    }

    private Properties getConfigPropertyFile(String configFile){
        return  PropertyUtils.propertyLoader(RESOURCES_PATH +configFile);
    }


    private String getPropertyValue(String propertyKey){

      String prop=  properties.getProperty(propertyKey);
      if(prop!=null){
          return prop.trim();
      }else{
          throw new RuntimeException("Property" +propertyKey+ "is not specified in the config.properties file");

      }
    }

    public static ConfigLoader getInstanceMethod(){
    if(configLoader == null){
        configLoader = new ConfigLoader();
    }
    return  configLoader;
    }

    public String getBaseUrl(){
       return getPropertyValue(BASE_URL);

    }

    public String getFailedStepsScreenshot() {
        return getPropertyValue(FAILED_STEPS_SCREENSHOT);
    }

    public String getPassedStepsScreenshot() {
        return getPropertyValue(PASSED_STEPS_SCREENSHOT);
    }

    public String getSkippedStepsScreenshot() {
        return getPropertyValue(SKIPPED_STEPS_SCREENSHOT);
    }

    public String getOverrideReports() {
        return getPropertyValue(OVERRIDE_REPORTS);
    }
}
