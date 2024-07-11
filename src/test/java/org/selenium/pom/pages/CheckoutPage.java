package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.extentreports.ExtentLogger;
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
    private final By countryDropdown = By.id("billing_country");
    private final By countyDropdown = By.id("billing_state");
    private final By directBankTransfer= By.id("payment_method_bacs");


    private final By clickLogin = By.cssSelector(".showlogin");
    private final By loginFirstname = By.xpath("//input[@id=\"username\"]");
    private final By loginPassword = By.xpath("//input[@id=\"password\"]");
    private final By loginBtn = By.cssSelector(".woocommerce-button.button.woocommerce-form-login__submit");

    private final By overLay = By.cssSelector(".blockUI.blockOverlay");
    private final By productNameCheckout = By.xpath("(//td[@class='product-name'])[1]");
    private final By thankYouNotice = By.cssSelector(".woocommerce-order p");
    private final By cashOnDelivaryRadioBtn = By.id("payment_method_cod");

    public CheckoutPage(WebDriver driver) {
        super(driver);
    }

    public  CheckoutPage sendFirstName(String firstNameTxt){
        WebElement e= waitLong.until(ExpectedConditions.visibilityOfElementLocated((firstName)));
        e.clear();
        e.sendKeys(firstNameTxt);
        //using get element method created in base class

        return this;
    }

    public  CheckoutPage sendLastName(String lastNameTxt){
        WebElement e= waitLong.until(ExpectedConditions.visibilityOfElementLocated((lastName)));
        e.clear();
        e.sendKeys(lastNameTxt);
        return this;
    }

    public  CheckoutPage sendBillingCompany(String billingCompanyTxt){
        WebElement e=waitLong.until(ExpectedConditions.visibilityOfElementLocated((billingCompany)));
        e.clear();
        e.sendKeys(billingCompanyTxt);
        return this;
    }

    public CheckoutPage selectCountry(String countryName){

        Select select = new Select(driver.findElement(countryDropdown));
        select.selectByVisibleText(countryName);
        return  this;
    }

    public CheckoutPage selectCounty(String countyName){

        Select select = new Select(driver.findElement(countyDropdown));
        select.selectByVisibleText(countyName);
        return  this;
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
        waitLong.until(ExpectedConditions.visibilityOfElementLocated(clickLogin)).click();
        return this;
    }

    public CheckoutPage login(String username,String password){
        waitLong.until(ExpectedConditions.visibilityOfElementLocated(loginFirstname));
        driver.findElement(loginFirstname).sendKeys(username);
        driver.findElement(loginPassword).sendKeys(password);

        return this;
    }

    public CheckoutPage clickLoginButton(){
        waitLong.until(ExpectedConditions.visibilityOfElementLocated(loginBtn)).click();
        waitForLoginBtnToDisappear();
        ExtentLogger.pass("Login successfull");
        return this;
    }

    public CheckoutPage selectDirectBankTransfer(){
       WebElement e= waitLong.until(ExpectedConditions.elementToBeClickable(directBankTransfer));
       if(!e.isSelected()){
           e.click();
       }
        return this;
    }


    public ConfirmationPage clickPlaceOrder(){
        waitForElementToDisappear(overLay);
        driver.findElement(placeOrder).click();
        return new ConfirmationPage(driver);
    }

    public CheckoutPage load(){
        load("/checkout");
        return  this;
    }

    public String getProductName (){
      return  waitLong.until(ExpectedConditions.visibilityOfElementLocated(productNameCheckout)).getText();
    }

    public String getNotice(){
        return waitLong.until(ExpectedConditions.visibilityOfElementLocated(thankYouNotice)).getText();
    }

    private CheckoutPage waitForLoginBtnToDisappear(){
        waitLong.until(ExpectedConditions.invisibilityOfElementLocated(loginBtn));
        return this;
    }

    public CheckoutPage setBillingAddress(BillingAddress billingAddress){

      return  sendFirstName(billingAddress.getFirstName()).
                sendLastName(billingAddress.getLastName()).
                    selectCountry(billingAddress.getCountry()).
                        sendBillingAddress(billingAddress.getAddressLineOne()).
                            sendBillingCity(billingAddress.getCity()).
                                selectCounty(billingAddress.getState()).
                                    sendBillingPostcode(billingAddress.getPostalCode()).
                                        sendBillingEmail(billingAddress.getEmail());

    }

    public CheckoutPage selectCashOnDeliveryMethod(){
        waitForElementToDisappear(overLay);
        waitLong.until(ExpectedConditions.elementToBeClickable(cashOnDelivaryRadioBtn)).click();
        return  this;
    }

}
