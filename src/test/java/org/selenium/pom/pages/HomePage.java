package org.selenium.pom.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.objects.Product;
import org.selenium.pom.pages.components.HeaderComponent;
import org.selenium.pom.pages.components.ProductThumbnail;

import java.io.IOException;

public class HomePage extends BasePage {


    public ProductThumbnail getProductThumbnail() {
        return productThumbnail;
    }

    public void setProductThumbnail(ProductThumbnail productThumbnail) {
        this.productThumbnail = productThumbnail;
    }

    public HeaderComponent getHeaderComponent() {
        return headerComponent;
    }

    public void setHeaderComponent(HeaderComponent headerComponent) {
        this.headerComponent = headerComponent;
    }

    private HeaderComponent headerComponent;
    private ProductThumbnail productThumbnail;

    public HomePage(WebDriver driver) {
        super(driver);
        headerComponent=  new HeaderComponent(driver);
        productThumbnail=  new ProductThumbnail(driver);
    }

    public ProductPage navigateToTheProduct(int id) throws IOException {
        driver.findElement(By.xpath("//h2[normalize-space()='"+ new Product(id).getName() + "']")).click();
        return new ProductPage(driver);
          }

    public HomePage load(){
        //waitLong.until(ExpectedConditions.titleContains("AskOmDch"));
        load("/");
        return this;
    }


}
