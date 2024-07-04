package org.selenium.pom.utils;

import org.selenium.pom.constants.EnvType;

import java.util.Properties;

public class ConfigLoader {

private final Properties properties;
private static ConfigLoader configLoader;

private ConfigLoader(){
    String env = System.getProperty("env", String.valueOf(EnvType.STAGE));
    switch (EnvType.valueOf(env)){

        case STAGE:
            properties = PropertyUtils.propertyLoader("src/test/resources/stg_config.properties");
            break;
        case PROD:
            properties = PropertyUtils.propertyLoader("src/test/resources/prod_config.properties");
            break;
        default: throw
                new IllegalStateException("invalid env tyoe" +env);
    }

}

public static ConfigLoader getInstanceMethod(){
if(configLoader == null){
    configLoader = new ConfigLoader();
}
return  configLoader;
}

public String getBaseUrl(){
    String prop = properties.getProperty("baseUrl");
    if(prop != null)  return  prop;
    else throw new RuntimeException("Property baseurl is not specified in the config properties file ");

}
}
