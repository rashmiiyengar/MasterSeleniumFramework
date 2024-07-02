package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;

public class CartPage extends BasePage {

    private final By productName = By.cssSelector(".product-name a");
    private final By checkoutButon = By.cssSelector(".checkout-button.button.alt.wc-forward");

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public String getProductName(){
        return waitLong.until(ExpectedConditions.visibilityOfElementLocated(productName)).getText();
        //return driver.findElement(productName).getText();
    }

    public CheckoutPage clickCheckoutBtn(){
        waitLong.until(ExpectedConditions.elementToBeClickable(checkoutButon)).click();
       //driver.findElement(checkoutButon).click();
       return new CheckoutPage(driver);
    }

}
