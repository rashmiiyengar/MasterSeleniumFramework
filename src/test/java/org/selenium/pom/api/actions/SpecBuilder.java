package org.selenium.pom.api.actions;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.selenium.pom.utils.ConfigLoader;

public class SpecBuilder {


    public static RequestSpecification getRequestSpec(){
        //RequestSpecBuilder - is a builder class provided by RestAssured to construct a RequestSpecification
        //object. Using this builder pattern makes it easier to configure various aspects of the request in a readable and maintainable way.
        //The build() method is used in the context of the RequestSpecBuilder class in RestAssured to finalize the
        // creation of a RequestSpecification object. This method is essential as it compiles all the configurations
        // you've set in the RequestSpecBuilder into a single, immutable RequestSpecification object.


        return new RequestSpecBuilder().setBaseUri(ConfigLoader.getInstanceMethod().getBaseUrl()).
                log(LogDetail.ALL).
                build();

    }

    public static ResponseSpecification getResponseSpec(){
        return new ResponseSpecBuilder().
                log(LogDetail.ALL).
                build();
    }
}
