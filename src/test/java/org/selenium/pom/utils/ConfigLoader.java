package org.selenium.pom.utils;

import java.util.Properties;

public class ConfigLoader {

private final Properties properties;
private static ConfigLoader configLoader;

private ConfigLoader(){

    properties = PropertyUtils.propertyLoader("src/test/resources/config.properties");

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
