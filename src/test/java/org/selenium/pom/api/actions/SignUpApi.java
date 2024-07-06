package org.selenium.pom.api.actions;

import io.restassured.http.Cookies;
import org.selenium.pom.utils.ConfigLoader;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class SignUpApi {

    private Cookies cookies;

    public Cookies getCookies(){
        return  cookies;
    }

    private void getAccountCall(){
        Response response =     given().
                    baseUri(ConfigLoader.getInstanceMethod().
                            getBaseUrl()).
                            cookies(getCookies()).

                    when().
                        get("/account").

                    then().
                        extract().
                        response();
        if(response.getStatusCode()!= 200){
            throw new RuntimeException("Failed to fetch the account");
        }
    }

}
