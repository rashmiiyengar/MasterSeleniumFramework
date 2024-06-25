package org.selenium.pom.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.selenium.pom.objects.BillingAddress;

import java.io.IOException;
import java.io.InputStream;

public class JacksonUtil {


//    public static BillingAddress deserializeJson(InputStream is, BillingAddress billingAddress) throws IOException {
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        return objectMapper.readValue(is,billingAddress.getClass());
//
//
//    }
    //The below is the generic way of writing the above util

public static <T> T deserializeJson(String fileName, Class<T> T) throws IOException {
    InputStream is= JacksonUtil.class.getClassLoader().getResourceAsStream(fileName);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(is,T);


    }
}
