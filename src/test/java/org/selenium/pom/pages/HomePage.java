package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.Product;

import java.io.IOException;

public class HomePage extends BasePage {
  private final By storeMenuLink = By.xpath("//li[@id='menu-item-1227']");
  private final By viewCartLink = By.cssSelector(".added_to_cart.wc-forward");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public StorePage navigateToStoreUsingMenu(){
        driver.findElement(storeMenuLink).click();
        return  new StorePage(driver);
    }//fluent interface

    public ProductPage navigateToTheProduct(int id) throws IOException {
        driver.findElement(By.xpath("//h2[normalize-space()='"+ new Product(id).getName() + "']")).click();
        return new ProductPage(driver);
          }

    public HomePage load(){
        //waitLong.until(ExpectedConditions.titleContains("AskOmDch"));
        load("/");
        return this;
    }

    private By getAddToCartBtnElement(String productName){
        return By.cssSelector("a[aria-label='Add “" + productName + "” to your cart']");
    }

    public HomePage clickAddToCartButtonFromHomePage(String productName){
       By addToCartBtnFromHome =  getAddToCartBtnElement(productName);

       waitLong.until(ExpectedConditions.elementToBeClickable(addToCartBtnFromHome)).click();
        return  this;
    }

    public CartPage clickViewCarkLink(){
        waitLong.until(ExpectedConditions.visibilityOfElementLocated(viewCartLink)).click();
        return  new CartPage(driver);
    }

//    public void clickStoreMenuLink(){
//        driver.findElement(storeMenuLink).click();
//
//    }
}
