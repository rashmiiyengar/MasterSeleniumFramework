package org.selenium.pom.api.actions;

import io.restassured.http.Cookies;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import org.selenium.pom.constants.Codes;
import org.selenium.pom.objects.BillingAddress;

import java.util.HashMap;

public class BillingApi {

    private Cookies cookies;

    public BillingApi(Cookies cookies){
        this.cookies = cookies;
    }

    public Response addBillingAddress(BillingAddress billingAddress){


        Header header = new Header("content-type","application/x-www-form-urlencoded");
        Headers headers = new Headers(header);

        HashMap<String,Object> formParams = new HashMap<>();
        formParams.put("billing_first_name", billingAddress.getFirstName());
        formParams.put("billing_last_name",billingAddress.getLastName());
        formParams.put("billing_country", Codes.getCountryCode(billingAddress.getCountry()));
        formParams.put("billing_address_1", billingAddress.getAddressLineOne());
        formParams.put("billing_city",billingAddress.getCity());
        formParams.put("billing_state",Codes.getStateCode(billingAddress.getCounty()));
        formParams.put("billing_postcode",billingAddress.getPostalCode());
        formParams.put("billing_company",billingAddress.getCompany());
        formParams.put("billing_phone",billingAddress.getPhone());
    }




}
