package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.extentreports.ExtentReport;

public class CartPage extends BasePage {

    // private final By productName = By.cssSelector(".product-name a");
    // private final By checkoutButon = By.cssSelector(".checkout-button.button.alt.wc-forward");

    // Using page factory
    // For tcs where construction of selector is at run time we cant use page factory

    @FindBy(css = ".product-name a" )
    private WebElement productName;

    @FindBy(css = ".checkout-button.button.alt.wc-forward") @CacheLookup
    private  WebElement checkoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);
    }

    public String getProductName(){
        return waitLong.until(ExpectedConditions.visibilityOf(productName)).getText();
        //return driver.findElement(productName).getText();
    }

    public CheckoutPage clickCheckoutBtn(){
        waitLong.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
        ExtentReport.test.pass("Checkout button clicked");
       //driver.findElement(checkoutButon).click();
       return new CheckoutPage(driver);
    }

}
