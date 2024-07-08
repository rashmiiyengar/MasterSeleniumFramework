package org.selenium.pom.pages.components;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.selenium.pom.base.BasePage;
import org.selenium.pom.pages.StorePage;

public class HeaderComponent extends BasePage{

    private final By storeMenuLink = By.xpath("//li[@id='menu-item-1227']");
    public HeaderComponent(WebDriver driver) {
        super(driver);
    }

    public StorePage navigateToStoreUsingMenu(){
        driver.findElement(storeMenuLink).click();
        return  new StorePage(driver);
    }//fluent interface







}
