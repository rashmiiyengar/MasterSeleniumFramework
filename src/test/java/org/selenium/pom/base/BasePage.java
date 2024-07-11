package org.selenium.pom.base;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.selenium.pom.utils.ConfigLoader;

import java.time.Duration;
import java.util.List;

public class BasePage {
protected WebDriver driver;
protected WebDriverWait waitLong;
protected WebDriverWait waitShort;

public BasePage(WebDriver driver){
    this.driver=driver;
    waitLong = new WebDriverWait(driver,Duration.ofSeconds(30));
    waitShort = new WebDriverWait(driver,Duration.ofSeconds(5));
}

protected void load(String endPoint){
    driver.get(ConfigLoader.getInstanceMethod().getBaseUrl() +endPoint);
}


protected void waitForElementToDisappear(By element) {
    List<WebElement> overlays = driver.findElements(element);
    System.out.println(overlays.size());
    if (overlays.size() > 0) {
        waitLong.until(
                ExpectedConditions.invisibilityOfAllElements(overlays)
        );
        System.out.println("Overlays is invisible");
    } else {
        System.out.println("Overlay not found");
    }
}

}
