package org.selenium.pom.constants;

public enum EndPoint {

    ACCOUNT_EDIT_BILLING_ADDRESS("/account/edit-address/billing/");
    public final String url;

    EndPoint(String url){
        this.url = url;

    }
}
