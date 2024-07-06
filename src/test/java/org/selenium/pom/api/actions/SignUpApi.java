package org.selenium.pom.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.selenium.pom.objects.User;
import org.selenium.pom.utils.ConfigLoader;
import io.restassured.response.Response;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class SignUpApi {

    private Cookies cookies;

    public Cookies getCookies(){
        return  cookies;
    }

    //Parse using Groovy
    private String fetchRegisterNonceValue(){
        Response response = getAccountCall();
        return response.htmlPath().getString("**.findAll {it.@name == 'woocommerce-register-nonce' }.@value");
    }

    private String fetchRegisterNonceValueIUsingJSoup(){
        Response response = getAccountCall();
        Document doc = Jsoup.parse(response.body().prettyPrint());

        Element ele = doc.selectFirst("#woocommerce-register-nonce");
        return ele.attr("value");

    }

    private Response getAccountCall(){
        Cookies cookies= new Cookies();
        Response response =     given().
                    baseUri(ConfigLoader.getInstanceMethod().
                            getBaseUrl()).
                            cookies(cookies).
                            log().
                            all(). //to log the request

                    when().
                        get("/account").

                    then().
                        log().
                        all().
                        extract().
                        response();

        if(response.getStatusCode()!= 200){
            throw new RuntimeException("Failed to fetch the account, HTTP Status Code : " +response.getStatusCode());
        }
        return  response;
    }

    public Response register(User user){
        Cookies cookies= new Cookies();

        Header  header = new Header("Content-Type","application/x-www-form-urlencoded");
        Headers headers = new Headers(header);

        HashMap<String,String> formParams = new HashMap<>();
        formParams.put("username", user.getUsername());
        formParams.put("email",user.getEmail());
        formParams.put("password",user.getPassword());
        formParams.put("woocommerce-register-nonce",fetchRegisterNonceValueIUsingJSoup());
        formParams.put("register","Register");

        Response response =  given().
                baseUri(ConfigLoader.getInstanceMethod().
                        getBaseUrl()).
                headers(headers).
                body(formParams).
                cookies(cookies).
                log().
                all(). //to log the request

                        when().
                post("/account").

                then().
                log().
                all().
                extract().
                response();

        if(response.getStatusCode()!= 302){
            throw new RuntimeException("Failed to register the account : " +response.getStatusCode());
        }
        return  response;
    }

}
