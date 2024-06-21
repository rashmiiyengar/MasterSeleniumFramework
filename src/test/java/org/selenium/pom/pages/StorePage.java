package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;

public class StorePage extends BasePage {
    private final By searchFeild = By.xpath("//input[@id='woocommerce-product-search-field-0']");
    private final By searchBtn= By.xpath("//button[@type='submit' and @value='Search']");
    private final By title =By.xpath("//h1[contains(text(),'Search results: “blue”')]");
    private final By addToCartBtn = By.cssSelector("a.button.product_type_simple.add_to_cart_button.ajax_add_to_cart");

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

    public void clickAddtoCartBtn(){
          driver.findElement(addToCartBtn).click();
    }

}
