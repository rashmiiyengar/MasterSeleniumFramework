package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;

public class CheckoutPage extends BasePage {

    private final By firstName = By.xpath("//input[@id='billing_first_name']");
    private final By lastName = By.xpath("//input[@id='billing_last_name']");
    private final By billingCompany = By.xpath("//input[@id='billing_company']");
    private final By billingAddress = By.xpath("//input[@id='billing_address_1']");
    private final By billingCity = By.xpath("//input[@id='billing_city']");
    private final By billingPostCode = By.xpath("//input[@id='billing_postcode']");
    private final By billingEmail = By.xpath("//input[@id='billing_email']");
    private final By billingPhone = By.xpath("//input[@id='billing_phone']");
    private final By placeOrder = By.xpath("//button[@id='place_order']");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public  CheckoutPage sendFirstName(String firstNameTxt){
        driver.findElement(firstName).sendKeys(firstNameTxt);
        return this;
    }

    public  CheckoutPage sendLastName(String lastNameTxt){
        driver.findElement(lastName).sendKeys(lastNameTxt);
        return this;
    }

    public  CheckoutPage sendBillingCompany(String billingCompanyTxt){
        driver.findElement(billingCompany).sendKeys(billingCompanyTxt);
        return this;
    }

    public  CheckoutPage sendBillingAddress(String billingAddressTxt){
        driver.findElement(billingAddress).sendKeys(billingAddressTxt);
        return this;
    }

    public  CheckoutPage sendBillingCity(String billingCityTxt){
        driver.findElement(billingCity).sendKeys(billingCityTxt);
        return this;
    }

    public  CheckoutPage sendBillingPostcode(String billingPostcodeTxt){
        driver.findElement(billingPostCode).sendKeys(billingPostcodeTxt);
        return this;
    }

    public  CheckoutPage sendBillingEmail(String billingEmailTxt){
        driver.findElement(billingEmail).sendKeys(billingEmailTxt);
        return this;
    }

    public  CheckoutPage sendBillingPhone(String billingPhoneTxt){
        driver.findElement(billingPhone).sendKeys(billingPhoneTxt);
        return this;
    }

    public CheckoutPage clickPlaceOrder(){

        driver.findElement(placeOrder).click();
        return this;
    }

}
