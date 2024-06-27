package org.selenium.pom.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
protected WebDriver driver;


public BasePage(WebDriver driver){
    this.driver=driver;
}

public void load(String endPoint){
    driver.get("https://askomdch.com" +endPoint);
}

public void waitForElementToDisappear(By element){
    List<WebElement> overlays = driver.findElements(element);
    System.out.println(overlays.size());
    if(overlays.size()>0){
        new WebDriverWait(driver, Duration.ofSeconds(15)).until(
                ExpectedConditions.invisibilityOfAllElements(overlays)
        );
        System.out.println("Overlays is invisible");
    }
    else{
        System.out.println("Overlay not found");
    }
}
}
