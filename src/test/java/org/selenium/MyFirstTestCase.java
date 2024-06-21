package org.selenium;

import org.bouncycastle.util.Store;
import org.openqa.selenium.By;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.HomePage;
import org.selenium.pom.pages.StorePage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MyFirstTestCase extends BaseTest {


    @Test
    public void guestCheckoutUsingDirectBankTransfer(){
        driver.get("https://askomdch.com");

        HomePage homePage = new HomePage(driver);
        StorePage storePage= homePage.clickStoreMenuLink();
        storePage.
                 enterTextInSearchField("blue").
                 clickSearchBtn();
        //storePage.clickSearchBtn();
        Assert.assertEquals(storePage.getTitle(),"Search results: “blue”");
        storePage.clickAddtoCartBtn();





        driver.findElement(By.xpath("//li[@id='menu-item-1227']")).click();
        driver.findElement(By.xpath("//input[@id='woocommerce-product-search-field-0']")).sendKeys("blue");
        driver.findElement(By.xpath("//button[@type='submit' and @value='Search']")).click();
        Assert.assertEquals(
                driver.findElement(By.xpath("//h1[contains(text(),'Search results: “blue”')]")).getText(),
                "Search results: “blue”"
        );
        driver.findElement(By.cssSelector("a.button.product_type_simple.add_to_cart_button.ajax_add_to_cart")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        driver.findElement(By.cssSelector("a.added_to_cart.wc-forward")).click();
        Assert.assertEquals(

                driver.findElement(By.cssSelector(".product-name a")).getText(),"Blue Shoes");
        driver.findElement(By.cssSelector(".checkout-button.button.alt.wc-forward")).click();

    }
}
