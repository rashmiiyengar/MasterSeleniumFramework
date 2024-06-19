package org.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.github.bonigarcia.wdm.WebDriverManager;

public class MyFirstTestCase {

    private WebDriver driver;
    @Test
    public void guestCheckoutUsingDirectBankTransfer(){

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.get("https://askomdch.com");
        driver.manage().window().maximize();

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
        driver.quit();
    }
}
