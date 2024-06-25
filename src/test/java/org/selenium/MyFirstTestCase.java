package org.selenium;

import org.bouncycastle.util.Store;
import org.openqa.selenium.By;
import org.selenium.pom.base.BaseTest;
import org.selenium.pom.pages.*;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MyFirstTestCase extends BaseTest {


    @Test
    public void guestCheckoutUsingDirectBankTransfer() throws InterruptedException {
        driver.get("https://askomdch.com");

        HomePage homePage = new HomePage(driver);
        StorePage storePage= homePage.navigateToStoreUsingMenu();
        storePage.search("Blue");
                 //enterTextInSearchField("Blue").
                 //clickSearchBtn();
        //storePage.clickSearchBtn();

        Assert.assertEquals(storePage.getTitle(),"Search results: “Blue”");
        Thread.sleep(2000);
        storePage.clickAddtoCartBtn("Blue Shoes");
        Thread.sleep(5000);
        CartPage cartpage = storePage.clickViewCarkLink();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
      Assert.assertEquals(cartpage.getProductName(),"Blue Shoes");
        CheckoutPage checkoutPage = cartpage.clickCheckoutBtn();
        checkoutPage.
                sendFirstName("Sai").
                sendLastName("Ram").
                sendBillingCompany("Nexus Tech").
                sendBillingAddress("MUMBAI").
                sendBillingCity("baroda").
                sendBillingPostcode("982345").
                sendBillingPhone("7585467544").
                sendBillingEmail("sai@gmail.com").
                clickPlaceOrder();





    }
}
