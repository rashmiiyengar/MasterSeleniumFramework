package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.BillingAddress;

import java.time.Duration;
import java.util.List;

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

    private final By clickLogin = By.cssSelector(".showlogin");
    private final By loginFirstname = By.xpath("//input[@id=\"username\"]");
    private final By loginPassword = By.xpath("//input[@id=\"password\"]");
    private final By loginBtn = By.cssSelector(".woocommerce-button.button.woocommerce-form-login__submit");

    private final By overLay = By.cssSelector(".blockUI.blockOverlay");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public  CheckoutPage sendFirstName(String firstNameTxt){
        driver.findElement(firstName).clear();
        driver.findElement(firstName).sendKeys(firstNameTxt);
        return this;
    }

    public  CheckoutPage sendLastName(String lastNameTxt){
        driver.findElement(lastName).clear();
        driver.findElement(lastName).sendKeys(lastNameTxt);
        return this;
    }

    public  CheckoutPage sendBillingCompany(String billingCompanyTxt){
        driver.findElement(billingCompany).clear();
        driver.findElement(billingCompany).sendKeys(billingCompanyTxt);
        return this;
    }

    public  CheckoutPage sendBillingAddress(String billingAddressTxt){
        driver.findElement(billingAddress).clear();
        driver.findElement(billingAddress).sendKeys(billingAddressTxt);
        return this;
    }

    public  CheckoutPage sendBillingCity(String billingCityTxt){
        driver.findElement(billingCity).clear();
        driver.findElement(billingCity).sendKeys(billingCityTxt);
        return this;
    }

    public  CheckoutPage sendBillingPostcode(String billingPostcodeTxt){
        driver.findElement(billingPostCode).clear();
        driver.findElement(billingPostCode).sendKeys(billingPostcodeTxt);
        return this;
    }

    public  CheckoutPage sendBillingEmail(String billingEmailTxt){
        driver.findElement(billingEmail).clear();
        driver.findElement(billingEmail).sendKeys(billingEmailTxt);
        return this;
    }

    public  CheckoutPage sendBillingPhone(String billingPhoneTxt){
        driver.findElement(billingPhone).clear();
        driver.findElement(billingPhone).sendKeys(billingPhoneTxt);
        return this;
    }

    public CheckoutPage clickLoginLink(){
        driver.findElement(clickLogin).click();
        return this;
    }

    public CheckoutPage login(String username,String password){
        driver.findElement(loginFirstname).sendKeys(username);
        driver.findElement(loginPassword).sendKeys(password);
        return this;
    }

    public CheckoutPage clickLoginButton(){
        driver.findElement(loginBtn).click();
        return this;
    }

    public ConfirmationPage clickPlaceOrder(){
        waitForElementToDisappear(overLay);
        driver.findElement(placeOrder).click();
        return new ConfirmationPage(driver);
    }

    public CheckoutPage setBillingAddress(BillingAddress billingAddress){

      return  sendFirstName(billingAddress.getFirstName()).
                sendLastName(billingAddress.getLastName()).
                        sendBillingAddress(billingAddress.getAddressLineOne()).
                            sendBillingCity(billingAddress.getCity()).
                                sendBillingPostcode(billingAddress.getPostalCode()).
                                    sendBillingEmail(billingAddress.getEmail());

    }

}
