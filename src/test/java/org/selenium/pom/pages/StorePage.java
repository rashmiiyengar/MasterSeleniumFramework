package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.base.BasePage;

import java.time.Duration;

public class StorePage extends BasePage {
    private final By searchFeild = By.xpath("//input[@id='woocommerce-product-search-field-0']");
    private final By searchBtn= By.xpath("//button[@type='submit' and @value='Search']");
    private final By title =By.xpath("//h1[@class='woocommerce-products-header__title page-title']");
    private final By viewCartLink = By.cssSelector(".added_to_cart.wc-forward");

    public StorePage(WebDriver driver) {
        super(driver);
    }

    public StorePage enterTextInSearchField(String txt){
        driver.findElement(searchFeild).sendKeys(txt);
        return this;
    }

    public StorePage clickSearchBtn(){
        driver.findElement(searchBtn).click();
        return this;
    }

    public String getTitle(){
      return  driver.findElement(title).getText();
    }

    private By getAddToCartBtnElement(String productName){
        return By.cssSelector("a[aria-label='Add “" + productName + "” to your cart']");
    }

    public StorePage clickAddtoCartBtn(String productName){
        By addToCartBtn = getAddToCartBtnElement(productName);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(addToCartBtn)).click();
          driver.findElement(addToCartBtn).click();
          return this;
    }

    public StorePage search(String txt){
        driver.findElement(searchFeild).sendKeys(txt);
        driver.findElement(searchBtn).click();
        return this;
    }

    public CartPage clickViewCarkLink(){
        waitLong.until(ExpectedConditions.visibilityOfElementLocated(viewCartLink)).click();
        return  new CartPage(driver);
    }

}
