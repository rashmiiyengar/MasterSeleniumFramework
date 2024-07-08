package org.selenium.pom.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.CartPage;
import org.selenium.pom.pages.HomePage;

public class ProductThumbnail extends BasePage {
    private final By viewCartLink = By.cssSelector(".added_to_cart.wc-forward");

    public ProductThumbnail(WebDriver driver) {
        super(driver);
    }

    private By getAddToCartBtnElement(String productName){
        return By.cssSelector("a[aria-label='Add “" + productName + "” to your cart']");
    }

    public ProductThumbnail clickAddToCartButton(String productName){
        By addToCartBtnFromHome =  getAddToCartBtnElement(productName);

        waitLong.until(ExpectedConditions.elementToBeClickable(addToCartBtnFromHome)).click();
        return  this;
    }

    public CartPage clickViewCarkLink(){
        waitLong.until(ExpectedConditions.elementToBeClickable(viewCartLink)).click();
        return  new CartPage(driver);
    }
}
