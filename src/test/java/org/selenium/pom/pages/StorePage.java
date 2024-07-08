package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.components.ProductThumbnail;

import java.io.IOException;
import java.time.Duration;

public class StorePage extends BasePage {
    private final By searchFeild = By.xpath("//input[@id='woocommerce-product-search-field-0']");
    private final By searchBtn= By.xpath("//button[@type='submit' and @value='Search']");
    private final By title =By.xpath("//h1[@class='woocommerce-products-header__title page-title']");

    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }

    private ProductThumbnail productThumbnail;

    public StorePage(WebDriver driver) {
        super(driver);
        new ProductThumbnail(driver);
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



    public StorePage search(String txt){
        driver.findElement(searchFeild).sendKeys(txt);
        driver.findElement(searchBtn).click();
        return this;
    }



    public StorePage load(){
        load("/store");
        return this;
    }


    public ProductPage navigateToTheProductFromStore(Integer id) throws IOException {
        waitLong.until(ExpectedConditions.elementToBeClickable(By.xpath("//h2[normalize-space()='"+ new Product(id).getName() + "']"))).click();
        return new ProductPage(driver);
    }

}
